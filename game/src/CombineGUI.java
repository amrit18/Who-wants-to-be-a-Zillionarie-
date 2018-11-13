/*
 * @author - Amrit Sarai
 * @uniCourse - Computing Science
 * @uniNumber - u1755231
 * This is the GUI class.
 */


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CombineGUI {
    //Instance Variables
    private JPanel addingPlayers;
    private JTextField playerNameText;
    private JButton addButton;
    private JList<Player> allPlayer;
    private JButton nextPlayerButton;
    private JPanel homePage;
    private JLabel gameLogo;
    private JButton startingButton;
    private JButton instructionButton;
    private JPanel mainPanel;
    private JPanel categoryPage;
    private JButton TVButton;
    private JButton generalButton;
    private JButton filmButton;
    private JLabel logoGame;
    private JPanel gamePage;
    private JLabel gameQuestionLabel;
    private JLabel gameCategoryLabel;
    private JButton answer1Button;
    private JButton answer2Button;
    private JButton answer3Button;
    private JButton answer4Button;
    private JLabel playerNameTxt;
    private JLabel amountWonTxt;
    private JLabel correctAnswerTxt;
    private JButton askAudienceButton;
    private JButton giveClueButton;
    private JButton a5050Button;
    private JButton nextButton;
    private JPanel askAudiencePage;
    private JButton okButton;
    private JProgressBar answerABar;
    private JProgressBar answerBBar;
    private JProgressBar answerCBar;
    private JProgressBar answerDBar;
    private JLabel answerA;
    private JLabel answerB;
    private JLabel answerC;
    private JLabel answerD;
    private JLabel askAudience;
    private JPanel winnerPage;
    private JLabel getPlayerName;
    private JButton thankYouButton;
    private JButton removeButton;

    private PlayerList playerList;


    public CombineGUI() throws IOException {
        //Setting up the JList
        playerList = new PlayerList();
        allPlayer.setModel(playerList);
        //Importing the games class
        Game game = new Game();

        //Implmenting the introduction music.
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("startScreenIntroSound.wav").getAbsoluteFile());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        clip.start();
        clip.loop(50000);

        //Implementing the tense music when the player is asked a question
        AudioInputStream tenseMusic = null;
        try {
            tenseMusic = AudioSystem.getAudioInputStream(new File("tenseMusic.wav").getAbsoluteFile());
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Clip tense = null;
        try {
            tense = AudioSystem.getClip();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
        try {
            tense.open(tenseMusic);
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        tense.loop(500000);
        tense.stop();

        //Set visible because this is the startup screen
        homePage.setVisible(true);



        //method which adds the player name
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String playerName = playerNameText.getText();

                if (playerName.equals("")){
                    JOptionPane.showMessageDialog(null, "You need to enter a name!");
                }

                if(playerName.length() > 10){
                   JOptionPane.showMessageDialog(null, "You can't enter more than 10 characters");
                }



                else  {
                    playerList.addPlayer(playerName);
                    game.addPlayer(playerName);
                    Player playerByName = playerList.findPlayerByName(playerName);
                    allPlayer.setSelectedValue(playerByName, true);
                    clearAllTextFields();
                }


            }
        });




        Clip finalClip = clip;
        //Takes you to the adding player screen
        startingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                homePage.setVisible(false);
                addingPlayers.setVisible(true);
            }
        });



        //Gives you instructions for the game.
        instructionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String instructions = "Welcome to Who Wants To Be A Zillionaire \n\n" +
                        "You need to enter a minimum of two player names before selecting a category of questions. \n" +
                        "After entering the game, you must answer 12 multiple choice questions correctly to win \n" +
                        "£1,000,000. \n\n" +
                        "If you get one of the questions wrong, you are removed from the game \n" +
                        "Each player has a choice of three help options. The player can use each help option + \n" +
                        "once in the game.\n\n" +
                        "The help options are: \n\n" +
                        "\u2022 Ask The Audience - Gives you a graphical image of what the audience currently thinks \n"
                        + "is the correct answer. \n\n" +
                        "\u2022 Give a Clue - Give you hint for the answer of the questions. \n\n" +
                        "\u2022 50/50 - Removes two of the wrong answers in the question \n\n";

                JOptionPane.showMessageDialog(null, instructions);

            }
        });


        //Allows you to enter the category page, if there is a minimum of 2 players and maximum of 3
        nextPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getAmountOfPlayers() > 1 && game.getAmountOfPlayers() < 5){
                    categoryPage.setVisible(true);
                    addingPlayers.setVisible(false);

                }


                else{
                    String instructions = "You need to enter a minimum of two players, and you can have a \n" +
                            "a maximum of 4, before you enter the category page. If you only have one player, please add one!";

                    JOptionPane.showMessageDialog(null, instructions);

                }

            }
        });

        Clip finalTense = tense;
        TVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Ends the intro music and starts the tense music.
                finalClip.stop();
                finalTense.start();
                finalTense.loop(50000);
                categoryPage.setVisible(false);
                gamePage.setVisible(true);
                //Gets the questions with the category TV-Shows
                game.chosenCategory("TV-Shows");


                playerNameTxt.setText(game.getPlayerNumber().getPlayerName());
                amountWonTxt.setText(String.valueOf(game.getPlayerNumber().getAmountWon()));
                gameQuestionLabel.setText(game.getGameQuestion());
                nextButton.setEnabled(false);

                int rand = (int) ((Math.random() * 4) + 1);


                //Method which makes the place of the answers random
                if (rand == 1){
                    answer1Button.setText(game.getAnswerA());
                    answer2Button.setText(game.getAnswerC());
                    answer3Button.setText(game.getAnswerD());
                    answer4Button.setText(game.getAnswerB());
                }

                if (rand == 2){
                    answer1Button.setText(game.getAnswerC());
                    answer2Button.setText(game.getAnswerA());
                    answer3Button.setText(game.getAnswerD());
                    answer4Button.setText(game.getAnswerB());
                }

                if (rand == 3){
                    answer1Button.setText(game.getAnswerB());
                    answer2Button.setText(game.getAnswerC());
                    answer3Button.setText(game.getAnswerA());
                    answer4Button.setText(game.getAnswerD());
                }

                if (rand == 4){
                    answer1Button.setText(game.getAnswerD());
                    answer2Button.setText(game.getAnswerB());
                    answer3Button.setText(game.getAnswerC());
                    answer4Button.setText(game.getAnswerA());

                }




                nextButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        finalTense.start();
                        finalTense.loop(5000);
                        int rand = (int) ((Math.random() * 4) + 1);
                        correctAnswerTxt.setText("");
                        answer1Button.setEnabled(true);
                        answer2Button.setEnabled(true);
                        answer3Button.setEnabled(true);
                        answer4Button.setEnabled(true);
                        askAudienceButton.setEnabled(true);
                        giveClueButton.setEnabled(true);
                        a5050Button.setEnabled(true);
                        answer1Button.setVisible(true);
                        answer2Button.setVisible(true);
                        answer3Button.setVisible(true);
                        answer4Button.setVisible(true);
                        nextButton.setEnabled(false);
                        game.gettingCurrentPlayer();
                        game.chosenCategory("TV-Shows");

                        //A check to see if there is any players still in the game.
                        if (game.getPlayer().isEmpty()){
                            String noPlayers = "Our system detects there are no players left in the game. Thank you for playing!";
                            JOptionPane.showMessageDialog(null, noPlayers);
                            System.out.println("There are no players");
                            System.exit(0);
                        }


                        playerNameTxt.setText(game.getPlayerNumber().getPlayerName());
                        amountWonTxt.setText(String.valueOf(game.getPlayerNumber().getAmountWon()));
                        gameQuestionLabel.setText(game.getGameQuestion());



                        if (rand == 1){
                            answer1Button.setText(game.getAnswerA());
                            answer2Button.setText(game.getAnswerC());
                            answer3Button.setText(game.getAnswerD());
                            answer4Button.setText(game.getAnswerB());
                        }

                        if (rand == 2){
                            answer1Button.setText(game.getAnswerC());
                            answer2Button.setText(game.getAnswerA());
                            answer3Button.setText(game.getAnswerD());
                            answer4Button.setText(game.getAnswerB());
                        }

                        if (rand == 3){
                            answer1Button.setText(game.getAnswerB());
                            answer2Button.setText(game.getAnswerC());
                            answer3Button.setText(game.getAnswerA());
                            answer4Button.setText(game.getAnswerD());
                        }

                        if (rand == 4){
                            answer1Button.setText(game.getAnswerD());
                            answer2Button.setText(game.getAnswerB());
                            answer3Button.setText(game.getAnswerC());
                            answer4Button.setText(game.getAnswerA());

                        }

                    }
                });

            }
        });



        answer1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalTense.stop();
                answer1Button.setEnabled(false);
                answer2Button.setEnabled(false);
                answer3Button.setEnabled(false);
                answer4Button.setEnabled(false);
                askAudienceButton.setEnabled(false);
                giveClueButton.setEnabled(false);
                a5050Button.setEnabled(false);
                nextButton.setEnabled(true);
                correctAnswerTxt.setText(game.getAnswerA());
                //A method for if the answer is correct.
                if (answer1Button.getText().equals(game.getAnswerA())){
                    AudioInputStream correctAnswer = null;
                    try {
                        correctAnswer = AudioSystem.getAudioInputStream(new File("correctAnswerSound.wav").getAbsoluteFile());
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Clip clip = null;
                    try {
                        clip = AudioSystem.getClip();
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        clip.open(correctAnswer);
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    clip.start();
                    String answerCorrect = "<html>" +
                            "<body width='400'><h1>Correct Answer!</h1>" +
                            "<p> Well Done, you have just won some money!" ;
                    JOptionPane.showMessageDialog(null, answerCorrect);
                    game.givePlayerMoney();
                    amountWonTxt.setText(String.valueOf(game.getPlayerNumber().getAmountWon()));

                    if (amountWonTxt.getText().equals("1000000")){
                        gamePage.setVisible(false);
                        winnerPage.setVisible(true);
                        getPlayerName.setText(game.getPlayerNumber().getPlayerName());
                        System.out.println("This works!!!");
                    }
                }



                //A method for if the answer is wrong.
                else{
                    AudioInputStream correctAnswer = null;
                    try {
                        correctAnswer = AudioSystem.getAudioInputStream(new File("wrongAnswerSound.wav").getAbsoluteFile());
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Clip clip = null;
                    try {
                        clip = AudioSystem.getClip();
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        clip.open(correctAnswer);
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    clip.start();
                    String answerWrong = "<html>" +
                            "<body width='400'><h1>Wrong Answer!</h1>" +
                            "<p> You have got the question wrong. You have been removed from the game!." ;
                    JOptionPane.showMessageDialog(null, answerWrong);
                    game.removePlayer();

                }

                if (game.getPlayer().isEmpty()){
                    String noPlayers = "No players left in the game. Thank you for playing!";
                    JOptionPane.showMessageDialog(null, noPlayers);
                    System.out.println("There are no players");
                    System.exit(0);
                }

            }
        });



        answer2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalTense.stop();
                answer1Button.setEnabled(false);
                answer2Button.setEnabled(false);
                answer3Button.setEnabled(false);
                answer4Button.setEnabled(false);
                askAudienceButton.setEnabled(false);
                giveClueButton.setEnabled(false);
                a5050Button.setEnabled(false);
                nextButton.setEnabled(true);
                correctAnswerTxt.setText(game.getAnswerA());

                if (answer2Button.getText().equals(game.getAnswerA())){
                    AudioInputStream correctAnswer = null;
                    try {
                        correctAnswer = AudioSystem.getAudioInputStream(new File("correctAnswerSound.wav").getAbsoluteFile());
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Clip clip = null;
                    try {
                        clip = AudioSystem.getClip();
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        clip.open(correctAnswer);
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    clip.start();

                    String answerCorrect = "<html>" +
                            "<body width='400'><h1>Correct Answer!</h1>" +
                            "<p> Well Done, you have just won some money!" ;
                    JOptionPane.showMessageDialog(null, answerCorrect);
                    game.givePlayerMoney();
                    amountWonTxt.setText(String.valueOf(game.getPlayerNumber().getAmountWon()));


                    if (amountWonTxt.getText().equals("1000000")){
                        gamePage.setVisible(false);
                        winnerPage.setVisible(true);
                        getPlayerName.setText(game.getPlayerNumber().getPlayerName());
                    }
                }


                else{
                    AudioInputStream correctAnswer = null;
                    try {
                        correctAnswer = AudioSystem.getAudioInputStream(new File("wrongAnswerSound.wav").getAbsoluteFile());
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Clip clip = null;
                    try {
                        clip = AudioSystem.getClip();
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        clip.open(correctAnswer);
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    clip.start();
                    String answerWrong = "<html>" +
                            "<body width='400'><h1>Wrong Answer!</h1>" +
                            "<p> You have got the question wrong. You have been removed from the game!" ;

                    JOptionPane.showMessageDialog(null, answerWrong);
                    game.removePlayer();

                }

                if (game.getPlayer().isEmpty()){
                    String noPlayers = "No players left in the game. Thank you for playing!";
                    JOptionPane.showMessageDialog(null, noPlayers);
                    System.out.println("There are no players");
                    System.exit(0);
                }



            }
        });


        answer3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalTense.stop();
                answer1Button.setEnabled(false);
                answer2Button.setEnabled(false);
                answer3Button.setEnabled(false);
                answer4Button.setEnabled(false);
                askAudienceButton.setEnabled(false);
                giveClueButton.setEnabled(false);
                a5050Button.setEnabled(false);
                nextButton.setEnabled(true);
                correctAnswerTxt.setText(game.getAnswerA());
                if (answer3Button.getText().equals(game.getAnswerA())){
                    AudioInputStream correctAnswer = null;
                    try {
                        correctAnswer = AudioSystem.getAudioInputStream(new File("correctAnswerSound.wav").getAbsoluteFile());
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Clip clip = null;
                    try {
                        clip = AudioSystem.getClip();
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        clip.open(correctAnswer);
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    clip.start();
                    String answerCorrect = "<html>" +
                            "<body width='400'><h1>Correct Answer!</h1>" +
                            "<p> Well Done, you have just won some money!" ;
                    JOptionPane.showMessageDialog(null, answerCorrect);
                    game.givePlayerMoney();
                    amountWonTxt.setText(String.valueOf(game.getPlayerNumber().getAmountWon()));

                    if (amountWonTxt.getText().equals("1000000")){
                        gamePage.setVisible(false);
                        winnerPage.setVisible(true);
                        getPlayerName.setText(game.getPlayerNumber().getPlayerName());
                    }
                }


                else{
                    AudioInputStream correctAnswer = null;
                    try {
                        correctAnswer = AudioSystem.getAudioInputStream(new File("wrongAnswerSound.wav").getAbsoluteFile());
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Clip clip = null;
                    try {
                        clip = AudioSystem.getClip();
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        clip.open(correctAnswer);
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    clip.start();
                    String answerWrong = "<html>" +
                            "<body width='400'><h1>Wrong Answer!</h1>" +
                            "<p> You have got the question wrong. You have been removed removed from the game!" ;
                    JOptionPane.showMessageDialog(null, answerWrong);
                    game.removePlayer();
                }

                if (game.getPlayer().isEmpty()){
                    String noPlayers = "No players left in the game. Thank you for playing!";
                    JOptionPane.showMessageDialog(null, noPlayers);
                    System.exit(0);
                }

            }
        });


        answer4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalTense.stop();
                answer1Button.setEnabled(false);
                answer2Button.setEnabled(false);
                answer3Button.setEnabled(false);
                answer4Button.setEnabled(false);
                askAudienceButton.setEnabled(false);
                giveClueButton.setEnabled(false);
                a5050Button.setEnabled(false);
                nextButton.setEnabled(true);
                correctAnswerTxt.setText(game.getAnswerA());
                if (answer4Button.getText().equals(game.getAnswerA())){
                    AudioInputStream correctAnswer = null;
                    try {
                        correctAnswer = AudioSystem.getAudioInputStream(new File("correctAnswerSound.wav").getAbsoluteFile());
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Clip clip = null;
                    try {
                        clip = AudioSystem.getClip();
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        clip.open(correctAnswer);
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    clip.start();
                    String answerCorrect = "<html>" +
                            "<body width='400'><h1>Correct Answer!</h1>" +
                            "<p> Well Done, you have just won some money!" ;
                    JOptionPane.showMessageDialog(null, answerCorrect);
                    game.givePlayerMoney();
                    amountWonTxt.setText(String.valueOf(game.getPlayerNumber().getAmountWon()));

                    if (amountWonTxt.getText().equals("1000000")){
                        gamePage.setVisible(false);
                        winnerPage.setVisible(true);
                        getPlayerName.setText(game.getPlayerNumber().getPlayerName());
                    }


                }

                else{
                    AudioInputStream correctAnswer = null;
                    try {
                        correctAnswer = AudioSystem.getAudioInputStream(new File("wrongAnswerSound.wav").getAbsoluteFile());
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Clip clip = null;
                    try {
                        clip = AudioSystem.getClip();
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        clip.open(correctAnswer);
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    clip.start();
                    String answerWrong = "<html>" +
                            "<body width='400'><h1>Wrong Answer!</h1>" +
                            "<p> You have got the question wrong. You have been removed from the game!" ;
                    JOptionPane.showMessageDialog(null, answerWrong);
                    game.removePlayer();

                }

                if (game.getPlayer().isEmpty()){
                    String noPlayers = "No players left in the game. Thank you for playing!";
                    JOptionPane.showMessageDialog(null, noPlayers);
                    System.exit(0);
                }

            }
        });




        //When the player selects the general button
        generalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalClip.stop();
                finalTense.start();
                finalTense.loop(5000);
                game.chosenCategory("General Knowledge");
                categoryPage.setVisible(false);
                gamePage.setVisible(true);

                nextButton.setEnabled(false);
                playerNameTxt.setText(game.getPlayerNumber().getPlayerName());
                amountWonTxt.setText(String.valueOf(game.getPlayerNumber().getAmountWon()));
                gameQuestionLabel.setText(game.getGameQuestion());
                int rand = (int) ((Math.random() * 4) + 1);


                if (rand == 1){
                    answer1Button.setText(game.getAnswerA());
                    answer2Button.setText(game.getAnswerC());
                    answer3Button.setText(game.getAnswerD());
                    answer4Button.setText(game.getAnswerB());
                }

                if (rand == 2){
                    answer1Button.setText(game.getAnswerC());
                    answer2Button.setText(game.getAnswerA());
                    answer3Button.setText(game.getAnswerD());
                    answer4Button.setText(game.getAnswerB());
                }

                if (rand == 3){
                    answer1Button.setText(game.getAnswerB());
                    answer2Button.setText(game.getAnswerC());
                    answer3Button.setText(game.getAnswerA());
                    answer4Button.setText(game.getAnswerD());
                }

                if (rand == 4){
                    answer1Button.setText(game.getAnswerD());
                    answer2Button.setText(game.getAnswerB());
                    answer3Button.setText(game.getAnswerC());
                    answer4Button.setText(game.getAnswerA());

                }


                nextButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int rand = (int) ((Math.random() * 4) + 1);

                        finalTense.start();
                        finalTense.loop(5000);

                        correctAnswerTxt.setText("");
                        answer1Button.setEnabled(true);
                        answer2Button.setEnabled(true);
                        answer3Button.setEnabled(true);
                        answer4Button.setEnabled(true);
                        askAudienceButton.setEnabled(true);
                        giveClueButton.setEnabled(true);
                        a5050Button.setEnabled(true);
                        answer1Button.setVisible(true);
                        answer2Button.setVisible(true);
                        answer3Button.setVisible(true);
                        answer4Button.setVisible(true);
                        nextButton.setEnabled(false);
                        game.gettingCurrentPlayer();
                        game.chosenCategory("General Knowledge");
                        playerNameTxt.setText(game.getPlayerNumber().getPlayerName());
                        amountWonTxt.setText(String.valueOf(game.getPlayerNumber().getAmountWon()));
                        gameQuestionLabel.setText(game.getGameQuestion());

                        if (game.getPlayer().isEmpty()){
                            String noPlayers = "Our system detects there are no players left in the game. Thank you for playing!";
                            JOptionPane.showMessageDialog(null, noPlayers);
                            System.exit(0);
                        }


                        if (rand == 1){
                            answer1Button.setText(game.getAnswerA());
                            answer2Button.setText(game.getAnswerC());
                            answer3Button.setText(game.getAnswerD());
                            answer4Button.setText(game.getAnswerB());
                        }

                        if (rand == 2){
                            answer1Button.setText(game.getAnswerC());
                            answer2Button.setText(game.getAnswerA());
                            answer3Button.setText(game.getAnswerD());
                            answer4Button.setText(game.getAnswerB());
                        }

                        if (rand == 3){
                            answer1Button.setText(game.getAnswerB());
                            answer2Button.setText(game.getAnswerC());
                            answer3Button.setText(game.getAnswerA());
                            answer4Button.setText(game.getAnswerD());
                        }

                        if (rand == 4){
                            answer1Button.setText(game.getAnswerD());
                            answer2Button.setText(game.getAnswerB());
                            answer3Button.setText(game.getAnswerC());
                            answer4Button.setText(game.getAnswerA());

                        }

                    }
                });




            }
        });


        //When the player selects the film button
        filmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalClip.stop();
                finalTense.start();
                finalTense.loop(5000);
                categoryPage.setVisible(false);
                gamePage.setVisible(true);
                game.chosenCategory("Movies");

                playerNameTxt.setText(game.getPlayerNumber().getPlayerName());
                amountWonTxt.setText(String.valueOf(game.getPlayerNumber().getAmountWon()));
                gameQuestionLabel.setText(game.getGameQuestion());
                nextButton.setEnabled(false);
                int rand = (int) ((Math.random() * 4) + 1);


                if (rand == 1){
                    answer1Button.setText(game.getAnswerA());
                    answer2Button.setText(game.getAnswerC());
                    answer3Button.setText(game.getAnswerD());
                    answer4Button.setText(game.getAnswerB());
                }

                if (rand == 2){
                    answer1Button.setText(game.getAnswerC());
                    answer2Button.setText(game.getAnswerA());
                    answer3Button.setText(game.getAnswerD());
                    answer4Button.setText(game.getAnswerB());
                }

                if (rand == 3){
                    answer1Button.setText(game.getAnswerB());
                    answer2Button.setText(game.getAnswerC());
                    answer3Button.setText(game.getAnswerA());
                    answer4Button.setText(game.getAnswerD());
                }

                if (rand == 4){
                    answer1Button.setText(game.getAnswerD());
                    answer2Button.setText(game.getAnswerB());
                    answer3Button.setText(game.getAnswerC());
                    answer4Button.setText(game.getAnswerA());

                }

                nextButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int rand = (int) ((Math.random() * 4) + 1);
                        finalTense.start();

                        if (game.getPlayer().isEmpty()){
                            String noPlayers = "Our system detects there are no players left in the game. Thank you for playing!";
                            JOptionPane.showMessageDialog(null, noPlayers);
                            System.exit(0);
                        }
                        correctAnswerTxt.setText("");
                        answer1Button.setEnabled(true);
                        answer2Button.setEnabled(true);
                        answer3Button.setEnabled(true);
                        answer4Button.setEnabled(true);
                        askAudienceButton.setEnabled(true);
                        giveClueButton.setEnabled(true);
                        a5050Button.setEnabled(true);
                        answer1Button.setVisible(true);
                        answer2Button.setVisible(true);
                        answer3Button.setVisible(true);
                        nextButton.setEnabled(false);
                        answer4Button.setVisible(true);
                        game.gettingCurrentPlayer();
                        game.chosenCategory("Movies");
                        playerNameTxt.setText(game.getPlayerNumber().getPlayerName());
                        amountWonTxt.setText(String.valueOf(game.getPlayerNumber().getAmountWon()));
                        gameQuestionLabel.setText(game.getGameQuestion());


                        if (rand == 1){
                            answer1Button.setText(game.getAnswerA());
                            answer2Button.setText(game.getAnswerC());
                            answer3Button.setText(game.getAnswerD());
                            answer4Button.setText(game.getAnswerB());
                        }

                        if (rand == 2){
                            answer1Button.setText(game.getAnswerC());
                            answer2Button.setText(game.getAnswerA());
                            answer3Button.setText(game.getAnswerD());
                            answer4Button.setText(game.getAnswerB());
                        }

                        if (rand == 3){
                            answer1Button.setText(game.getAnswerB());
                            answer2Button.setText(game.getAnswerC());
                            answer3Button.setText(game.getAnswerA());
                            answer4Button.setText(game.getAnswerD());
                        }

                        if (rand == 4){
                            answer1Button.setText(game.getAnswerD());
                            answer2Button.setText(game.getAnswerB());
                            answer3Button.setText(game.getAnswerC());
                            answer4Button.setText(game.getAnswerA());

                        }

                    }
                });



            }
        });


        //Gets the correct clue
        giveClueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getPlayerNumber().getAskClue().equals(true)){
                    giveClueButton.setEnabled(false);
                    game.getPlayerNumber().playerAskForHelp("Clue");
                    String tv = game.getClue();
                    JOptionPane.showMessageDialog(null, tv);

                }

                else{
                    String helplineused = "<html>" +
                            "<body width='400'><h1>Helpline used</h1>" +
                            "<p> You have already asked for a hint!" ;
                    JOptionPane.showMessageDialog(null, helplineused);

                }

            }
        });

       //A method which sets visible the correct answer, and one of the other answers randomly.
        a5050Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getPlayerNumber().getAskForHalfAndHalf().equals(true)){
                    a5050Button.setEnabled(false);
                    game.getPlayerNumber().playerAskForHelp("50/50");
                    int random = (int) ((Math.random() * 3) + 1);


                    if (answer1Button.getText().equals(game.getAnswerA())){
                        answer1Button.setVisible(true);
                        if (random == 1){
                            answer2Button.setVisible(true);
                            answer3Button.setVisible(false);
                            answer4Button.setVisible(false);
                        }
                        else if(random == 2){
                            answer2Button.setVisible(false);
                            answer3Button.setVisible(false);
                            answer4Button.setVisible(true);
                        }
                        else if(random == 3){
                            answer2Button.setVisible(false);
                            answer3Button.setVisible(true);
                            answer4Button.setVisible(false);
                        }



                    }
                    if (answer2Button.getText().equals(game.getAnswerA())){
                        answer2Button.setVisible(true);
                        if (random == 1){
                            answer1Button.setVisible(true);
                            answer3Button.setVisible(false);
                            answer4Button.setVisible(false);
                        }
                        else if(random == 2){
                            answer1Button.setVisible(false);
                            answer3Button.setVisible(false);
                            answer4Button.setVisible(true);
                        }
                        else if(random == 3){
                            answer1Button.setVisible(false);
                            answer3Button.setVisible(true);
                            answer4Button.setVisible(false);
                        }



                    }
                    if (answer3Button.getText().equals(game.getAnswerA())){
                        answer3Button.setVisible(true);
                        if (random == 1){
                            answer2Button.setVisible(true);
                            answer1Button.setVisible(false);
                            answer4Button.setVisible(false);
                        }
                        else if(random == 2){
                            answer2Button.setVisible(false);
                            answer1Button.setVisible(false);
                            answer4Button.setVisible(true);
                        }
                        else if(random == 3){
                            answer2Button.setVisible(false);
                            answer1Button.setVisible(true);
                            answer4Button.setVisible(false);
                        }



                    }
                    if (answer4Button.getText().equals(game.getAnswerA())){
                        answer4Button.setVisible(true);
                        if (random == 1){
                            answer2Button.setVisible(true);
                            answer3Button.setVisible(false);
                            answer1Button.setVisible(false);
                        }
                        else if(random == 2){
                            answer2Button.setVisible(false);
                            answer3Button.setVisible(false);
                            answer1Button.setVisible(true);
                        }
                        else if(random == 3){
                            answer2Button.setVisible(false);
                            answer3Button.setVisible(true);
                            answer1Button.setVisible(false);
                        }



                    }







                }

                else{
                    String helplineused = "<html>" +
                            "<body width='400'><h1>Helpline used</h1>" +
                            "<p> You have already asked to half your options!" ;
                    JOptionPane.showMessageDialog(null, helplineused);

                }


            }
        });

        //An action listener for when the player has finished using Ask The Audience
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePage.setVisible(true);
                askAudiencePage.setVisible(false);
            }
        });


        //Method for when the player Asks the Audience
        askAudienceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getPlayerNumber().getAskAudience().equals(true)){
                    askAudienceButton.setEnabled(false);
                    game.getPlayerNumber().playerAskForHelp("Ask The Audience");
                    gamePage.setVisible(false);
                    askAudiencePage.setVisible(true);
                    //Method which makes the audiences opinion random
                    int rand = (int) ((Math.random() * 6) + 1);
                    if (rand == 6){
                        answerA.setText(game.getAnswerC());
                        answerB.setText(game.getAnswerB());
                        answerC.setText(game.getAnswerD());
                        answerD.setText(game.getAnswerA());

                        answerABar.setValue(12);
                        answerBBar.setValue(1);
                        answerCBar.setValue(46);
                        answerDBar.setValue(41);
                    }
                    if (rand == 5){
                        answerA.setText(game.getAnswerB());
                        answerB.setText(game.getAnswerA());
                        answerC.setText(game.getAnswerC());
                        answerD.setText(game.getAnswerD());

                        answerABar.setValue(43);
                        answerBBar.setValue(39);
                        answerCBar.setValue(11);
                        answerDBar.setValue(7);
                    }
                    if (rand == 4){
                        answerA.setText(game.getAnswerD());
                        answerB.setText(game.getAnswerB());
                        answerC.setText(game.getAnswerA());
                        answerD.setText(game.getAnswerC());

                        answerABar.setValue(21);
                        answerBBar.setValue(13);
                        answerCBar.setValue(44);
                        answerDBar.setValue(22);
                    }
                    if (rand == 3){
                        answerA.setText(game.getAnswerA());
                        answerB.setText(game.getAnswerD());
                        answerC.setText(game.getAnswerB());
                        answerD.setText(game.getAnswerC());

                        answerABar.setValue(66);
                        answerBBar.setValue(4);
                        answerCBar.setValue(29);
                        answerDBar.setValue(1);
                    }

                    if (rand == 2){
                        answerA.setText(game.getAnswerA());
                        answerB.setText(game.getAnswerB());
                        answerC.setText(game.getAnswerC());
                        answerD.setText(game.getAnswerD());

                        answerABar.setValue(79);
                        answerBBar.setValue(12);
                        answerCBar.setValue(8);
                        answerDBar.setValue(1);

                    }
                    if (rand == 1){
                        answerA.setText(game.getAnswerA());
                        answerB.setText(game.getAnswerD());
                        answerC.setText(game.getAnswerB());
                        answerD.setText(game.getAnswerC());

                        answerABar.setValue(55);
                        answerBBar.setValue(11);
                        answerCBar.setValue(12);
                        answerDBar.setValue(22);
                    }
                }

                else{
                    askAudiencePage.setVisible(false);
                    String helplineused = "<html>" +
                            "<body width='400'><h1>Helpline used</h1>" +
                            "<p> You have already asked the audience!" ;
                    JOptionPane.showMessageDialog(null, helplineused);


                }

            }
        });


        //A method for when the the player wins the money.
        thankYouButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.removePlayer();
                if (game.getPlayer().isEmpty()){
                    String noPlayers = "Our system detects there are no players left in the game. Thank you for playing!";
                    JOptionPane.showMessageDialog(null, noPlayers);
                    System.exit(0);
                }
                winnerPage.setVisible(false);
                gamePage.setVisible(true);

            }
        });


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = allPlayer.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Select An Player");
                } else {
                    Player toGo = playerList.getElementAt(selectedIndex);
                    if (JOptionPane.showConfirmDialog(null, "Do you really want to delete a player: " + toGo,
                            "Delete Confirmation", JOptionPane.YES_NO_OPTION)
                            == JOptionPane.YES_OPTION) {
                        game.removePlayer();
                        playerList.removePlayer(toGo.getPlayerName());
                        allPlayer.clearSelection();
                    }

                }

            }
        });
    }





    //A method which clears the text field after you have entered the name
    private void clearAllTextFields() {
        playerNameText.setText("");
    }


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("CombineGUI");
        frame.setContentPane(new CombineGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    //Adds images
    private void createUIComponents() {
        gameLogo = new JLabel(new ImageIcon("Logo.png"));
        logoGame = new JLabel(new ImageIcon("Logo.png"));
    }
}




