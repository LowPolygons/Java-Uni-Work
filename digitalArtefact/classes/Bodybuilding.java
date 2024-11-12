package classes;

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
}