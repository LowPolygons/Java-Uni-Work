package classes;
import java.util.ArrayList;

public class Cardio extends WorkoutPlan {
    double fastestMile;
    double powerOutput;

    public Cardio(int numDays, int numRests, int length, String workoutName, boolean alreadyExists) {
        super(numDays, numRests, length, workoutName);

        this.promptCardioConstructor();
    }

    public void promptCardioConstructor() {
        //Initialise
    }

    public ArrayList<String> linesToWrite() {
        ArrayList<String> linesToSave = new ArrayList<String>();
        
        linesToSave.add("workoutStyle => Cardio;");
        linesToSave.add("numDays => "+this.getDaySplit()+";");
        linesToSave.add("numRestDays => "+this.getRestDays()+";");
        linesToSave.add("workoutLength => "+this.getIdealLength()+";");

        return linesToSave;
    }

}
