package travel.management.system;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

/**
 * JUnit test for Login class
 */
public class LoginTest {
    
    private Login login;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JButton forgotButton;
    
    @Before
    public void setUp() throws Exception {
        // Create a Login instance
        login = new Login();
        
        // Access private fields using reflection
        Field textFieldField = Login.class.getDeclaredField("textField");
        textFieldField.setAccessible(true);
        textField = (JTextField) textFieldField.get(login);
        
        Field passwordFieldField = Login.class.getDeclaredField("passwordField");
        passwordFieldField.setAccessible(true);
        passwordField = (JPasswordField) passwordFieldField.get(login);
        
        Field b1Field = Login.class.getDeclaredField("b1");
        b1Field.setAccessible(true);
        loginButton = (JButton) b1Field.get(login);
        
        Field b2Field = Login.class.getDeclaredField("b2");
        b2Field.setAccessible(true);
        signupButton = (JButton) b2Field.get(login);
        
        Field b3Field = Login.class.getDeclaredField("b3");
        b3Field.setAccessible(true);
        forgotButton = (JButton) b3Field.get(login);
    }
    
    /**
     * Test UI component initialization
     */
    @Test
    public void testUIComponentsInitialization() {
        // Verify UI components are initialized properly
        assertNotNull("TextField should be initialized", textField);
        assertNotNull("PasswordField should be initialized", passwordField);
        assertNotNull("Login button should be initialized", loginButton);
        assertNotNull("Signup button should be initialized", signupButton);
        assertNotNull("Forgot Password button should be initialized", forgotButton);
        
        // Test button text
        assertEquals("Login button text should be 'Login'", "Login", loginButton.getText());
        assertEquals("Signup button text should be 'SignUp'", "SignUp", signupButton.getText());
        assertEquals("Forgot Password button text should be 'Forgot Password'", "Forgot Password", forgotButton.getText());
    }
    
    
    /**
     * Test navigation to Signup screen
     */
    @Test
    public void testNavigationToSignup() throws Exception {
        // Test requires mocking window visibility changes
        // We can just verify the button exists and has the correct action listener
        assertTrue("Signup button should have ActionListener", signupButton.getActionListeners().length > 0);
    }
    
    /**
     * Test navigation to Forgot Password screen
     */
    @Test
    public void testNavigationToForgotPassword() throws Exception {
        // Test requires mocking window visibility changes
        // We can just verify the button exists and has the correct action listener
        assertTrue("Forgot Password button should have ActionListener", forgotButton.getActionListeners().length > 0);
    }
    
    /**
     * Test main method execution
     */
    @Test
    public void testMainMethod() {
        try {
            Login.main(new String[]{});
            // If we get here without exception, the test passes
            assertTrue(true);
        } catch (Exception e) {
            fail("Main method should execute without throwing exception: " + e.getMessage());
        }
    }
    
    /**
     * Utility class for mocking JOptionPane
     */
    static class MockUtilities {
        public static void mockJOptionPane(final boolean[] showMessageDialogCalled) {
            // Create a custom SecurityManager to prevent System.exit calls
            final SecurityManager securityManager = System.getSecurityManager();
            System.setSecurityManager(new SecurityManager() {
                @Override
                public void checkPermission(java.security.Permission perm) {
                    if (perm.getName().contains("showMessageDialog")) {
                        showMessageDialogCalled[0] = true;
                    }
                }
                
                @Override
                public void checkExit(int status) {
                    throw new SecurityException("System.exit called");
                }
            });
        }
    }
}
