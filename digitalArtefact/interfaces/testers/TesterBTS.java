package interfaces.testers;

import testers.Logging.Logger;
import java.util.ArrayList;

public interface TesterBTS {
    //get the data to be ran, however that is decided
    public abstract ArrayList<String> testPreparation();
   
    //Runs the test
    public abstract void runTest(ArrayList<String> inputs);

    //Feeds the test results to the Logger
    public abstract void feedbackTest(Logger logger);
}