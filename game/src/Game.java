/*
 * @author - Amrit Sarai
 * @uniCourse - Computing Science
 * @uniNumber - u1755231
 * This is the games class - the controller class.
 */


import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    /*
    *Created an ArrayList of players. This is where each player object will be stored. Each player has a number
    * assigned to them, this will help me to swap players when playing the game.
     */
    private ArrayList<Player> player;
    private int playerNumber;
    private ArrayList<String > askedQuestions;
    private GameQuestions gameQuestion = null;

    /*
    My questions are read in this games class. I read the JSON file using ObjectMapper which is part of Jackson
    library.
     */

    ObjectMapper questionReader = new ObjectMapper();
    ZillionaireList question = questionReader.readValue(new File("ZillionaireGame.json"),
            ZillionaireList.class);

    //Constructor
    public Game() throws IOException {
        this.player = new ArrayList<Player>();
        this.playerNumber = 0;
        this.askedQuestions = new ArrayList<String>();

    }


    //Getters and Methods
    public ArrayList<Player> getPlayer() {
        return player;
    }

    public void addPlayer(String name) {
        this.player.add(new Player(name));
    }


    public void removePlayer() {
        this.player.remove(playerNumber);

    }

    public Player getPlayerNumber() {
        return this.player.get(playerNumber);

    }


    public void givePlayerMoney(){
        this.player.get(playerNumber).givePlayerMoney();
    }

    public int getAmountOfPlayers() {
        return this.player.size();
    }


    //An algorithm which goes back to the beginning of the Players Array after it reaches the end.
    public void gettingCurrentPlayer(){
        if (getAmountOfPlayers() > 1){
            if (playerNumber + 1 > getAmountOfPlayers() -1){
                playerNumber = 0;
            } else {
                playerNumber +=1;
            }
        }

        else {
            playerNumber = 0;
        }

    }


    /*
    An algorithm which gets a random question based on the chosen category. Once the question is used it
    it is added to another Array of used questions, therefore the same question doesn't appear twice.
     */
    public GameQuestions chosenCategory(String gameCategory) {
        boolean found = false;
        while (!found) {
            int rand = (int) ((Math.random() * 143) + 1);
            gameQuestion = question.getQuestions().get(rand);
            if ((gameQuestion.getGameCategory().equals(gameCategory))&&
                    (!askedQuestions.contains(gameQuestion.getGameQuestion()))){
                askedQuestions.add(gameQuestion.getGameQuestion());
                found = true;
            } else {
                gameQuestion = null;
            }

        }
        return gameQuestion;
    }



    /*
    The String getters here get the current question object.
     */
    public String getCurrentCategory(){
        return gameQuestion.getGameCategory();
    }

    public String getGameQuestion(){
        return gameQuestion.getGameQuestion();
    }

    public String getAnswerA(){
        return gameQuestion.getAnswerA();

    }

    public String getAnswerB(){
        return gameQuestion.getAnswerB();
    }

    public String getAnswerC(){
        return gameQuestion.getAnswerC();
    }

    public String getAnswerD(){
        return gameQuestion.getAnswerD();
    }

    public String getClue(){
        return gameQuestion.getAskForClue();
    }


}







