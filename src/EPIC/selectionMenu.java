package EPIC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class selectionMenu {
    public static void Main(JFrame frame, Dimension screenSize) {
        int screenWidth = (int) screenSize.getWidth();//getting screen dimensions
        int screenHeight = (int) screenSize.getHeight();
        Font f2 = new Font(Font.DIALOG, Font.BOLD, 30); // the font kelly chose

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("images//selectionBg.jpg"));

        Dimension size = background.getPreferredSize();
        background.setBounds(0,0,size.width,size.height);

        JButton returnButton = new JButton("Return");
        returnButton.setFont(f2);
        returnButton.setForeground(new Color (0x0ffffff));
        returnButton.setBackground(new Color (0x0F039B1));
        returnButton.setBorder(BorderFactory.createLineBorder(new Color (0xE305AD), 5));
        returnButton.setBounds((screenWidth/8) - 90,screenHeight-(screenHeight/2) -30,200,50);

        JButton foundationsOfCompSci = new JButton(); // declaring a new button of the name foundationsOfCompSci
        foundationsOfCompSci.setText("Foundations of Computer Science");
        foundationsOfCompSci.setFont(f2); // added the font
        foundationsOfCompSci.setForeground(new Color (0x0ffffff));
        foundationsOfCompSci.setBackground(new Color (0x38b000));
        foundationsOfCompSci.setBorder(BorderFactory.createLineBorder(new Color (0x008000), 5));
        foundationsOfCompSci.setBounds((screenWidth/2)-280,(screenHeight/2)-310,600,40);// setting location for element on frame
        //foundationsOfCompSci.setBackground(Color.decode("#38b000"));

        JButton discreteMaths = new JButton();
        discreteMaths.setFont(f2);
        discreteMaths.setForeground(new Color (0x0ffffff));
        discreteMaths.setBackground(new Color (0x0168aad));
        discreteMaths.setBorder(BorderFactory.createLineBorder(new Color (0x1E6091), 5));
        discreteMaths.setBounds((screenWidth/2)-280,(screenHeight/2)+ 250,600,40);
        //discreteMaths.setBackground(Color.decode("#168aad"));
        discreteMaths.setText("Discrete Mathematics");

        JButton compOrg = new JButton();
        compOrg.setFont(f2);
        compOrg.setForeground(new Color (0x0ffffff));
        compOrg.setBackground(new Color (0x0f039b1));
        compOrg.setBorder(BorderFactory.createLineBorder(new Color (0x0e305ad), 5));
        compOrg.setBounds((screenWidth/2)-280,(screenHeight/2) -30,600,45);
        compOrg.setBackground(Color.decode("#f039b1"));
       compOrg.setText("Computer Organisation");

        frame.add(foundationsOfCompSci);frame.add(discreteMaths);frame.add(compOrg);;frame.add(returnButton);frame.add(background);

        foundationsOfCompSci.addActionListener(e -> {
            //JOptionPane.showMessageDialog(frame, "Foundations of Computer Science");
            frame.getContentPane().removeAll();
            frame.revalidate();// these remove all of the elements on screen so the others can be shown and not overlap
            frame.repaint();
            quizSubjectTemplate computerScience = new quizSubjectTemplate("Computer Science Foundation", "null", "null", "null", "images//compSci//csfTitle.jpg", "images//compSci//compSciBg.jpg", "images//compSci//csfResults.jpg", 0x008000, 0x38B000, 0x06AD0A);
            computerScience.modes(frame, screenSize);
        });
        discreteMaths.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.revalidate();// these remove all of the elements on screen so the others can be shown and not overlap
            frame.repaint();
           quizSubjectTemplate DiscreteMaths = new quizSubjectTemplate("Discrete Mathematics", "null","null", "null", "images//discreteMaths//9.jpg", "images//discreteMaths//10.jpg", "images//discreteMaths//12.jpg", 0x1E6091, 0x168AAD, 0x116F8B);
            DiscreteMaths.modes(frame, screenSize);
            // JAMES ADD DISCRETE CODE FUNCTION CALL HERE
        });
        compOrg.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.revalidate();// these remove all of the elements on screen so the others can be shown and not overlap
            frame.repaint();
          quizSubjectTemplate CompOrg = new quizSubjectTemplate("Computer Organisation", "Difficulty", "null", "Question", "images//compOrg//compOrgMainBg.jpg", "images//compOrg//compOrgBg.jpg", "images//compOrg//resultsBackground.jpg", 0xE305AD, 0x0F039B1,0XD43FAF );
            CompOrg.modes(frame, screenSize);

            // KELLY ADD COMPUTER ORGANISATION FUNCTION CALL HERE
        });
        returnButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.revalidate();// these remove all of the elements on screen so the others can be shown and not overlap
            frame.repaint();
            Login login = new Login();
            login.login(frame,screenSize);
        });
        foundationsOfCompSci.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                foundationsOfCompSci.setBackground(Color.decode("#40cb00"));
            }

            public void mouseExited(MouseEvent evt) {
                foundationsOfCompSci.setBackground(Color.decode("#38b000"));
            }
        });
        compOrg.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                compOrg.setBackground(Color.decode("#f361c1"));
            }

            public void mouseExited(MouseEvent evt) {
                compOrg.setBackground(Color.decode("#f039b1"));
            }
        });
        discreteMaths.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                discreteMaths.setBackground(Color.decode("#1fb5e3"));
            }

            public void mouseExited(MouseEvent evt) {
                discreteMaths.setBackground(Color.decode("#168aad"));
            }
        });
        frame.setVisible(true);//making the frame visible
    }
}
