package classes;
import java.util.ArrayList;

public class Cardio extends WorkoutPlan {
    double fastestMile;
    double powerOutput;

    public Cardio(int numDays, int numRests, int length, String workoutName) {
        super(numDays, numRests, length, workoutName);

        this.promptCardioConstructor();
    }

    public void promptCardioConstructor() {
        //Initialise
    }

    public ArrayList<String> linesToWrite() {
        ArrayList<String> linesToWrite = new ArrayList<String>();
        
        linesToWrite.add("numDays => "+this.getDaySplit()+";");
        linesToWrite.add("numRestDays => "+this.getRestDays()+";");
        linesToWrite.add("workoutLength => "+this.getIdealLength()+";");

        return linesToWrite;
    }

}
