import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login userLogin = new Login();

        System.out.println("=== USER REGISTRATION ===");
        
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        userLogin.setFirstName(firstName);

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        userLogin.setLastName(lastName);

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        userLogin.setUsername(username);

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        userLogin.setPassword(password);

        System.out.print("Enter Cell Phone Number (e.g., +27838968976): ");
        String cellPhone = scanner.nextLine();
        userLogin.setCellPhoneNumber(cellPhone);

        // Display registration status message
        String registrationMessage = userLogin.registerUser();
        System.out.println("\n" + registrationMessage);

        // Only proceed to login if registration conditions were met
        if (registrationMessage.contains("registered successfully")) {
            System.out.println("\n=== USER LOGIN ===");
            
            System.out.print("Enter Username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();

            boolean isAuthenticated = userLogin.loginUser(loginUsername, loginPassword);
            System.out.println(userLogin.returnLoginStatus(isAuthenticated));
        }

        scanner.close();
    }
}