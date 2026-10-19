/**
 * Module: PROG5121 - Part 2
 * File Name: MessageTest.java
 * Student Name: Mlungisi Balane
 * Description: Unit test suite validating message lengths, recipient cell numbers, hashes, and send options.
 */
public class MessageTest {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("     QUICKCHAT UNIT TEST EXECUTION       ");
        System.out.println("==========================================");

        int passed = 0;
        int failed = 0;

        // Test 1: Message length check success
        if (testMessageLengthSuccess()) passed++; else failed++;

        // Test 2: Message length check failure (> 250 chars)
        if (testMessageLengthFailure()) passed++; else failed++;

        // Test 3: Valid cell number format
        if (testRecipientValidFormat()) passed++; else failed++;

        // Test 4: Invalid cell number format
        if (testRecipientInvalidFormat()) passed++; else failed++;

        // Test 5: Hash creation
        if (testCreateMessageHash()) passed++; else failed++;

        // Test 6: Message counter increment
        if (testTotalMessagesCount()) passed++; else failed++;

        System.out.println("\n------------------------------------------");
        System.out.println("RESULTS: " + passed + " Passed | " + failed + " Failed");
        System.out.println("==========================================");
    }

    public static boolean testMessageLengthSuccess() {
        Message msg = new Message("0821234567", "Hi Mike, can you join the call tonight?");
        boolean result = msg.checkMessageLength();
        printResult("testMessageLengthSuccess", result);
        return result;
    }

    public static boolean testMessageLengthFailure() {
        String longText = "This message is intentionally made extremely long to exceed the maximum character limit set for the QuickChat system. "
                + "We need to ensure that the character validation logic properly identifies when a message has surpassed the two hundred "
                + "and fifty character threshold and flags it as invalid accordingly.";
        Message msg = new Message("0821234567", longText);
        boolean result = !msg.checkMessageLength(); // Expecting false
        printResult("testMessageLengthFailure", result);
        return result;
    }

    public static boolean testRecipientValidFormat() {
        Message msg = new Message("0821234567", "Hello");
        boolean result = msg.checkRecipient();
        printResult("testRecipientValidFormat", result);
        return result;
    }

    public static boolean testRecipientInvalidFormat() {
        Message msg = new Message("082123", "Hello");
        boolean result = !msg.checkRecipient(); // Expecting false
        printResult("testRecipientInvalidFormat", result);
        return result;
    }

    public static boolean testCreateMessageHash() {
        Message msg = new Message("0821234567", "Hi Keegan, please review the document");
        boolean result = msg.getMessageHash() != null && msg.getMessageHash().startsWith(msg.getMessageID());
        printResult("testCreateMessageHash", result);
        return result;
    }

    public static boolean testTotalMessagesCount() {
        int initialCount = Message.returnTotalMessages();
        Message msg = new Message("0821234567", "Test message");
        msg.checkSendOption(1);
        boolean result = (Message.returnTotalMessages() == initialCount + 1);
        printResult("testTotalMessagesCount", result);
        return result;
    }

    private static void printResult(String testName, boolean success) {
        if (success) {
            System.out.println("[PASS] " + testName);
        } else {
            System.out.println("[FAIL] " + testName);
        }
    }
}