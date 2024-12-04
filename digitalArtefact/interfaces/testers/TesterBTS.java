package interfaces.testers;

import testers.Logging.Logger;


public interface TesterBTS {
    //get the data to be ran, however that is decided
    public abstract void testPreparation();
   
    //Runs the test
    public abstract void runTest();

    //Feeds the test results to the Logger
    public abstract void feedbackTest(Logger logger);
}