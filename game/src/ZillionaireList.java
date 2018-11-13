/*
 * @author - Amrit Sarai
 * @uniCourse - Computing Science
 * @uniNumber - u1755231
 * This is the class which stores the list of questions for the Zillionaire Game.
 */
import org.codehaus.jackson.annotate.JsonProperty;
import java.util.ArrayList;

public class ZillionaireList {

    //The Instance Variables for my class
    private ArrayList<GameQuestions> questions;
    //Constructor for the ZillionaireList class. @JsonProperty matches the variable in the class, with the variables
    //in the JSON file.
    public ZillionaireList(@JsonProperty("Zillionaire") ArrayList<GameQuestions> questions) {
        this.questions = questions;
    }
    //Getter, I didn't use setter because it was never going to be used.
    public ArrayList<GameQuestions> getQuestions() {
        return questions;
    }

}
