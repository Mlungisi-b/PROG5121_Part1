/**
 * Module: PROG5121 - Part 2
 * File Name: Message.java
 * Student Name: Mlungisi Balane
 * Description: Handles message validation, recipient checks, ID generation, hash creation, and storage logic.
 */
import java.util.Random;

public class Message {

    private String recipient;
    private String messageText;
    private String messageID;
    private String messageHash;

    // Static counter for total messages sent/stored across sessions
    private static int totalMessagesCount = 0;

    // Constructor
    public Message(String recipient, String messageText) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Validates that message length does not exceed 250 characters
    public boolean checkMessageLength() {
        return this.messageText != null && this.messageText.length() <= 250;
    }

    // Validates recipient cell number (10 digits starting with '0')
    public boolean checkRecipient() {
        if (this.recipient == null) {
            return false;
        }
        return this.recipient.matches("^0\\d{9}$");
    }

    // Generates a random 10-digit Message ID
    public String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // Creates message hash: ID + ":" + length + ":" + FIRST & LAST word capitalized
    public String createMessageHash() {
        if (this.messageText == null || this.messageText.trim().isEmpty()) {
            return this.messageID + ":0:EMPTY";
        }

        String[] words = this.messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        if (firstWord.isEmpty()) firstWord = "MSG";
        if (lastWord.isEmpty()) lastWord = "MSG";

        return this.messageID + ":" + this.messageText.length() + ":" + firstWord + lastWord;
    }

    // Evaluates the option selected by the user (Send, Store, Discard)
    public String checkSendOption(int option) {
        switch (option) {
            case 1:
                totalMessagesCount++;
                return "Message successfully sent.";
            case 2:
                totalMessagesCount++;
                return "Message successfully stored.";
            case 3:
                return "Message discarded.";
            default:
                return "Invalid option selected.";
        }
    }

    // Returns full formatted message details
    public String printMessage() {
        return "Message ID: " + this.messageID + "\n"
             + "Recipient: " + this.recipient + "\n"
             + "Message: " + this.messageText + "\n"
             + "Message Hash: " + this.messageHash;
    }

    // Getter methods
    public String getMessageID() {
        return this.messageID;
    }

    public String getMessageHash() {
        return this.messageHash;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public static int returnTotalMessages() {
        return totalMessagesCount;
    }
}