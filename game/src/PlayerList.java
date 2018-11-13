/*
 * @author - Amrit Sarai
 * @uniCourse - Computing Science
 * @uniNumber - u1755231
 * This is the Player List class - Required if I wanted to use a JList in my GUI.
 */

import javax.swing.*;


public class PlayerList extends DefaultListModel<Player> {

    public PlayerList(){
        super();

    }

    public void addPlayer(String name){
        super.addElement(new Player(name));

    }



    public Player findPlayerByName(String name){
        Player temp;
        int indexLocation = -1;
        for (int i = 0; i < super.size(); i++) {
            temp = (Player) super.elementAt(i);
            if (temp.getPlayerName().equals(name)){
                indexLocation = i;
                break;
            }

        }

        if (indexLocation == -1) {
            return null;
        } else {
            return (Player) super.elementAt(indexLocation);
        }

    }

    public void removePlayer(String name){
        Player playerToGo = this.findPlayerByName(name);
        super.removeElement(playerToGo);
    }






}

