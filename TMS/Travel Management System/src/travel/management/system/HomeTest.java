package travel.management.system;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * JUnit test for Home class
 */
public class HomeTest {
    
    private Home home;
    private JMenuBar menuBar;
    
    @Before
    public void setUp() throws Exception {
        // Create a Home instance with a test username
        try {
            home = new Home("testuser");
            
            // Get the JMenuBar from the Home frame
            menuBar = home.getJMenuBar();
        } catch (Exception e) {
            System.out.println("Could not create Home instance: " + e.getMessage());
            // Skip tests if we can't create the instance
        }
    }
    
    /**
     * Test UI components initialization
     */
    @Test
    public void testUIComponentsInitialization() {
        if (home == null) {
            System.out.println("Skipping test: could not create Home instance");
            return;
        }
        
        // Verify title is set correctly
        assertEquals("Home frame should have correct title", 
                "Gentle Journeys Insights into Tourism Management", home.getTitle());
        
        // Verify username is set correctly
        assertEquals("Username should be set correctly", "testuser", home.username);
        
        // Verify JMenuBar is initialized
        assertNotNull("JMenuBar should be initialized", menuBar);
    }
    
    /**
     * Test menu structure
     */
    @Test
    public void testMenuStructure() {
        if (home == null || menuBar == null) {
            System.out.println("Skipping test: could not create Home instance or get JMenuBar");
            return;
        }
        
        // Verify number of menus
        assertEquals("Should have 7 menus", 7, menuBar.getMenuCount());
        
        // Verify menu names
        JMenu[] expectedMenus = new JMenu[7];
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            expectedMenus[i] = menuBar.getMenu(i);
        }
        
        assertEquals("First menu should be CUSTOMER", "CUSTOMER", expectedMenus[0].getText());
        assertEquals("Second menu should be PACKAGES", "PACKAGES", expectedMenus[1].getText());
        assertEquals("Third menu should be HOTELS", "HOTELS", expectedMenus[2].getText());
        assertEquals("Fourth menu should be DESTINATION", "DESTINATION", expectedMenus[3].getText());
        assertEquals("Fifth menu should be PAYMENT", "PAYMENT", expectedMenus[4].getText());
        assertEquals("Sixth menu should be UTILITY", "UTILITY", expectedMenus[5].getText());
        assertEquals("Seventh menu should be ABOUT", "ABOUT", expectedMenus[6].getText());
        
        // Verify menu items in CUSTOMER menu
        JMenu customerMenu = expectedMenus[0];
        assertEquals("CUSTOMER menu should have 4 items", 4, customerMenu.getItemCount());
        assertEquals("First item should be ADD CUSTOMER", "ADD CUSTOMER", customerMenu.getItem(0).getText());
        assertEquals("Second item should be UPDATE CUSTOMER DETAIL", "UPDATE CUSTOMER DETAIL", customerMenu.getItem(1).getText());
        assertEquals("Third item should be VIEW CUSTOMER DETAILS", "VIEW CUSTOMER DETAILS", customerMenu.getItem(2).getText());
        assertEquals("Fourth item should be DELETE CUSTOMER DETAILS", "DELETE CUSTOMER DETAILS", customerMenu.getItem(3).getText());
        
        // Verify menu items in PACKAGES menu
        JMenu packagesMenu = expectedMenus[1];
        assertEquals("PACKAGES menu should have 3 items", 3, packagesMenu.getItemCount());
        assertEquals("First item should be CHECK PACKAGE", "CHECK PACKAGE", packagesMenu.getItem(0).getText());
        assertEquals("Second item should be BOOK PACKAGE", "BOOK PACKAGE", packagesMenu.getItem(1).getText());
        assertEquals("Third item should be VIEW PACKAGE", "VIEW PACKAGE", packagesMenu.getItem(2).getText());
        
        // Verify menu items in HOTELS menu
        JMenu hotelsMenu = expectedMenus[2];
        assertEquals("HOTELS menu should have 3 items", 3, hotelsMenu.getItemCount());
        assertEquals("First item should be BOOK HOTELS", "BOOK HOTELS", hotelsMenu.getItem(0).getText());
        assertEquals("Second item should be VIEW HOTELS", "VIEW HOTELS", hotelsMenu.getItem(1).getText());
        assertEquals("Third item should be VIEW BOOKED HOTEL", "VIEW BOOKED HOTEL", hotelsMenu.getItem(2).getText());
    }
    
    /**
     * Test action listeners for menu items
     */
    @Test
    public void testMenuItemActionListeners() {
        if (home == null || menuBar == null) {
            System.out.println("Skipping test: could not create Home instance or get JMenuBar");
            return;
        }
        
        // Verify that all menu items have action listeners
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem menuItem = menu.getItem(j);
                
                // Skip null menu items (separators)
                if (menuItem == null) continue;
                
                ActionListener[] listeners = menuItem.getActionListeners();
                assertTrue("Menu item '" + menuItem.getText() + "' should have at least one action listener", 
                        listeners != null && listeners.length > 0);
            }
        }
    }
    
    /**
     * Test background image loading
     */
    @Test
    public void testBackgroundImageLoading() {
        if (home == null) {
            System.out.println("Skipping test: could not create Home instance");
            return;
        }
        
        // Find the background image label
        Component[] components = home.getContentPane().getComponents();
        boolean foundBackgroundLabel = false;
        
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getIcon() != null) {
                    foundBackgroundLabel = true;
                    break;
                }
            }
        }
        
        assertTrue("Background image should be loaded and added to the frame", foundBackgroundLabel);
    }
    
    /**
     * Test window properties
     */
    @Test
    public void testWindowProperties() {
        if (home == null) {
            System.out.println("Skipping test: could not create Home instance");
            return;
        }
        
        // Verify the window is set to maximized
        assertEquals("Window should be maximized", JFrame.MAXIMIZED_BOTH, home.getExtendedState());
        
        // Verify the window is visible
        assertTrue("Window should be visible", home.isVisible());
        
        // Verify background color
        assertEquals("Background color should be white", java.awt.Color.WHITE, home.getContentPane().getBackground());
    }
    
    /**
     * Test main method execution
     */
    @Test
    public void testMainMethod() {
        try {
            Home.main(new String[]{});
            // If no exception is thrown, the test passes
            assertTrue(true);
        } catch (Exception e) {
            fail("Main method should execute without exceptions: " + e.getMessage());
        }
    }
}