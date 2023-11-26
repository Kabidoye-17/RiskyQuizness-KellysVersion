package EPIC;

import java.io.*;

public class Statistics {

    String username;
    public Statistics(String username) {
        this.username = username;
    }

    public void addScoreToStore(int score){
        try (BufferedReader reader = new BufferedReader(new FileReader("src//EPIC//Scores.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("src//EPIC//ScoresTemp.txt"))) {

            boolean usernameFound = false;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                if (words.length > 0 && words[0].equals(username)) {
                    usernameFound = true;
                }
                if (usernameFound && words.length > 0 && words[0].equals("scores")) {
                    //rewrites line with score added at the end
                    line += "," + score;
                    usernameFound = false;
                }
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try (BufferedReader tempReader = new BufferedReader(new FileReader("src//EPIC//ScoresTemp.txt"));
             BufferedWriter mainWriter = new BufferedWriter(new FileWriter("src//EPIC//Scores.txt"))) {
            // updates the main scores file with the new info in scores temp file
            // rewrites everyline
            String line;
            while ((line = tempReader.readLine()) != null) {
                mainWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }}
    public  int findHighScore(){
        int highscore = 0;
        int score = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src//EPIC//Scores.txt"));
            String line = "";
            boolean usernameFound = false;
            while ((line = reader.readLine()) != null){
                String[] words = line.split(",");
                if (words[0].equals(username)){
                    usernameFound = true;
                }
                if ((usernameFound) && words[0].equals("scores")){
                    for(int i = 1; i < words.length; i++){
                        score = Integer.parseInt(words[i]);
                        if (score > highscore){
                            highscore = score;
                        }

                    }
                }
            }
        } catch (IOException e){
            System.out.println("Error handling files");
        }
        return highscore;
    }
    public  double calculateMean(){
        double mean = 0;
        int counter = 0;
        int length = 0;
        int score = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src//EPIC//Scores.txt"));
            String line = "";
            boolean usernameFound = false;
            while ((line = reader.readLine()) != null){
                String[] words = line.split(",");
                if (words[0].equals(username)){
                    usernameFound = true;
                }
                if ((usernameFound) && words[0].equals("scores")){
                    for(int i = 1; i < words.length; i++){
                        score = Integer.parseInt(words[i]);
                        counter += score;
                        length = words.length;

                    }
                }
            }
        } catch (IOException e){
            System.out.println("Error handling files");
        }
        mean = (double) counter /length-1;
        return mean;

    }
    public  int calculateMedian() {
        int median = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src//EPIC//Scores.txt"));
            String line = "";
            boolean usernameFound = false;

            while ((line = reader.readLine()) != null) {
                //finds current username
                String[] words = line.split(",");
                if (words[0].equals(username)) {
                    usernameFound = true;
                }
                if ((usernameFound) && words[0].equals("scores")) {
                    int[] tempScoreList = new int[words.length - 1];
                    for (int i = 1; i < words.length - 1; i++) {
                        tempScoreList[i - 1] = Integer.valueOf(words[i]);
                    }
                    // sort scores in ascending order
                    for (int i = 0; i < tempScoreList.length - 1; i++) {
                        for (int j = 0; j < tempScoreList.length - i - 1; j++) {
                            if (tempScoreList[j] > tempScoreList[j + 1]) {
                                // Swap arr[j] and arr[j+1]
                                int temp = tempScoreList[j];
                                tempScoreList[j] = tempScoreList[j + 1];
                                tempScoreList[j + 1] = temp;
                            }
                        }
                    }
                    // get the median
                    int length = tempScoreList.length;
                    if (length == 0) {
                        return -1;
                    }
                    int midpoint = length / 2;
                    median = tempScoreList[midpoint];


                }
            }

        } catch (IOException e) {
            System.out.println("Error handling files");
        }

        return  median;
    }
    public  double calculateStandardDeviation(){
        double mean = calculateMean();
        double squareDifferenceMean = 0;
        int score = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src//EPIC//Scores.txt"));
            String line = "";
            boolean usernameFound = false;
            while ((line = reader.readLine()) != null){
                //finds current username
                String[] words = line.split(",");
                if (words[0].equals(username)){
                    usernameFound = true;
                }
                if ((usernameFound) && words[0].equals("scores")){
                    for(int i = 1; i < words.length; i++){
                        score = (Integer.valueOf(words[i]));
                        /// calculates standard dec
                        squareDifferenceMean += (score - mean) * (score - mean);

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error handling files");
        }
        return Math.sqrt(squareDifferenceMean);
    }

}
