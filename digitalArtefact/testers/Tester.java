package testers;

import java.util.*;
import classes.Workouts.*;

import testers.Logging.*;

//This will orchestrate the testing of all the classes and will send the logger object to them all
public class Tester {

    private Logger logger;

    public Tester() {
        logger = new Logger();    
    }
}