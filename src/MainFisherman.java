import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class MainFisherman extends JFrame implements ActionListener {
    private final JLabel imageLabel;
    public static JTextArea visibleGameText;

    public MainFisherman() {
        // image set up
        setTitle("Graphics Loader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        loadImage("Fisherman.png");

        // Create Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton button1 = new JButton("Get Food");
        button1.addActionListener(this);
        buttonPanel.add(button1);

        JButton button2 = new JButton("Gather Material");
        button2.addActionListener(this);
        buttonPanel.add(button2);

        JButton button3 = new JButton("Rebuild");
        button3.addActionListener(this);
        buttonPanel.add(button3);

        JButton button4 = new JButton("Strengthen Militia");
        button4.addActionListener(this);
        buttonPanel.add(button4);

        add(buttonPanel, BorderLayout.SOUTH);

        // Game Text
        visibleGameText = new JTextArea("Theoretical text");
        visibleGameText.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(visibleGameText);
        scrollPane.setPreferredSize(new Dimension(200, 400));
        add(scrollPane, BorderLayout.EAST);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // button actions
            // the response changes based off progress in game and unlocked mechanics
        Scanner userInput = new Scanner(System.in);
        String command = e.getActionCommand();
        switch (command) {
            case "Get Food":
                boolean secondStr = FriendlyStranger.isSecondStranger();
                boolean thirdStr = FriendlyStranger.isThirdStranger();
                String foodMethod = FriendlyStranger.getFoodMethod(secondStr,thirdStr);

                 if (foodMethod.equals("hunting")){
                     updateGameText("You send a patrol hunting");
                     try {
                         PlayerActions.sendGroupHunting();
                     } catch (InterruptedException ex) {
                         throw new RuntimeException(ex);
                     }

                 } else if(foodMethod.equals("fishing")){
                    updateGameText("You go fishing");
                    try {
                        PlayerActions.goFishing();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                break;
            case "Gather Material":
                if(FriendlyStranger.isSecondStranger()){
                    updateGameText("You send a patrol to gather materials");
                    try {
                        PlayerActions.sendGroupForMaterials();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    updateGameText("You gather materials alone");
                    try {
                        PlayerActions.getMaterialsAlone();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    double randomNum = Math.random();
                    if (Math.random() < .15) {
                        try {
                            RandomEvents.announceButtonAnimalAttack("While gathering materials by yourself ");
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                break;
            case "Rebuild":
                String rebuildWorks = FriendlyStranger.canRebuild(FriendlyStranger.isThirdStranger());
                if(rebuildWorks.equals("no")) {
                    updateGameText("A nice thought");
                } else{
                    try {
                        PlayerActions.rebuildFarm(userInput);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            case "Strengthen Militia":
                boolean armyWorks= FriendlyStranger.isSecondStranger();
                if(!armyWorks) {
                    updateGameText("You and what army?");
                    break;
                } else{
                    try {
                        PlayerActions.rebuildMilitary();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        }
    }

    private void loadImage(String fileName) {
        try {
            BufferedImage image = ImageIO.read(new File(fileName));
            ImageIcon icon = new ImageIcon(image);
            imageLabel.setIcon(icon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    // update text beside image display
    private static void updateGameText(String newtext) {
        visibleGameText.setText(newtext);
    }

   // let me add pauses to print
    public static void delayedPrintln (String newText, int sleepTime) throws InterruptedException {
        System.out.println(newText);
        Thread.sleep(sleepTime);
    }
    public static void main(String[] args) throws InterruptedException {
        //object set up

        RandomEvents pillaged = new RandomEvents(1,3,"A group of foxes pillage the village");
        RandomEvents scuffle = new RandomEvents(1,3,"A patrol gets into a scuffle with some wild beasts");
        RandomEvents runIn = new RandomEvents(1,3,"Some Rogues attack the village");
        RandomEvents fire = new RandomEvents(1,3,"A cooking accident causes a small fire");

      // natural event
        NaturalEvents coldSnap = new NaturalEvents();
        coldSnap.setInvolvedWeatherHazard("coldSnap");
        NaturalEvents drought = new NaturalEvents();
        drought.setInvolvedWeatherHazard("drought");
        NaturalEvents storm = new NaturalEvents();
        storm.setInvolvedWeatherHazard("storm");

       // relevant objects
        RandomEvents randomIncident = new RandomEvents();
        randomIncident.generateProbability();
        RandomEvents trainingAccident = new RandomEvents();
        trainingAccident.generateProbability();


        //beginning text
        delayedPrintln("In the dead of night a village is burned down to the ground by bandits",4300);
        delayedPrintln("Before the attack, the village was a small but lively community",4000);
        System.out.println();

        delayedPrintln("It isn't much of anything now",5400);
        System.out.println();

        delayedPrintln("A man comes out from the rubble of his house",4500);
        System.out.println();
        delayedPrintln("He starts fishing",5000);

        //Load swing GUI
        SwingUtilities.invokeLater(() -> {
            MainFisherman mainFisherman = new MainFisherman();
        });
        //formatting
        System.out.println();
    }
}