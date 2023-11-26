package EPIC;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SignUp {


    public void signUp(JFrame frame, Dimension screenSize){
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
        userNameLabel.setForeground(new Color(0xffffff));
        userNameLabel.setFont(f2);
        userNameLabel.setBounds((screenWidth/2)-100,(screenHeight/2)-200,500,50);

        JTextField userName = new JTextField(20);
        userName.setFont(f1);
        userName.setBounds((screenWidth/2)-200,(screenHeight/2)-140,400,60);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(0xFFFFFF));
        passwordLabel.setFont(f2);
        passwordLabel.setBounds((screenWidth/2)-100,(screenHeight/2)-80,500,50);

        JTextField password = new JPasswordField(20);
        password.setFont(f1);
        password.setBounds((screenWidth/2)-200,(screenHeight/2)-20,400,60);

        JLabel confirmPwdLabel = new JLabel("Confirm password:");
        confirmPwdLabel.setForeground(new Color(0xffffff));
        confirmPwdLabel.setFont(f2);
        confirmPwdLabel.setBounds((screenWidth/2)-200,(screenHeight/2)+40,500,50);

        JTextField confirmPassword = new JPasswordField(20);
        confirmPassword.setFont(f1);
        confirmPassword.setBounds((screenWidth/2)-200,(screenHeight/2)+100,400,60);



        JButton signUp = new JButton("Sign Up");
        signUp.setFont(f2);
        signUp.setForeground(new Color(0xffffff));
        signUp.setBounds((screenWidth/2),(screenHeight/2)+200,200,45);
        signUp.setBackground(new Color(0x0f039b1));

        JButton loginButton = new JButton("login");
        loginButton.setFont(f2);
        loginButton.setForeground(new Color(0xffffff));
        loginButton.setBounds((screenWidth/2)-200,(screenHeight/2)+200,200,45);
        loginButton.setBackground(new Color(0x0f039b1));

        frame.add(rqLogo);frame.add(signUp);frame.add(loginButton);frame.add(userName);frame.add(password);
        frame.add(userNameLabel);frame.add(confirmPwdLabel);frame.add(passwordLabel);
        frame.add(background);frame.add(confirmPassword);


        signUp.addActionListener(e -> {
            String userDataFile = "logins.csv"; // this file contains the login details  of every password
            BufferedReader reader;
            String line;
            boolean usernameExists = false;
            int numberChecker =  0;

            String usernameFinal = userName.getText(); // we don't need to  convert this into a hashcode as it wil;l allow usto search for users in the login file
            try {
                reader = new BufferedReader(new FileReader(userDataFile));
                while ((line = reader.readLine())!=null) {
                    String[] row = line.split(",");
                    if (row[0].equals(usernameFinal)){// so the user cannot create another account under the same username
                        usernameExists = true;
                    }
                }
                reader.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(usernameExists) {
                JOptionPane.showMessageDialog(frame, "Sorry that username already exists!");
            }
            else {
                if (password.getText().equals(confirmPassword.getText())) {
                    if ((password.getText()).length() < 8) {
                        //// added in password length checker
                        JOptionPane.showMessageDialog(frame, "Sorry, your password must be atleast 8 characters long!");
                    } else {
                        //// added in a number checker
                        for (int i = 0; i < (password.getText()).length(); i++) {
                            int character = (password.getText()).charAt(i);
                            if (character >= 48 && character <= 57) {
                                numberChecker++;
                            }
                        }
                        if (numberChecker == 0) {
                            JOptionPane.showMessageDialog(frame, "Sorry, your password must include at least one number!");
                        } else {
                            try {//source: https://www.youtube.com/watch?v=epDEG6YstSU
                                BufferedWriter writer = new BufferedWriter(new FileWriter(userDataFile, true));
                                writer.write("\n" + usernameFinal + "," + password.getText().hashCode() );
                                writer.close();
                                formatScores(usernameFinal);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }


                            JOptionPane.showMessageDialog(frame, "You have successfully signed up");
                            frame.getContentPane().removeAll();
                            frame.revalidate();// these remove all of the elements on screen so the others can be shown and not overlap
                            frame.repaint();
                            Login login = new Login();
                            login.login(frame,screenSize);
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(frame,"Your passwords do not match, please try again!");
                }
            }


        });
        loginButton.addActionListener((e)-> {
            frame.getContentPane().removeAll();
            frame.revalidate();// these remove all of the elements on screen so the others can be shown and not overlap
            frame.repaint();
            Login login = new Login();
            login.login(frame,screenSize);
        });
        signUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUp.setBackground(Color.decode("#EB28A7"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUp.setBackground(Color.decode("#E305AD"));
            }
        });
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(Color.decode("#EB28A7"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(Color.decode("#E305AD"));
            }
        });
        frame.setVisible(true);/**making the frame visible**/
    }
    public void formatScores(String username){

        try{
            BufferedReader reader = new BufferedReader(new FileReader("src//EPIC//Scores.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("src//EPIC//ScoresTemp.txt", true));
            writer.append("\n");
            writer.write(username);
            writer.newLine();
            writer.write("scores");
            writer.close();

        } catch(IOException e){
            System.out.println("Error handling files");
        }
        try (BufferedReader tempReader = new BufferedReader(new FileReader("src//EPIC//ScoresTemp.txt"));
             BufferedWriter mainWriter = new BufferedWriter(new FileWriter("src//EPIC//Scores.txt"))) {

            String line;
            while ((line = tempReader.readLine()) != null) {
                mainWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
