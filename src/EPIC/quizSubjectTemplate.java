package EPIC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class quizSubjectTemplate extends questionClass {

    //// Attributes ////
    String Category;
    String Mode;
    int timedResults = 0;
    int leveledResults = 0;
    int randomResults = 0;
    boolean isTimed = false;
    boolean isRandom = false;
    boolean isLeveled = false;
    Instant startTime;
    String titleBg;
    String runningBg;
    String resultsBg;
    int borderHex;
    int mainButtonBgHex;
    int alternateButtonBgHex;
    questionClass questionStore;
    Results resultsInfo;


    //// Constructor ////

    public quizSubjectTemplate(String Category, String Difficulty,String Mode, String Question, String titleBg, String runningBg, String resultsBg, int borderHex, int mainButtonBgHex, int alternateButtonBgHex) {
        super(Category, Difficulty, Mode, Question);
        this.Mode = Mode;
        this.Category = Category;
        this.titleBg = titleBg;
        this.runningBg = runningBg;
        this.resultsBg = resultsBg;
        this.borderHex = borderHex;
        this.mainButtonBgHex = mainButtonBgHex;
        this.alternateButtonBgHex = alternateButtonBgHex;
        this.questionStore =  new questionClass(Category,"null", Mode,"null");
        this.resultsInfo = new Results(Category, "Difficulty", Mode, "Question", titleBg, runningBg, resultsBg, borderHex, mainButtonBgHex, alternateButtonBgHex, 0,0);



    }


    //// Getters and Setters ////

    //// Methods ////
    public void modes(JFrame frame, Dimension screenSize) {

// this is the first screen that appears once the button "computer organisation button is clicked
// this first section is just for aesthetics
        int screen_width = (int) screenSize.getWidth();
        int screen_height = (int) screenSize.getHeight();
        Font f2 = new Font(Font.DIALOG, Font.BOLD, 30); // the font that i have chose


        //background image for design purposes
        JLabel first_background = new JLabel();
        first_background.setIcon(new ImageIcon(titleBg));
        Dimension size = first_background.getPreferredSize();
        first_background.setBounds(0, 0, size.width, size.height);


        JLabel Quiz_mode = new JLabel("Please choose a Quiz mode");

// quiz 1 where user is given one random question to answer
        JButton Random = new JButton();
        Random.setFont(f2);
        Random.setText("Random Question Mode");
        Random.setForeground(new Color(0x0FFFFFF)); //sets text colour
        Random.setBackground(new Color(mainButtonBgHex));// sets background colour
        Random.setBorder(BorderFactory.createLineBorder(new Color(borderHex), 5));
        Random.setBounds((screen_width / 2) - 210, (screen_height / 2) - 110, 400, 50);

        Random.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();/* these remove all the elements on screen so the others can be shown and not
									overlap */
                frame.repaint();
                isRandom = true;
                questionStore.setMode("Random");
                java.util.Random rand = new Random();
                int rand_level = rand.nextInt(3) + 1;
                int rand_question = rand.nextInt(2) + 1;

                switch (rand_level) {
                    case 1:
                        questionStore.setDifficulty("Easy");
                        break;
                    case 2:
                        questionStore.setDifficulty("Intermediate");
                        break;
                    case 3:
                        questionStore.setDifficulty("Difficult");
                        break;
                }
                switch (rand_question) {
                    case 1:
                        questionStore.setQuestion("Q1");
                        break;
                    case 2:
                        questionStore.setQuestion("Q2");
                        break;
                }
                questionStore.retrieveQuestion();
                showFrame(frame, screenSize);

            }

        });
// quiz 2 where the user can choose the difficulty of the questions the want to answer
        JButton increasing_Difficulty = new JButton();
        increasing_Difficulty.setFont(f2);
        increasing_Difficulty.setText("Increasing Difficulty");
        increasing_Difficulty.setForeground(new Color(0x0FFFFFF));
        increasing_Difficulty.setBackground(new Color(mainButtonBgHex));
        increasing_Difficulty.setBorder(BorderFactory.createLineBorder(new Color(borderHex), 5));
        increasing_Difficulty.setBounds((screen_width / 2) - 210, (screen_height / 2) - 40, 400, 50);
        increasing_Difficulty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();// these remove all the elements on screen so the others can be shown and not overlap
                frame.repaint();
                isLeveled = true;
                questionStore.setMode("Increasing Difficulty");
                level_choice(frame, screenSize);

            }

            ;

        });
