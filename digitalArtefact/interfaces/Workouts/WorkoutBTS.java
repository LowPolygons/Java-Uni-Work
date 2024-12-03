package interfaces.Workouts;

import java.util.ArrayList;

//The methods must be abstract or they aren't recognised as a symbol by the compiler
public interface WorkoutBTS {
    //For file writing
    public abstract ArrayList<String> linesToWrite();
    
    //Standard function for displaying Information
    public abstract void displayInformation();

    //The local constructor for each class
    public abstract void promptLocalConstructor(boolean alreadyExists);
}