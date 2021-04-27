import java.awt.*;
//import static java.awt.PageAttributes.ColorType.COLOR;
import javax.swing.*; 
import java.awt.event.*; 
  
public class Settings extends JFrame { 
  
    // Declaration of object of JRadioButton class. 
    final JRadioButton jRadioButton1;
    final JRadioButton jRadioButton2;
    final JRadioButton jRadioButton3;
    final JRadioButton jRadioButton4;
    final JRadioButton jRadioButton5;
  
    final JRadioButton jRadioSize1;
    final JRadioButton jRadioSize2;
    final JRadioButton jRadioSize3;

    // Declaration of object of JButton class. 
    final JButton jButton;
    final JButton jButton2;
    final JButton musicButton;
  
    // Declaration of object of ButtonGroup class. 
    final ButtonGroup G1;
    final ButtonGroup G2;
    final ButtonGroup G3;
  
    // Declaration of object of  JLabel  class. 
    final JLabel L1;
    final JLabel L2;
    final JLabel L3;
    
    Color temp;
    String tempSize;
    
// --Commented out by Inspection START (4/27/2021 1:28 PM):
//    public Color getColor(){
//        return temp;
//    }
// --Commented out by Inspection STOP (4/27/2021 1:28 PM)

    // Constructor of Settings class. 
    public Settings(Color color, String size) {
        temp = color;
        tempSize = size;
        
        this.setLayout(null);   
        //Color
        jRadioButton1 = new JRadioButton();   
        jRadioButton2 = new JRadioButton(); 
        jRadioButton3 = new JRadioButton(); 
        jRadioButton4 = new JRadioButton(); 
        jRadioButton5 = new JRadioButton(); 
        
        jButton = new JButton("Save");    
        G1 = new ButtonGroup();  
        L1 = new JLabel("Color:"); 
  
        jRadioButton1.setText("Red");  
        jRadioButton2.setText("Blue");
        jRadioButton3.setText("Black");
        jRadioButton4.setText("Green"); 
        jRadioButton5.setText("Yellow"); 

        jRadioButton1.setBounds(120, 30, 120, 50);   
        jRadioButton2.setBounds(250, 30, 80, 50);
        jRadioButton3.setBounds(380, 30, 80, 50); 
        jRadioButton4.setBounds(510, 30, 80, 50); 
        jRadioButton5.setBounds(640, 30, 80, 50); 
          
        jButton.setBounds(125, 90, 80, 30); 
  
        L1.setBounds(20, 30, 150, 50); 

        this.add(jRadioButton1); 
        this.add(jRadioButton2); 
        this.add(jRadioButton3); 
        this.add(jRadioButton4); 
        this.add(jRadioButton5); 
        this.add(jButton); 
        this.add(L1); 
  
        // Adding "jRadioButton1" and "jRadioButton3" in a Button Group "G2". 
        G1.add(jRadioButton1); 
        G1.add(jRadioButton2); 
        G1.add(jRadioButton3);
        G1.add(jRadioButton4);
        G1.add(jRadioButton5);
        
        //Size
        jRadioSize1 = new JRadioButton();   
        jRadioSize2 = new JRadioButton(); 
        jRadioSize3 = new JRadioButton(); 
        
        jButton2 = new JButton("Save");    
        G2 = new ButtonGroup();  
        L2 = new JLabel("Size:"); 
  
        jRadioSize1.setText("Small");  
        jRadioSize2.setText("Medium");
        jRadioSize3.setText("Large");
        
        jRadioSize1.setBounds(120, 180, 120, 50);   
        jRadioSize2.setBounds(250, 180, 80, 50);
        jRadioSize3.setBounds(380, 180, 80, 50); 

        jButton2.setBounds(125, 240, 80, 30); 
  
        L2.setBounds(20, 180, 150, 50);
        
        this.add(jRadioSize1);
        this.add(jRadioSize2);
        this.add(jRadioSize3);
        this.add(jButton2);
        this.add(L2);
        
        G2.add(jRadioSize1);
        G2.add(jRadioSize2);
        G2.add(jRadioSize3);

        // Add a music button to settings menu
        ImageIcon power = new ImageIcon("power.png"); // To be used to add power icon to button
        musicButton = new JButton(power);

        G3 = new ButtonGroup();
        L3 = new JLabel("Music:");

        musicButton.setBounds(120, 310, 100, 100);
        L3.setBounds(20, 330, 150, 50);

        this.add(musicButton);
        this.add(L3);
        G3.add(musicButton);        

        // Adding Listener to JButton. 
        jButton.addActionListener(e -> {
            // Declaration of String class Objects.
            String qual = " ";

            // If condition to check if jRadioButton2 is selected.
            if (jRadioButton1.isSelected()) {
                qual = "Red saved";
                temp = Color.red;
            }
            else if (jRadioButton2.isSelected()) {
                qual = "Blue saved";
                temp = Color.blue;
            }
            else if (jRadioButton3.isSelected()) {
                qual = "Black saved";
                temp = Color.black;
            }
            else if (jRadioButton4.isSelected()) {
                qual = "Green saved";
                temp = Color.green;
            }
            else if (jRadioButton5.isSelected()) {
                qual = "Yellow saved";
                temp = Color.yellow;
            }
            else {
                qual = "NO Button selected";
            }

            // MessageDialog to show information selected radion buttons.
            JOptionPane.showMessageDialog(Settings.this, qual);
        });

        jButton2.addActionListener(e -> {
            // Declaration of String class Objects.
            String qual = " ";

            // If condition to check if jRadioButton2 is selected.
            if (jRadioSize1.isSelected()) {
                qual = "Small saved";
                tempSize = "Small";
            }
            else if (jRadioSize2.isSelected()) {
                qual = "Medium saved";
                tempSize = "Medium";
            }
            else if (jRadioSize3.isSelected()) {
                qual = "Large saved";
                tempSize = "Large";
            }
            else {
                qual = "NO Button selected";
            }

            // MessageDialog to show information selected radion buttons.
            JOptionPane.showMessageDialog(Settings.this, qual);
        });

        // Lambda function for musicButton
        musicButton.addActionListener(e -> {
            // If music is currently playing, then clicking button will pause music
            if (Music.musicPlaying)
                Music.pauseMusic();
            else
                Music.resumeMusic();
        });
    } 
} 
