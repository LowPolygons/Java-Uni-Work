package testers.Profile;

import classes.*;
import interfaces.testers.TesterBTS;

import java.util.ArrayList;
import testers.Logging.Logger;

public class T_Profile implements TesterBTS {
    public T_Profile () {};
    
    @Override
    public ArrayList<String> testPreparation() {
        return new ArrayList<>();
    }

    @Override
    public void runTest(ArrayList<String> inputs) {
        System.out.println("The Profile Class, besides reliance on the FileManager and Validator class which are already tested, provides no opportunity for error.");
    }
    @Override
    public Logger feedbackTest(Logger logger) {
        ArrayList<String> _a = new ArrayList<>();

        _a.add("The Profile Class, besides reliance on the FileManager and Validator class which are already tested, provides no opportunity for error.");
        logger.addToLogLines(_a);

        return logger;
    }
    
}