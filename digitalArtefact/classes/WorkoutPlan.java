
//Use inheritence here. Cardio, Powerlifting, Bodybuilding are all 'is-a' as a workout plan, so in this class be extremely generic
package classes;

import java.util.HashMap;

public class WorkoutPlan {
    private int daySplit;
    private int restDays;
    private int idealLength;

    public WorkoutPlan(int numDayForSplit, int numRestDays, int idealLengthInMins) {
        this.daySplit = numDayForSplit;
        this.restDays = numRestDays;
        this.idealLength = idealLengthInMins;
    }

    //Inidivudal setters
    public void updateNumDays(int _new) {
        this.daySplit = _new;
    }

    public void updateNumRests(int _new) {
        this.restDays = _new;
    }

    public void updateLength(int _new) {
        this.idealLength = _new;
    }
}