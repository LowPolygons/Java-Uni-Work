package testers;

import classes.Workouts.*;
import testers.Logging.Logger;
import java.util.ArrayList;

import testers.Misc.T_Validator;
import testers.Misc.T_FileManager;

//This will orchestrate the testing of all the classes and will send the logger object to them all
public class Tester {

    private Logger logger;

    public Tester() {
        logger = new Logger();    

        //Validator class testing
        T_Validator t_v = new T_Validator();
        ArrayList<String> testData = t_v.testPreparation();
        t_v.runTest(testData);
        logger = t_v.feedbackTest(logger);
 
        //FileManager class testing
        T_FileManager t_fm = new T_FileManager();
        testData = t_fm.testPreparation();

        t_fm.runTest(testData);
        logger = t_fm.feedbackTest(logger);

        logger.writeToLog();
    }
}