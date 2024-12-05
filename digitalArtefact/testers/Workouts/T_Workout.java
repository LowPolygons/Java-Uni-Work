package testers.Workouts;

import java.util.*;

import classes.Workouts.*;
import interfaces.testers.TesterBTS;

import java.util.ArrayList;
import testers.Logging.Logger;

public class T_Workout implements TesterBTS {
    public T_Workout() {};
    
    @Override
    public ArrayList<String> testPreparation() {
        return new ArrayList<>();
    }

    @Override
    public void runTest(ArrayList<String> inputs) {
        System.out.println("The Workout Class, besides reliance on the FileManager and Validator class which are already tested, provides no opportunity for error.");
    }
    @Override
    public Logger feedbackTest(Logger logger) {
        ArrayList<String> _a = new ArrayList<>();

        _a.add("The Workout Class, besides reliance on the FileManager and Validator class which are already tested, provides no opportunity for error.");
        logger.addToLogLines(_a);

        return logger;
    }
    
}