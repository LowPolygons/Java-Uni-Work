
//Use inheritence here. Cardio, Powerlifting, Bodybuilding are all 'is-a' as a workout plan, so in this class be extremely generic

//When a workout plan is created, it obviously must be stored in the profile for the user.
//- Split the profile saving into better chartertings.
//- A section should have a title being the composite class name
//- In the workout case, the workout will be replaced by the child class type (powerlifting, bodybuilding, cardio)
//- the variables will then be oriented around the given type
//- How to store these though? Perhaps workout plans are separately stored

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