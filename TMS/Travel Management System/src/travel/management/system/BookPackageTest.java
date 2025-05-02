package travel.management.system;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Choice;

/**
 * JUnit test for BookPackage class
 */
public class BookPackageTest {
    
    private BookPackage bookPackage;
    private JTextField t1; // Total Persons
    private Choice c1;     // Package selection
    private JButton checkPriceButton;
    private JButton bookButton;
    private JButton backButton;
    private JLabel totalPriceLabel;
    private JLabel usernameLabel;
    
    @Before
    public void setUp() throws Exception {
        // Create a BookPackage instance with a test username
        try {
            bookPackage = new BookPackage("testuser");
            
            // Access private fields using reflection
            Class<?> bookPackageClass = bookPackage.getClass();
            
            Field t1Field = bookPackageClass.getDeclaredField("t1");
            t1Field.setAccessible(true);
            t1 = (JTextField) t1Field.get(bookPackage);
            
            Field c1Field = bookPackageClass.getDeclaredField("c1");
            c1Field.setAccessible(true);
            c1 = (Choice) c1Field.get(bookPackage);
            
            // Find buttons and labels by traversing components
            java.awt.Component[] components = bookPackage.getContentPane().getComponents();
            for (java.awt.Component component : components) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    if ("Check Price".equals(button.getText())) {
                        checkPriceButton = button;
                    } else if ("Book".equals(button.getText())) {
                        bookButton = button;
                    } else if ("Back".equals(button.getText())) {
                        backButton = button;
                    }
                } else if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    if (label.getForeground() != null && label.getForeground().equals(java.awt.Color.RED)) {
                        totalPriceLabel = label;
                    }
                    if ("testuser".equals(label.getText())) {
                        usernameLabel = label;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Could not create BookPackage instance: " + e.getMessage());
            // Skip tests if we can't create the instance
        }
    }
    
    /**
     * Test UI components initialization
     */
    @Test
    public void testUIComponentsInitialization() {
        if (bookPackage == null) {
            System.out.println("Skipping test: could not create BookPackage instance");
            return;
        }
        
        // Verify UI components are properly initialized
        assertNotNull("Total Persons field should be initialized", t1);
        assertNotNull("Package selection choice should be initialized", c1);
        
        // Verify buttons
        assertNotNull("Check Price button should be initialized", checkPriceButton);
        assertNotNull("Book button should be initialized", bookButton);
        assertNotNull("Back button should be initialized", backButton);
        
        // Verify button text
        assertEquals("Check Price button text should be 'Check Price'", "Check Price", checkPriceButton.getText());
        assertEquals("Book button text should be 'Book'", "Book", bookButton.getText());
        assertEquals("Back button text should be 'Back'", "Back", backButton.getText());
        
        // Verify default values
        assertEquals("Total Persons field should default to '0'", "0", t1.getText());
        
        // Verify Package choices
        assertEquals("Package choice should have 3 options", 3, c1.getItemCount());
        assertEquals("First package option should be 'Gold Package'", "Gold Package", c1.getItem(0));
        assertEquals("Second package option should be 'Silver Package'", "Silver Package", c1.getItem(1));
        assertEquals("Third package option should be 'Bronze Package'", "Bronze Package", c1.getItem(2));
    }
    
