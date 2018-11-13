/*
 * @author - Amrit Sarai
 * @uniCourse - Computing Science
 * @uniNumber - u1755231
 * This is the Player class.
 */

import java.util.ArrayList;

public class Player {
    //Instance Variables
    private String playerName;
    private int amountWon;
    private Boolean hasLifeLine;
    private Boolean askClue;
    private Boolean askForHalfAndHalf;
    private Boolean askAudience;



    //Constructor
    public Player(String playerName){
        this.playerName = playerName;
        this.hasLifeLine = true;
        this.amountWon = 0;
        this.askClue = true;
        this.askForHalfAndHalf = true;
        this.askAudience= true;

    }



    //Getters, didn't use setters because they were never called.
    public String getPlayerName() {
        return playerName;
    }


    public int getAmountWon() {
        return amountWon;
    }


    public void setAmountWon(int amountWon) {
        this.amountWon = amountWon;
    }

    public Boolean getAskClue() {
        return askClue;
    }

    public Boolean getAskForHalfAndHalf() {
        return askForHalfAndHalf;
    }

    public Boolean getAskAudience() {
        return askAudience;
    }


    //Method which gives the player money every time they get the question correct.
    public void givePlayerMoney() {
        switch (amountWon) {
            case 0:
                amountWon = 500;
                break;
            case 500:
                amountWon = 1000;
                break;
            case 1000:
                amountWon = 2000;
                break;
            case 2000:
                amountWon = 5000;
                break;
            case 5000:
                amountWon = 10000;
                break;
            case 10000:
                amountWon = 20000;
                break;
            case 20000:
                amountWon = 50000;
                break;
            case 50000:
                amountWon = 75000;
                break;
            case 75000:
                amountWon = 150000;
                break;
            case 150000:
                amountWon = 250000;
                break;
            case 250000:
                amountWon = 500000;
                break;
            case 500000:
                amountWon = 1000000;
                break;

    }

    }


    //Method which checks if the player has used a lifeline. If they do use a lifeline they no longer can use that
    //lifeline, therefore equals false.
    public void playerAskForHelp(String choice){
        if (hasLifeLine){
            if(choice.equals("Clue")){
               this.askClue = false;
            }

            if(choice.equals("50/50")){
                this.askForHalfAndHalf = false;
            }

            if(choice.equals("Ask The Audience")){
                this.askAudience = false;
            }

        }
    }

    //toString method because I don't want my JList to show register numbers, when showing the players name.
    public String toString(){
        return "" + playerName;
    }








}
