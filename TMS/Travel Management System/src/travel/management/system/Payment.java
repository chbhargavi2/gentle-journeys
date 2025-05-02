//PAYMENT.JAVA
package travel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Payment extends JFrame {
    
    // Card detail fields
    private JTextField cardNumberField;
    private JTextField nameOnCardField;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JPasswordField cvvField;
    
    public Payment() {
        setLayout(null);
        setBounds(400, 220, 800, 600);
        
        JLabel label = new JLabel("Payment Details");
        label.setFont(new Font("Raleway", Font.BOLD, 40));
        label.setBounds(50, 20, 350, 45);
        add(label);
        
        // Create a panel for the card details
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBounds(50, 80, 400, 250);
        cardPanel.setBorder(new LineBorder(Color.GRAY, 1));
        cardPanel.setBackground(Color.WHITE);
        add(cardPanel);
        
        // Card Number
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        cardNumberLabel.setBounds(20, 20, 150, 30);
        cardPanel.add(cardNumberLabel);
        
        cardNumberField = new JTextField();
        cardNumberField.setBounds(170, 20, 200, 30);
        cardPanel.add(cardNumberField);
        
        // Name on Card
        JLabel nameLabel = new JLabel("Name on Card:");
        nameLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        nameLabel.setBounds(20, 60, 150, 30);
        cardPanel.add(nameLabel);
        
        nameOnCardField = new JTextField();
        nameOnCardField.setBounds(170, 60, 200, 30);
        cardPanel.add(nameOnCardField);
        
        // Expiration Date
        JLabel expiryLabel = new JLabel("Expiry Date:");
        expiryLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        expiryLabel.setBounds(20, 100, 150, 30);
        cardPanel.add(expiryLabel);
        
        // Month dropdown
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        monthComboBox = new JComboBox<>(months);
        monthComboBox.setBounds(170, 100, 70, 30);
        cardPanel.add(monthComboBox);
        
        JLabel slashLabel = new JLabel("/");
        slashLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        slashLabel.setBounds(245, 100, 10, 30);
        cardPanel.add(slashLabel);
        
        // Year dropdown
        String[] years = {"2025", "2026", "2027", "2028", "2029", "2030"};
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setBounds(260, 100, 80, 30);
        cardPanel.add(yearComboBox);
        
        // CVV
        JLabel cvvLabel = new JLabel("CVV:");
        cvvLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        cvvLabel.setBounds(20, 140, 150, 30);
        cardPanel.add(cvvLabel);
        
        cvvField = new JPasswordField();
        cvvField.setBounds(170, 140, 70, 30);
        cardPanel.add(cvvField);
        
        // Payment method options
        JLabel methodLabel = new JLabel("Payment Method:");
        methodLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        methodLabel.setBounds(20, 180, 150, 30);
        cardPanel.add(methodLabel);
        
        JRadioButton creditRadio = new JRadioButton("Credit Card");
        creditRadio.setBounds(170, 180, 100, 30);
        creditRadio.setBackground(Color.WHITE);
        creditRadio.setSelected(true);
        cardPanel.add(creditRadio);
        
        JRadioButton debitRadio = new JRadioButton("Debit Card");
        debitRadio.setBounds(280, 180, 100, 30);
        debitRadio.setBackground(Color.WHITE);
        cardPanel.add(debitRadio);
        
        ButtonGroup cardTypeGroup = new ButtonGroup();
        cardTypeGroup.add(creditRadio);
        cardTypeGroup.add(debitRadio);
        
        // // Paytm Image
        // ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/paytm.jpeg"));
        // Image i8 = i7.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        // ImageIcon i9 = new ImageIcon(i8);
        // JLabel l4 = new JLabel(i9);
        // l4.setBounds(500, 100, 300, 200);
        // add(l4);
        
        // Pay button
        JButton pay = new JButton("Pay");
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Validate inputs
                if(validateCardDetails()) {
                    JOptionPane.showMessageDialog(null, "Payment Processed Successfully!", 
                        "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                }
            }
        });
        pay.setBounds(170, 220, 100, 30);
        pay.setBackground(new Color(0, 102, 204));
        pay.setForeground(Color.BLACK);
        pay.setFont(new Font("Raleway", Font.BOLD, 14));
        cardPanel.add(pay);
        
        // Back button
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        back.setBounds(280, 220, 100, 30);
        back.setFont(new Font("Raleway", Font.BOLD, 14));
        cardPanel.add(back);
        
        // Label for secure payment
        JLabel secureLabel = new JLabel("Secure Payment - Your card details are encrypted");
        secureLabel.setFont(new Font("Raleway", Font.PLAIN, 12));
        secureLabel.setForeground(new Color(0, 102, 0));
        secureLabel.setBounds(50, 340, 300, 20);
        add(secureLabel);
        
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    
    // Method to validate card details
    private boolean validateCardDetails() {
        String cardNumber = cardNumberField.getText();
        String nameOnCard = nameOnCardField.getText();
        String cvv = new String(cvvField.getPassword());
        
        // Check for empty fields
        if(cardNumber.trim().isEmpty() || nameOnCard.trim().isEmpty() || cvv.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all card details", 
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate card number (should be 16 digits)
        if(!cardNumber.matches("\\d{16}")) {
            JOptionPane.showMessageDialog(this, "Card number must be 16 digits", 
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            cardNumberField.requestFocus();
            return false;
        }
        
        // Validate CVV (should be 3 digits)
        if(!cvv.matches("\\d{3}")) {
            JOptionPane.showMessageDialog(this, "CVV must be 3 digits", 
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            cvvField.requestFocus();
            return false;
        }
        
        // All validations passed
        return true;
    }
    
    public static void main(String[] args) {
        new Payment().setVisible(true);
    }
}