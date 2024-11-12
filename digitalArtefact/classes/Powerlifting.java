
package classes;

import java.util.HashMap;
import java.util.ArrayList;

public class Powerlifting extends WorkoutPlan {

    private float maxSquat;
    private float maxBench;
    private float maxDeadlift;
    private float maxCAJ;     //Clean and Clean-And-Jerk

    //First 3 args for super class, the rest are for this
    public Powerlifting(int numDayForSplit, int numRestDays, int idealLengthInMins, String workoutName) { //, float mS, float mB, float mD, float caj) {
        super(numDayForSplit, numRestDays, idealLengthInMins, workoutName);
        
        this.promptPowerliftingConstructor();
    }
    public void promptPowerliftingConstructor() {
        // Do Something
    }

    //Adding ----s to separate termimal lines, makes it prettier/more readable
    public void displaySBD() {
        System.out.println("--------------------------------");

        System.out.println("Your Max Benchpress: " + this.maxBench + "Kg");
        System.out.println("Your Max Squat: " + this.maxSquat + "Kg");
        System.out.println("Your Max Deadlift: " + this.maxDeadlift + "Kg");
        System.out.println("Your Max Clean-And-Jerk: " + this.maxCAJ + "Kg");

        System.out.println("--------------------------------");
    }

    public ArrayList<String> linesToWrite() {
        ArrayList<String> linesToWrite = new ArrayList<String>();
        linesToWrite.add("maxSquat => "+this.maxSquat+";");
        linesToWrite.add("maxBench => "+this.maxBench+";");
        linesToWrite.add("maxDeadlift => "+this.maxDeadlift+";");
        linesToWrite.add("maxCAJ => "+this.maxCAJ+";");
        linesToWrite.add("numDays => "+this.getDaySplit()+";");
        linesToWrite.add("numRestDays => "+this.getRestDays()+";");
        linesToWrite.add("workoutLength => "+this.getIdealLength()+";");

        return linesToWrite;
    }


}
