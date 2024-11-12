package classes;

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
}