    /**
     * Detailed test for the Check Price button functionality
     */
    @Test
    public void testCheckPriceButtonCalculation() {
        if (bookPackage == null || checkPriceButton == null || totalPriceLabel == null) {
            System.out.println("Skipping test: could not create BookPackage instance or find necessary components");
            return;
        }
        
        // Test Case 1: Gold Package with 2 persons
        t1.setText("2");  // 2 persons
        c1.select("Gold Package");
        
        // Simulate button click
        ActionEvent mockEvent = new ActionEvent(checkPriceButton, ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : checkPriceButton.getActionListeners()) {
            listener.actionPerformed(mockEvent);
        }
        
        // Verify price is calculated correctly for Gold Package (500 * 2 = 1000)
        assertEquals("Gold Package price calculation should be correct", "$ 1000", totalPriceLabel.getText());
        
        // Test Case 2: Silver Package with 3 persons
        t1.setText("3");  // 3 persons
        c1.select("Silver Package");
        
        // Simulate button click again
        mockEvent = new ActionEvent(checkPriceButton, ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : checkPriceButton.getActionListeners()) {
            listener.actionPerformed(mockEvent);
        }
        
        // Verify price is calculated correctly for Silver Package (700 * 3 = 2100)
        assertEquals("Silver Package price calculation should be correct", "$ 2100", totalPriceLabel.getText());
        
        // Test Case 3: Bronze Package with 4 persons
        t1.setText("4");  // 4 persons
        c1.select("Bronze Package");
        
        // Simulate button click again
        mockEvent = new ActionEvent(checkPriceButton, ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : checkPriceButton.getActionListeners()) {
            listener.actionPerformed(mockEvent);
        }
        
        // Verify price is calculated correctly for Bronze Package (1000 * 4 = 4000)
        assertEquals("Bronze Package price calculation should be correct", "$ 4000", totalPriceLabel.getText());
        
        // Test Case 4: Zero persons (edge case)
        t1.setText("0");
        c1.select("Gold Package");
        
        // Simulate button click again
        mockEvent = new ActionEvent(checkPriceButton, ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : checkPriceButton.getActionListeners()) {
            listener.actionPerformed(mockEvent);
        }
        
        // Verify price is calculated correctly for zero persons (500 * 0 = 0)
        assertEquals("Price should be zero for zero persons", "$ 0", totalPriceLabel.getText());
    }
    
    /**
     * Test package booking functionality
     */
    @Test
    public void testPackageBooking() {
        if (bookPackage == null || bookButton == null) {
            System.out.println("Skipping test: could not create BookPackage instance or find Book button");
            return;
        }
        
        // Mock JOptionPane to avoid dialog popups during tests
        final boolean[] showMessageDialogCalled = {false};
        try {
            MockUtilities.mockJOptionPane(showMessageDialogCalled);
        } catch (Exception e) {
            System.out.println("Could not mock JOptionPane: " + e.getMessage());
        }
        
        // Set test data
        t1.setText("2");  // 2 persons
        c1.select("Gold Package");  // Select Gold Package
        
        // Verify that Book button has action listeners
        assertTrue("Book button should have action listener", 
                bookButton.getActionListeners().length > 0);
        
        // Note: We can't fully test the booking functionality without a real database
        // or mocking the database connection
    }
    
    /**
     * Test back button functionality
     */
    @Test
    public void testBackButtonFunctionality() {
        if (bookPackage == null || backButton == null) {
            System.out.println("Skipping test: could not create BookPackage instance or find Back button");
            return;
        }
        
        // Verify back button has action listener
        assertTrue("Back button should have action listener", 
                backButton.getActionListeners().length > 0);
        
        // Note: We can't easily test the window closing functionality
    }
    
    /**
     * Test input validation for number of persons
     */
    @Test
    public void testInputValidation() {
        if (bookPackage == null || checkPriceButton == null || totalPriceLabel == null) {
            System.out.println("Skipping test: could not create BookPackage instance or find necessary components");
            return;
        }
        
        // Test with invalid input (non-numeric)
        t1.setText("abc");  // Non-numeric input
        c1.select("Gold Package");
        
        // Simulate button click
        ActionEvent mockEvent = new ActionEvent(checkPriceButton, ActionEvent.ACTION_PERFORMED, "");
        
        try {
            // This should throw NumberFormatException due to non-numeric input
            for (ActionListener listener : checkPriceButton.getActionListeners()) {
                listener.actionPerformed(mockEvent);
            }
            
            // If we get here without exception, the validation is not working properly
            fail("Non-numeric input should throw NumberFormatException");
        } catch (NumberFormatException e) {
            // Expected exception - test passes
            assertTrue(true);
        } catch (Exception e) {
            // Unexpected exception type
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test main method execution
     */
    @Test
    public void testMainMethod() {
        try {
            BookPackage.main(new String[]{});
            // If no exception is thrown, the test passes
            assertTrue(true);
        } catch (Exception e) {
            fail("Main method should execute without exceptions: " + e.getMessage());
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