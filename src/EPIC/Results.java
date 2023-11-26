package EPIC;

//import Playground.ComputerOrganisationImproved;
//import Playground.DiscreteMathematicsImproved;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Duration;

public class Results  extends questionClass{

    String Mode;
    int correctAnswers;
    int totalQuestions;
    Duration timeElapsed;
    String titleBg;
    String runningBg;
    String resultsBg;
    int borderHex;
    int mainButtonBgHex;
    int alternateButtonBgHex;

    public Results(String Category, String Difficulty, String Question, String Mode, String titleBg, String runningBg, String resultsBg, int borderHex, int mainButtonBgHex, int alternateButtonBgHex, int correctAnswers, int totalQuestions, Duration timeElapsed) {
        super(Category, Difficulty,Mode, Question);
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.timeElapsed = timeElapsed;
        this.titleBg = titleBg;
        this.runningBg = runningBg;
        this.resultsBg = resultsBg;
        this.borderHex = borderHex;
        this.mainButtonBgHex = mainButtonBgHex;
        this.alternateButtonBgHex = alternateButtonBgHex;
        this.Mode = Mode;

    }

    public Results(String Category, String Difficulty, String Mode, String Question, String titleBg, String runningBg, String resultsBg, int borderHex, int mainButtonBgHex, int alternateButtonBgHex, int correctAnswers, int totalQuestions) {
        super(Category, Difficulty, Mode, Question);
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.titleBg = titleBg;
        this.runningBg = runningBg;
        this.resultsBg = resultsBg;
        this.borderHex = borderHex;
        this.mainButtonBgHex = mainButtonBgHex;
        this.alternateButtonBgHex = alternateButtonBgHex;
        this.Mode = Mode;

    }

    public void showResults(JFrame frame, Dimension screenSize, String Category, int correctAnswers, int totalQuestions){
        int screen_width = (int) screenSize.getWidth();
        int screen_height = (int) screenSize.getHeight();
        Font f1 = new Font(Font.DIALOG,  Font.BOLD, 100);
        Font f2 = new Font(Font.DIALOG,  Font.BOLD, 40);
        Font f3 = new Font(Font.DIALOG,  Font.BOLD, 36);


        JLabel results_background = new JLabel();
        results_background.setIcon(new ImageIcon(resultsBg));
        Dimension size = results_background.getPreferredSize();
        results_background.setBounds(0,0,size.width,size.height);

            if (totalQuestions == 6){
                JLabel totalValueTimed = new JLabel(); // prints the question
                totalValueTimed.setText("Out of " + totalQuestions + " questions correct in " + formatDuration(timeElapsed)+ " ");
                totalValueTimed.setFont(f2);
                totalValueTimed.setForeground(new Color(0x000000));
                totalValueTimed.setBounds((screen_width / 2) - 400, (screen_height / 2) + 300, 800, 50);
                frame.add(totalValueTimed);

                JButton Stats = new JButton("Stats");
                Stats.setForeground(new Color(0x0FFFFFF) );
                Stats.setBackground(new Color(mainButtonBgHex));
                Stats.setFont(f3);
                Stats.setBorder(BorderFactory.createLineBorder(new Color (borderHex), 5));
                Stats.setBounds((screen_width/2) + 500, (screen_height/2) - 25, 200, 60);
                frame.add(Stats);

                Stats.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Login user = new Login();
                        String username = user.getUsernameFinal();
                        System.out.println(username);
                        Statistics statsInfo = new Statistics(username);
                        statsInfo.addScoreToStore(correctAnswers);
                        int highscore = statsInfo.findHighScore();
                        double mean = statsInfo.calculateMean();
                        int medianNum = statsInfo.calculateMedian();
                        int median = 0;
                        if (medianNum == -1){
                            median = correctAnswers;
                        }
                        double standardDeviation = statsInfo.calculateStandardDeviation();
                        String message = "User: " + username + "\n Highscore: " + highscore +"\n Mean: " + mean + "\n Median: " + median + "\n Standard deviation: " + standardDeviation;
                        JOptionPane.showMessageDialog(frame,message);


                    };

                });

            }else {
                JLabel totalValue = new JLabel(); // prints the question
                totalValue.setText("Out of " + totalQuestions + " question correct!");
                totalValue.setFont(f2);
                totalValue.setForeground(new Color(0x000000));
                totalValue.setBounds((screen_width / 2) - 250, (screen_height / 2) + 300, 800, 50);
                frame.add(totalValue);
            }



            JLabel correctAmount = new JLabel(); // prints the question
            correctAmount.setText(Integer.toString(correctAnswers));
            correctAmount.setFont(f1);
            correctAmount.setForeground(new Color(0x000000));
            correctAmount.setBounds((screen_width / 2) - 30, (screen_height / 2) - 70, 400, 90);





            JLabel result_statement = new JLabel(); // prints the question
            result_statement.setText("You answered: ");
            result_statement.setFont(f2);
            result_statement.setForeground(new Color(0x000000));
            result_statement.setBounds((screen_width / 2) - 200, (screen_height / 2) - 350, 800, 50);



            frame.add(correctAmount);
            frame.add(result_statement);



            JButton rtrn = new JButton("Return");
            rtrn.setForeground(new Color(0x0FFFFFF) );
            rtrn.setBackground(new Color(mainButtonBgHex));
            rtrn.setFont(f3);
            rtrn.setBorder(BorderFactory.createLineBorder(new Color (borderHex), 5));
            rtrn.setBounds((screen_width/2) - 700, (screen_height/2) - 25, 200, 60);
            frame.add(rtrn);
            frame.add(results_background);


            rtrn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();
                    frame.revalidate();
                    frame.repaint();
                    if (Category == "Computer Science Foundation"){
                        quizSubjectTemplate computerScience = new quizSubjectTemplate("Computer Science Foundation", "null", Mode,"null", "images//compSci//csfTitle.jpg", "images//compSci//compSciBg.jpg", "images//compSci//csfResults.jpg", 0x008000, 0x38B000, 0x06AD0A);
                        computerScience.modes(frame, screenSize);
                    }  if (Category.equals("Discrete Mathematics")) {
                        quizSubjectTemplate DiscreteMaths = new quizSubjectTemplate("Discrete Mathematics", "Difficulty", Mode, "Question", "images//discreteMaths//9.jpg", "images//discreteMaths//10.jpg", "images//discreteMaths//12.jpg", 0x1E6091, 0x168AAD, 0x116F8B);
                        DiscreteMaths.modes(frame, screenSize);
                    } if (Category.equals("Computer Organisation")){
                        quizSubjectTemplate CompOrg = new quizSubjectTemplate("Computer Organisation", "Difficulty", Mode,"Question", "images//compOrg//compOrgMainBg.jpg", "images//compOrg//compOrgBg.jpg", "images//compOrg//resultsBackground.jpg", 0xE305AD, 0x0F039B1,0XD43FAF );
                        CompOrg.modes(frame, screenSize);
                    }


                };

            });





        }  public static String formatDuration(Duration duration) {
        long hours = duration.toHours(); // isolates hours
        long minutes = duration.minusHours(hours).toMinutes(); // isolates minutes
        long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds(); // isolates seconds
        long milliseconds = duration.minusHours(hours).minusMinutes(minutes).minusSeconds(seconds).toMillis(); // isolates milliseconds

        return String.format("%d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds); // formats how many decimal points wanted for each time measurement
    }

    }


