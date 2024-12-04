package testers;

import classes.Workouts.*;
import testers.Logging.Logger;
import java.util.ArrayList;

import testers.Misc.T_Validator;

//This will orchestrate the testing of all the classes and will send the logger object to them all
public class Tester {

    private Logger logger;

    public Tester() {
        logger = new Logger();    
        T_Validator t_v = new T_Validator();
        //T_Validator
        ArrayList<String> testData = t_v.testPreparation();
        t_v.runTest(testData);
        t_v.feedbackTest(logger);
 
        logger.writeToLog();
    }
}