package travel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame implements ActionListener {
    
    JButton b1;
    JLabel l1;
    Font f, f1, f2;
    TextArea t1;
    String s;
    
    public About() {
        setLayout(null);
        b1 = new JButton("Exit");
        add(b1);
        b1.setBounds(180, 430, 120, 20);
        b1.addActionListener(this);
        
        f = new Font("RALEWAY", Font.BOLD, 180);
        setFont(f);
        
        s = "                                    About Projects          \n  "
                + "\nGentle Journeys – Insights into Tourism Management"
                + "\n\nGentle Journeys is a simple yet effective tourism management system that helps "
                + "users book hotels, manage packages, and organize travel details with ease. Built using "
                + "Java and MySQL, it aims to make travel planning smooth, efficient, and accessible for all."                ;
        
        t1 = new TextArea(s, 10, 40, Scrollbar.VERTICAL);
        t1.setEditable(false);
        t1.setBounds(20, 100, 450, 300);
        
        add(t1);
        
        f1 = new Font("RALEWAY", Font.BOLD, 16);
        t1.setFont(f1);
        
        l1 = new JLabel("About Project");
        add(l1);
        l1.setBounds(170, 10, 180, 80);
        l1.setForeground(Color.red);
        
        f2 = new Font("RALEWAY", Font.BOLD, 20);
        l1.setFont(f2);
        
        setBounds(700, 220, 500, 550);
        
        setLayout(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
    
    public static void main(String args[]) {
        new About().setVisible(true);
    }
}