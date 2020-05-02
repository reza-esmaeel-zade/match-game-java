package mygame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;


public class MyGame extends JFrame {

    static String files[] = {
        "file1.JPG","file1.JPG",
        "file2.JPG","file2.JPG",
        "file3.JPG","file3.JPG",
        "file4.JPG","file4.JPG",
        "file5.JPG","file5.JPG",
        "file6.JPG","file6.JPG"
    };
    static JButton buttons[];
    ImageIcon closedIcon;
    int numButtons;
    int clicknumber;
    boolean match=false;
    ImageIcon icons[];
    Timer myTimer;
    int []numofbutton=new int[2];
    String []showpic=new String[2];

    public MyGame() {
        // Set the title.

        setTitle("My Game");

        // Specify an action for the close button.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a BorderLayout manager.
        setLayout(new GridLayout(2, files.length));

        closedIcon = new ImageIcon("Hidden.JPG");
         numButtons= files.length;
        buttons = new JButton[numButtons];
        icons = new ImageIcon[numButtons];
        shuffle(files);
        for (int i = 0, j = 0; i < files.length; i++) {
            icons[j] = new ImageIcon(files[i]);
            buttons[j] = new JButton("");
            buttons[j].addActionListener(new ImageButtonListener());
            buttons[j].setIcon(closedIcon);
            add(buttons[j++]);
        }
        
        // Pack and display the window.
        pack();
        setVisible(true);
        
        myTimer = new Timer(1000, new TimerListener());
    }
        private static void shuffle(String[] files){
        int i;
        String s;
        Random rand=new Random();
        for(int j=files.length-1;j>0;j--){
            i=rand.nextInt(j+1);
            s=files[i];
            files[i]=files[j];
            files[j]=s;  
       }
    }
        
    private void setclosedicon(){
        for(int k=0;k<2;k++)
            buttons[numofbutton[k]].setIcon(closedIcon);
    }
    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            setclosedicon();
            myTimer.stop();
        }
    }

    private class ImageButtonListener implements ActionListener {
        

        public void actionPerformed(ActionEvent e) {
            if(clicknumber==0){
                if(!match)
                    setclosedicon();
                myTimer.stop();
                match=false;
                
            }
            
            if(clicknumber<2){
                for (int i = 0; i < numButtons; i++) {
                    if (e.getSource() == buttons[i]) {
                        buttons[i].setIcon(icons[i]);
                        showpic[clicknumber]=files[i];
                        numofbutton[clicknumber]=i;
                        clicknumber++;
                        
                    }
                }
            }
           if(clicknumber==2){
                if(numofbutton[0]==numofbutton[1]||!(showpic[0].equals(showpic[1])))
                    myTimer.start();
                    else{
                            match=true;
                            for(int k=0;k<2;k++)
                                buttons[numofbutton[k]].setEnabled(false);

                        }
            clicknumber=0;    
            }
        }
    }

    public static void main(String[] args) {
        new MyGame();
    }
}
