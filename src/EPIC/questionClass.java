package EPIC;

import java.io.BufferedReader;
import java.io.FileReader;

public class questionClass {

    // class attributes
    private String Category;
    private String Difficulty;
    private String Question;
    private String Mode;
    private String file = "src//EPIC//epicQuestions.csv";
    private String line = "";
    private BufferedReader reader;
    private String chosenQuestion = "";
    private String[] questionOptions = new String[4];
    private String questionAnswer = "";
    private boolean categoryfound = false;
    private boolean difficultyfound = false;
    private boolean questionfound = false;


    // constructor
    public questionClass(String Category, String Difficulty, String Mode, String Question) {
        this.Category = Category;
        this.Difficulty = Difficulty;
        this.Question = Question;
        this.Mode = Mode;
    }
    // Getter and Setter



    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }


    public void setMode(String mode) {
        Mode = mode;
    }

    public String getChosenQuestion() {
        return chosenQuestion;
    }


    public String[] getQuestionOptions() {
        return questionOptions;
    }


    public String getQuestionAnswer() {
        return questionAnswer;
    }

    // Methods
    public void retrieveQuestion(){
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null){
                //finds the right Category
                if (line.equals(Category)){
                    categoryfound = true;
                }
                //finds the right difficulty under the category
                if (categoryfound && line.equals(Difficulty)){
                    difficultyfound = true;
                }
                // find the right questio
                if (categoryfound && difficultyfound && line.equals(Question)){
                    questionfound = true;
                    // store the questions information
                    chosenQuestion = reader.readLine();
                    line = reader.readLine();
                    questionOptions = line.split("/");
                    questionAnswer = reader.readLine();
                    categoryfound = false;
                    difficultyfound = false;
                    questionfound = false;
                }

            }
        } catch (Exception e){
            System.out.println("whoops");
        }

    }

    }

