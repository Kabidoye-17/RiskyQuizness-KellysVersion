package EPIC;

import javax.swing.*;
import java.awt.*;

// DEPENDENCIES:
//json-simple-1.1.1.jar


public class Main extends Thread{
    public static void main(String[] args){
        Main thread = new Main();
        thread.start();
        // This is main do not create code here, create different functions(methods)
        JFrame frame=new JFrame("RISKY QUIZNESS™");//creating instance of JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("images\\RQ-Icon.png").getImage());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(new Color(204,213,205));
        frame.setLayout(null);//using no layout managers
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // this gets the dimension of the current frame, source: https://stackoverflow.com/questions/6593322/why-does-the-jframe-setsize-method-not-set-the-size-correctly
        Login login = new Login();
        login.login(frame,screenSize);
        //selectionMenu.Main(frame,screenSize);
        frame.setResizable(true);

    }
    public void run() {
        MusicPlayer.playAudio("music\\Puzzle-Dreams-3.wav");
    }
}