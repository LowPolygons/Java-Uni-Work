package classes;
import java.util.ArrayList;

public class Bodybuilding extends WorkoutPlan {
    double bodyFatPercentage;
    double bodyWeight;
    double dailyCalories;
    double dailyProtein;

    public Bodybuilding(int numDays, int numRests, int length, String workoutName) {
        super(numDays, numRests, length, workoutName);

        this.promptBodybuildingConstructor();
    }

    public void promptBodybuildingConstructor() {
        //
    }
  
    public ArrayList<String> linesToWrite() {
        ArrayList<String> linesToWrite = new ArrayList<String>();
        
        linesToWrite.add("numDays => "+this.getDaySplit()+";");
        linesToWrite.add("numRestDays => "+this.getRestDays()+";");
        linesToWrite.add("workoutLength => "+this.getIdealLength()+";");

        return linesToWrite;
    }

}
