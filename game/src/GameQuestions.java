/*
 * @author - Amrit Sarai
 * @uniCourse - Computing Science
 * @uniNumber - u1755231
 * This is the question class.
 */
import org.codehaus.jackson.annotate.JsonProperty;

/*
 * This class matches everything that is inside the array of questions in the JSON file, so it represents
 * a question object.
 */
public class GameQuestions {
    //Instance Variables, note the variable answerA is the variable which always has the correct answer.
    private String gameCategory;
    private String gameQuestion;
    private String askForClue;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    //Constructor
    public GameQuestions(
            /*
            @JsonProperty matches the instance variables in the class with the variables in the JSON file
             */
                    @JsonProperty("gameCategory") String gameCategory,
                    @JsonProperty("gameQuestion") String gameQuestion,
                    @JsonProperty("askForClue") String askForClue, @JsonProperty("answerA") String answerA,
                    @JsonProperty("answerB") String answerB, @JsonProperty("answerC") String answerC,
                    @JsonProperty("answerD") String answerD) {
        this.gameCategory = gameCategory;
        this.gameQuestion = gameQuestion;
        this.askForClue = askForClue;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;

    }

    //Getters, I didn't use setter because it was never going to be used.
    public String getGameCategory() {
        return gameCategory;
    }

    public String getGameQuestion() {
        return gameQuestion;
    }


    public String getAskForClue() {
        return askForClue;
    }

    //Answer A is always correct
    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }
















}



