// quiz 3 where the user is timed and can repeat to beat their highscore

        JButton Timed_mode = new JButton();
        Timed_mode.setFont(f2);
        Timed_mode.setText("Timed mode");
        Timed_mode.setForeground(new Color(0x0FFFFFF));
        Timed_mode.setBackground(new Color(mainButtonBgHex));
        Timed_mode.setBorder(BorderFactory.createLineBorder(new Color(borderHex), 5));
        Timed_mode.setBounds((screen_width / 2) - 160, (screen_height / 2) + 30, 300, 50);
        Timed_mode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();// these remove all  the elements on screen so the others can be shown and not
                // overlap
                frame.repaint();
                isTimed = true;
                questionStore.setMode("Timed");
                startTime = Instant.now();
                questionStore.setDifficulty("Easy");
                questionStore.setQuestion("Q1");
                questionStore.retrieveQuestion();
                showFrame(frame, screenSize);
            }

            ;

        });
        JButton Return = new JButton();
        Return.setFont(f2);
        Return.setText("Return");
        Return.setForeground(new Color(0x0FFFFFF));
        Return.setBackground(new Color(mainButtonBgHex));
        Return.setBorder(BorderFactory.createLineBorder(new Color(borderHex), 5));
        Return.setBounds((screen_width / 2) - 105, (screen_height / 2) + 100, 200, 50);
        Return.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                selectionMenu.Main(frame, screenSize);
            }

            ;
        });

        frame.add(Quiz_mode);
        frame.add(Random);
        frame.add(increasing_Difficulty);
        frame.add(Timed_mode);
        frame.add(Return);
        frame.add(first_background);


    }
    public void showFrame(JFrame frame, Dimension screenSize) {
        int screen_width = (int) screenSize.getWidth();
        int screen_height = (int) screenSize.getHeight();
        Font f1 = new Font(Font.DIALOG, Font.BOLD, 18);

        JLabel general_background = new JLabel();
        general_background.setIcon(new ImageIcon(runningBg));
        Dimension size = general_background.getPreferredSize();
        general_background.setBounds(0, 0, size.width, size.height);

        JLabel q = new JLabel();
        q.setText("Question: " + questionStore.getChosenQuestion());
        q.setBounds((screen_width / 2) - 400, (screen_height / 2) - 180, 1200, 35);
        q.setFont(f1);

        frame.add(q);

        String[] Options = questionStore.getQuestionOptions();
        ButtonGroup ans = new ButtonGroup(); // question answer options
        JRadioButton first = new JRadioButton(Options[0]);
        first.setBounds((screen_width / 2) - 400, (screen_height / 2) - 140, 1000, 35);
        first.setFont(f1);
        first.setForeground(new Color(0x0FFFFFF));
        first.setBackground(new Color(mainButtonBgHex));

        JRadioButton second = new JRadioButton(Options[1]);
        second.setBounds((screen_width / 2) - 400, (screen_height / 2) - 100, 1000, 35);
        second.setFont(f1);
        second.setForeground(new Color(0x0FFFFFF));
        second.setBackground(new Color(alternateButtonBgHex));

        JRadioButton third = new JRadioButton(Options[2]);
        third.setBounds((screen_width / 2) - 400, (screen_height / 2) - 60, 1000, 35);
        third.setFont(f1);
        third.setForeground(new Color(0x0FFFFFF));
        third.setBackground(new Color(mainButtonBgHex));

        JRadioButton fourth = new JRadioButton(Options[3]);
        fourth.setBounds((screen_width / 2) - 400, (screen_height / 2) - 20, 1000, 35);
        fourth.setFont(f1);
        fourth.setForeground(new Color(0x0FFFFFF));
        fourth.setBackground(new Color(alternateButtonBgHex));

        ans.add(first);
        ans.add(second);
        ans.add(third);
        ans.add(fourth);
        first.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                if (isTimed) {
                    if (first.getText().equals(questionStore.getQuestionAnswer())) {
                        timedResults++;
                    } questionMover(frame, screenSize);


                } else if (isRandom) {
                    if (first.getText().equals(questionStore.getQuestionAnswer())) {
                        randomResults++;
                    }
                    // show results for Random
                    resultsInfo.showResults(frame, screenSize, Category, randomResults, 1);


                } else if (isLeveled) {
                    if (first.getText().equals(questionStore.getQuestionAnswer())) {
                        leveledResults++;
                    }
                    if (questionStore.getQuestion().equals("Q1")) {
                        questionStore.setQuestion("Q2");
                        questionStore.retrieveQuestion();
                        showFrame(frame, screenSize);

                    } else if (questionStore.getQuestion().equals("Q2")) {
                        //show results for levels
                        resultsInfo.showResults(frame, screenSize, Category, leveledResults, 2);
                    }
                }


            }

            ;

        });
        second.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                if (isTimed) {
                    if (second.getText().equals(questionStore.getQuestionAnswer())) {
                        timedResults++;
                    }questionMover(frame, screenSize);


                } else if (isRandom) {
                    if (second.getText().equals(questionStore.getQuestionAnswer())) {
                        randomResults++;
                    }
                    // show results for Random
                    resultsInfo.showResults(frame, screenSize, Category, randomResults, 1);

                } else if (isLeveled) {
                    if (second.getText().equals(questionStore.getQuestionAnswer())) {
                        leveledResults++;
                    }
                    if (questionStore.getQuestion().equals("Q1")) {
                        questionStore.setQuestion("Q2");
                        questionStore.retrieveQuestion();
                        showFrame(frame, screenSize);
                    } else if (questionStore.getQuestion().equals("Q2")) {
                        //show results for levels
                        resultsInfo.showResults(frame, screenSize, Category, leveledResults, 2);
                    }
                }

            }


            ;

        });
        third.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                if (isTimed) {
                    if (third.getText().equals(questionStore.getQuestionAnswer())) {
                        timedResults++;
                    }questionMover(frame, screenSize);


                } else if (isRandom) {
                    if (third.getText().equals(questionStore.getQuestionAnswer())) {
                        randomResults++;
                    }// show results for Random
                    resultsInfo.showResults(frame, screenSize, Category, randomResults, 1);


                } else if (isLeveled) {
                    if (third.getText().equals(questionStore.getQuestionAnswer())) {
                        leveledResults++;
                    }
                    if (questionStore.getQuestion().equals("Q1")) {
                        questionStore.setQuestion("Q2");
                        questionStore.retrieveQuestion();
                        showFrame(frame, screenSize);
                    } else if (questionStore.getQuestion().equals("Q2")) {
                        //show results for levels
                        resultsInfo.showResults(frame, screenSize, Category, leveledResults, 2);

                    }
                }

            }


            ;

        });
        fourth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                if (isTimed) {
                    if (fourth.getText().equals(questionStore.getQuestionAnswer())) {
                        timedResults++;
                    }
                    questionMover(frame, screenSize);


                } else if (isRandom) {
                    if (fourth.getText().equals(questionStore.getQuestionAnswer())) {
                        randomResults++;
                    }// show results for Random
                    resultsInfo.showResults(frame, screenSize, Category, randomResults, 1);

                } else if (isLeveled) {
                    if (fourth.getText().equals(questionStore.getQuestionAnswer())) {
                        leveledResults++;
                    }
                    if (questionStore.getQuestion().equals("Q1")) {
                        questionStore.setQuestion("Q2");
                        questionStore.retrieveQuestion();
                        showFrame(frame, screenSize);

                    } else if (questionStore.getQuestion().equals("Q2")) {
                        //show results for levels
                        resultsInfo.showResults(frame, screenSize, Category, leveledResults, 2);
                    }
                }

            }




            ;

        });


        frame.add(first);
        frame.add(second);
        frame.add(third);
        frame.add(fourth);
        frame.add(general_background);

    }
    public void level_choice(JFrame frame, Dimension screenSize) {
        int screen_width = (int) screenSize.getWidth();
        int screen_height = (int) screenSize.getHeight();
        Font f1 = new Font(Font.DIALOG, Font.BOLD, 18);

        JLabel general_background = new JLabel();
        general_background.setIcon(new ImageIcon(runningBg));
        Dimension size = general_background.getPreferredSize();
        general_background.setBounds(0, 0, size.width, size.height);

        JButton easy = new JButton("Easy");
        easy.setForeground(new Color(0x0FFFFFF));
        easy.setBackground(new Color(mainButtonBgHex));
        easy.setFont(f1);
        easy.setBorder(BorderFactory.createLineBorder(new Color(borderHex), 5));
        easy.setBounds((screen_width / 2) - 100, (screen_height / 2) - 140, 200, 35);
        frame.add(easy);

        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                questionStore.setDifficulty("Easy");
                questionStore.setQuestion("Q1");
                questionStore.retrieveQuestion();
                showFrame(frame, screenSize);
            }

            ;

        });

        JButton Intermediate = new JButton("Intermediate");
        Intermediate.setForeground(new Color(0x0FFFFFF));
        Intermediate.setBackground(new Color(mainButtonBgHex));
        Intermediate.setFont(f1);
        Intermediate.setBorder(BorderFactory.createLineBorder(new Color(borderHex), 5));
        Intermediate.setBounds((screen_width / 2) - 100, (screen_height / 2) - 80, 200, 35);
        frame.add(Intermediate);

        Intermediate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                questionStore.setDifficulty("Intermediate");
                questionStore.setQuestion("Q1");
                questionStore.retrieveQuestion();
                showFrame(frame, screenSize);
            }

            ;

        });

        JButton Difficult = new JButton("Difficult");
        Difficult.setForeground(new Color(0x0FFFFFF));
        Difficult.setBackground(new Color(mainButtonBgHex));
        Difficult.setFont(f1);
        Difficult.setBorder(BorderFactory.createLineBorder(new Color(borderHex), 5));
        Difficult.setBounds((screen_width / 2) - 100, (screen_height / 2) - 20, 200, 35);
        frame.add(Difficult);

        Difficult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                questionStore.setDifficulty("Difficult");
                questionStore.setQuestion("Q1");
                questionStore.retrieveQuestion();
                showFrame(frame, screenSize);

            }

            ;

        });
        JButton rtrn = new JButton("Return");
        rtrn.setForeground(new Color(0x0FFFFFF));
        rtrn.setBackground(new Color(mainButtonBgHex));
        rtrn.setFont(f1);
        rtrn.setBorder(BorderFactory.createLineBorder(new Color(borderHex), 5));
        rtrn.setBounds((screen_width / 2) - 100, (screen_height / 2) + 40, 200, 35);
        frame.add(rtrn);


        rtrn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                modes(frame, screenSize);


            }

            ;

        });
        frame.add(general_background);

    }
    public void questionMover(JFrame frame, Dimension screenSize) {
        if (questionStore.getDifficulty().equals("Easy")) {
            if (questionStore.getQuestion().equals("Q1")) {
                questionStore.setQuestion("Q2");
                questionStore.retrieveQuestion();
                showFrame(frame, screenSize);
            } else {
                questionStore.setDifficulty("Intermediate");
                questionStore.setQuestion("Q1");
                questionStore.retrieveQuestion();
                showFrame(frame, screenSize);
            }

        } else if (questionStore.getDifficulty().equals("Intermediate")) {
            if (questionStore.getQuestion().equals("Q1")) {
                questionStore.setQuestion("Q2");
                questionStore.retrieveQuestion();
                showFrame(frame, screenSize);
            } else {
                questionStore.setDifficulty("Difficult");
                questionStore.setQuestion("Q1");
                questionStore.retrieveQuestion();
                showFrame(frame, screenSize);
            }

        } else if (questionStore.getDifficulty().equals("Difficult")) {
            if (questionStore.getQuestion().equals("Q1")) {
                questionStore.setQuestion("Q2");
                questionStore.retrieveQuestion();
                showFrame(frame, screenSize);
            } else {
                isTimed = false;
                establishTimer(frame, screenSize);
            }
        }
    }
    public void establishTimer (JFrame frame, Dimension screenSize){
        Instant endTime = Instant.now(); // stops the stopwatch
        Duration timeElapsed = Duration.between(startTime, endTime);

        Results timed = new Results( Category, "Difficulty", Mode,"Question",titleBg, runningBg, resultsBg, borderHex, mainButtonBgHex, alternateButtonBgHex, 6, 6, timeElapsed);
        timed.showResults(frame, screenSize, Category, timedResults, 6);

    }
}
