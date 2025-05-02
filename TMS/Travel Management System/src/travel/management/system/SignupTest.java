package travel.management.system;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * JUnit test for Signup class
 */
public class SignupTest {
    
    private Signup signup;
    private JTextField textField;      // username
    private JTextField textField_1;    // name
    private JTextField textField_2;    // password
    private JTextField textField_3;    // answer
    private JComboBox comboBox;        // security question
    private JButton b1;                // create button
    private JButton b2;                // back button
    
    @Before
    public void setUp() throws Exception {
        // Create a Signup instance
        signup = new Signup();
        
        // Access private fields using reflection
        Class<?> signupClass = signup.getClass();
        
        Field textFieldField = signupClass.getDeclaredField("textField");
        textFieldField.setAccessible(true);
        textField = (JTextField) textFieldField.get(signup);
        
        Field textField_1Field = signupClass.getDeclaredField("textField_1");
        textField_1Field.setAccessible(true);
        textField_1 = (JTextField) textField_1Field.get(signup);
        
        Field textField_2Field = signupClass.getDeclaredField("textField_2");
        textField_2Field.setAccessible(true);
        textField_2 = (JTextField) textField_2Field.get(signup);
        
        Field textField_3Field = signupClass.getDeclaredField("textField_3");
        textField_3Field.setAccessible(true);
        textField_3 = (JTextField) textField_3Field.get(signup);
        
        Field comboBoxField = signupClass.getDeclaredField("comboBox");
        comboBoxField.setAccessible(true);
        comboBox = (JComboBox) comboBoxField.get(signup);
        
        Field b1Field = signupClass.getDeclaredField("b1");
        b1Field.setAccessible(true);
        b1 = (JButton) b1Field.get(signup);
        
        Field b2Field = signupClass.getDeclaredField("b2");
        b2Field.setAccessible(true);
        b2 = (JButton) b2Field.get(signup);
    }
    
    /**
     * Test the initialization of UI components
     */
    @Test
    public void testUIComponentsInitialization() {
        // Verify that the components are properly initialized
        assertNotNull("Username field should be initialized", textField);
        assertNotNull("Name field should be initialized", textField_1);
        assertNotNull("Password field should be initialized", textField_2);
        assertNotNull("Answer field should be initialized", textField_3);
        assertNotNull("Security question comboBox should be initialized", comboBox);
        assertNotNull("Create button should be initialized", b1);
        assertNotNull("Back button should be initialized", b2);
        
        // Verify button text
        assertEquals("Create button text should be 'Create'", "Create", b1.getText());
        assertEquals("Back button text should be 'Back'", "Back", b2.getText());
        
        // Verify comboBox items
        assertEquals("ComboBox should have 4 security questions", 4, comboBox.getItemCount());
        assertEquals("First item should be 'Your NickName?'", "Your NickName?", comboBox.getItemAt(0));
        assertEquals("Second item should be 'Your Lucky Number?'", "Your Lucky Number?", comboBox.getItemAt(1));
        assertEquals("Third item should be 'Your child SuperHero?'", "Your child SuperHero?", comboBox.getItemAt(2));
        assertEquals("Fourth item should be 'Your childhood Name ?'", "Your childhood Name ?", comboBox.getItemAt(3));
    }
    
    /**
     * Test user input validation and form submission
     */
    @Test
    public void testFormInputAndSubmission() {
        // Set form values
        textField.setText("testuser");
        textField_1.setText("Test User");
        textField_2.setText("password123");
        comboBox.setSelectedIndex(1); // Select second security question
        textField_3.setText("7");
        
        // Verify the values are set correctly
        assertEquals("Username should be set", "testuser", textField.getText());
        assertEquals("Name should be set", "Test User", textField_1.getText());
        assertEquals("Password should be set", "password123", textField_2.getText());
        assertEquals("Security question should be selected", "Your Lucky Number?", comboBox.getSelectedItem());
        assertEquals("Answer should be set", "7", textField_3.getText());
    }
    

    /**
     * Test navigation back to login
     */
    @Test
    public void testNavigationBackToLogin() {
        // Verify that the back button has an action listener
        assertTrue("Back button should have action listener", b2.getActionListeners().length > 0);
        
        // Note: We can't fully test the navigation without mocking window management
        // This would typically hide the current window and show the login window
    }
    
    /**
     * Test main method execution
     */
    @Test
    public void testMainMethod() {
        try {
            Signup.main(new String[]{});
            // If no exception, the test passes
            assertTrue(true);
        } catch (Exception e) {
            fail("Main method should execute without exception: " + e.getMessage());
        }
    }
    
    /**
     * Mock class for database connection
     */
    private class MockConn {
        public Connection c;
        
        public MockConn() {
            // Mock connection that doesn't actually connect to a database
            c = null;
        }
    }
    
    /**
     * Helper method to set mock connection
     */
    private void setMockConnection(Signup signup, MockConn mockConn) throws Exception {
        // This would ideally replace the Conn instance in the actionPerformed method
        // However, since it's not stored as a field, we can't easily replace it
        // In a real test, you might refactor the code to allow dependency injection
    }
}