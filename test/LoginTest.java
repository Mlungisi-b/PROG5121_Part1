import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private final Login login = new Login();

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("Kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&secake99!"));
    }

    @Test
    public void testPasswordFailsComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testLoginSuccessful() {
        Login user = new Login("kyl_1", "Ch&&secake99!", "+27838968976", "Kyle", "LastName");
        assertTrue(user.loginUser("kyl_1", "Ch&&secake99!"));
    }

    @Test
    public void testLoginFailed() {
        Login user = new Login("kyl_1", "Ch&&secake99!", "+27838968976", "Kyle", "LastName");
        assertFalse(user.loginUser("kyl_1", "wrongpassword"));
    }
}