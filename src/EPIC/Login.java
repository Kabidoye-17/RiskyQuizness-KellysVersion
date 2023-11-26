package EPIC;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Login {
  public static String usernameFinal;

    public String getUsernameFinal() {
        return usernameFinal;
    }

    public void setUsernameFinal(String usernameFinal) {

        this.usernameFinal = usernameFinal;
    }

    public static String loggedin;
    public void login(JFrame frame, Dimension screenSize){
        // CSV READER & WRITER LEARNED FROM BRO CODE

        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        JLabel background = new JLabel();
        JLabel rqLogo = new JLabel();
        Font f1 = new Font(Font.DIALOG, Font.PLAIN, 40);
        Font f2 = new Font(Font.DIALOG, Font.BOLD, 40);


        background.setIcon(new ImageIcon("images//login-signin//loginAndSignUp.jpg"));
        Dimension sizeBg = background.getPreferredSize();
        background.setBounds(0,0,sizeBg.width,sizeBg.height);

        JLabel userNameLabel = new JLabel("Username:");
        userNameLabel.setFont(f2);
        userNameLabel.setForeground(new Color(0x0FFFFFF));
        userNameLabel.setBounds((screenWidth/2)-100,(screenHeight/2)-150,500,50);

        JTextField userName = new JTextField(20);
        userName.setFont(f1);
        userName.setBounds((screenWidth/2)-200,(screenHeight/2)- 90,400,60);

        JRadioButton tncs = new JRadioButton("<html>Please accept the terms and conditions of RISKY QUIZNESS™</html>");
        tncs.setBounds((screenWidth/2)-110,(screenHeight/2)+200,220,50);
        tncs.setBackground(new Color(204,213,205));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(0x0FFFFFF));
        passwordLabel.setFont(f2);

        passwordLabel.setBounds((screenWidth/2)-100,(screenHeight/2)-30,500,50);


        JTextField password = new JPasswordField(20);
        password.setFont(f1);
        password.setBounds((screenWidth/2)-200,(screenHeight/2) + 30,400,60);


        JButton submit = new JButton("Login");
        submit.setBounds((screenWidth/2),(screenHeight/2)+130,200,45);
        submit.setFont(f2);
        submit.setForeground(new Color(0xFFFFFF));
        submit.setBackground(new Color (0x0f039b1));

        JButton signUpButton= new JButton("Sign Up");
        signUpButton.setBounds((screenWidth/2)-200,(screenHeight/2)+130,200,45);
        signUpButton.setFont(f2);
        signUpButton.setForeground(new Color(0xFFFFFF));
        signUpButton.setBackground(new Color (0x0f039b1));

        frame.add(rqLogo);frame.add(signUpButton);frame.add(userName);frame.add(password);frame.add(submit);
        frame.add(userNameLabel);frame.add(passwordLabel);frame.add(tncs);
        frame.add(background);

        submit.addActionListener(e -> {
            // This code will be executed when the submit is clicked
            String userDataFile = "logins.csv"; // this file contains the login details  of every password
            BufferedReader reader;
            String line;

            usernameFinal = userName.getText(); // we don't need to  convert this into a hashcode as it wil;l allow usto search for users in the login file
            int passwordFinal = password.getText().hashCode(); // this converts the password into a hashcode - allowing us to compare the stored password with the inputted one

            try {
                reader = new BufferedReader(new FileReader(userDataFile));
                while ((line = reader.readLine())!=null) {
                    String[] row = line.split(",");
                    if (row[0].equals(usernameFinal)){
                        if (Integer.parseInt(row[1]) == passwordFinal) {
                            loggedin = usernameFinal;
                            frame.getContentPane().removeAll();
                            frame.revalidate();// these remove all of the elements on screen so the others can be shown and not overlap
                            frame.repaint();
                            selectionMenu.Main(frame, screenSize);
                        }
                        else {
                            JOptionPane.showMessageDialog(frame,"Incorrect password");
                        }
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        signUpButton.addActionListener((e) -> {
            frame.getContentPane().removeAll();
            frame.revalidate();// these remove all of the elements on screen so the others can be shown and not overlap
            frame.repaint();
            SignUp signUp = new SignUp();
            signUp.signUp(frame, screenSize);
                }
        );
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submit.setBackground(Color.decode("#EB28A7"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submit.setBackground(Color.decode("#E305AD"));
            }
        });
        signUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUpButton.setBackground(Color.decode("#EB28A7"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUpButton.setBackground(Color.decode("#E305AD"));
            }
        });
        frame.setVisible(true);//making the frame visible
    }

}
