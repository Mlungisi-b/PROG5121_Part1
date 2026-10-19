/**
 * Module: PROG5121 - Part 2
 * File Name: Main.java
 * Student Name: Mlungisi Balane
 * Description: Entry point for the QuickChat application featuring login checks and the main option menu loop.
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login userLogin = new Login();

        System.out.println("=== QUICKCHAT APPLICATION LOGIN ===");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (userLogin.loginUser(username, password)) {
            System.out.println(userLogin.returnLoginStatus(true));
            runMessageMenu(scanner);
        } else {
            System.out.println(userLogin.returnLoginStatus(false));
            System.out.println("Access Denied. Exiting program.");
        }

        scanner.close();
    }

    private static void runMessageMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- QUICKCHAT MAIN MENU ---");
            System.out.println("1. Send / Store Message");
            System.out.println("2. View Total Messages Sent");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            String choiceInput = scanner.nextLine();
            int choice = 0;

            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    processMessageCreation(scanner);
                    break;
                case 2:
                    System.out.println("\nTotal Messages Sent/Stored: " + Message.returnTotalMessages());
                    break;
                case 3:
                    System.out.println("Exiting QuickChat. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid menu choice. Please select 1, 2, or 3.");
            }
        }
    }

    private static void processMessageCreation(Scanner scanner) {
        System.out.print("\nEnter recipient cell number (e.g. 0821234567): ");
        String recipient = scanner.nextLine();

        System.out.print("Enter message text (max 250 chars): ");
        String text = scanner.nextLine();

        Message msg = new Message(recipient, text);

        if (!msg.checkRecipient()) {
            System.out.println("Error: Recipient number is invalid. Must be 10 digits starting with '0'.");
            return;
        }

        if (!msg.checkMessageLength()) {
            System.out.println("Error: Message exceeds 250 characters. Please shorten your message.");
            return;
        }

        System.out.println("\n--- MESSAGE SUMMARY ---");
        System.out.println(msg.printMessage());

        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Send Message");
        System.out.println("2. Store Message");
        System.out.println("3. Discard Message");
        System.out.print("Select choice: ");

        String optionInput = scanner.nextLine();
        int option = 0;
        try {
            option = Integer.parseInt(optionInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice. Message discarded.");
            return;
        }

        String resultMsg = msg.checkSendOption(option);
        System.out.println("Status: " + resultMsg);
    }
}