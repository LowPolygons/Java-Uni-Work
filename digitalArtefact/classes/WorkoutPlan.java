
//Use inheritence here. Cardio, Powerlifting, Bodybuilding are all 'is-a' as a workout plan, so in this class be extremely generic

//When a workout plan is created, it obviously must be stored in the profile for the user.
//- Split the profile saving into better chartertings.
//- A section should have a title being the composite class name
//- In the workout case, the workout will be replaced by the child class type (powerlifting, bodybuilding, cardio)
//- the variables will then be oriented around the given type
//- How to store these though? Perhaps workout plans are separately stored

package classes;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

import interfaces.WorkoutBTS;

//Abstract so that it doesn't have to be instantiated immediately in the Profile class
public abstract class WorkoutPlan implements WorkoutBTS {
    //key information across all workouts
    private int daySplit;
    private int restDays;
    private int idealLength;

    //user can name their profiles
    private String workoutName;

    public WorkoutPlan(int numDayForSplit, int numRestDays, int idealLengthInMins, String workout_Name) {
        this.daySplit = numDayForSplit;
        this.restDays = numRestDays;
        this.idealLength = idealLengthInMins;
        this.workoutName = workout_Name;
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

    //Getters
    public int getDaySplit() {
      return this.daySplit;
    }

    public int getRestDays() {
      return this.restDays;
    }
    
    public int getIdealLength() {
      return this.idealLength;
    }
    //Getter for file writing
    public String getNameForFile() {
      return this.workoutName;
    }

    //Both will use this so may as well store as a concrete super method
    public void WorkoutInstance() {
      //Display user options in a while loop
      
      Scanner sc = new Scanner(System.in);

      boolean breakOut = false;

      do {
        this.perWorkoutFunc(sc);

        String result = Validator.yesOrNo(sc, "\n\nDo you want to continue?");

        if (result.toLowerCase().equals("no"))
          breakOut = true;

      } 
      while( !breakOut );
  }
    //Override this
    public abstract void perWorkoutFunc(Scanner sc);
}
