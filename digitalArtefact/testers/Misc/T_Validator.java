package testers.Misc;

import classes.Misc.Validator;
import interfaces.testers.TesterBTS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import testers.Logging.Logger;

public class T_Validator implements TesterBTS {
    //An amalgamation of all possible inputs for the Validator:
    //Note: these can be hard coded because no specific character searching/parsing is done here. As well as this, this covers all the correct grounds for all of the tests
    // - double, nonsense, nonsense (edge case), nonesense, integer, no edgecase(invalid), yes
    private ArrayList<String> linesToWrite = new ArrayList<>();
    private final ArrayList<String> testData = new ArrayList<>(
        Arrays.asList(
        "49.1",
        "-qwdu9",
        "234.13j",
        "fh9whf80439\\1",
        "100",
        "1000",
        "940",
        "noo",
        "yes"
        )
    );

    public T_Validator() {};
    //You can loop through a String with a scanner by separating with a \n

    //Test each function n yeah
    @Override
    public ArrayList<String> testPreparation() {
        return this.testData;
    }

    @Override
    public void runTest(ArrayList<String> inputs) {
        String allResults = String.join("\n", inputs);

        Scanner sc = new Scanner(allResults);
            String yesOrNo = Validator.yesOrNo(sc, "Message as a result of Testing (IGNORE)");
        sc.close();
        sc = new Scanner(allResults);
            String validateInt = Integer.toString(Validator.validateInt(sc, "Message as a result of Testing (IGNORE)"));
        sc.close();
        sc = new Scanner(allResults);
            String validateDouble = Double.toString(Validator.validateDouble(sc, "Message as a result of Testing (IGNORE)"));
        sc.close();
        sc = new Scanner(allResults);
            String intInRange = Integer.toString(Validator.intInRange(sc, 101, 950));
        sc.close();
        //As they are hard coded, you know what the expected output should be, so you can hard code these as well
        //For the list of validators, this data set will always be sufficient
        boolean[] results = new boolean[4];
      
        results[0] = (yesOrNo.equals("yes"));
        results[1] = (validateInt.equals("100"));
        results[2] = (validateDouble.equals("49.1"));
        results[3] = (intInRange.equals("940"));

        int numSuccessful = 0;

        this.linesToWrite.add("\n[T_Validator test : Four in Total]");
        
        for (boolean curr : results) {
            if (curr) {
                numSuccessful++;
                this.linesToWrite.add("Passed");
            } else {
                this.linesToWrite.add("Failed");
            }
        }

        this.linesToWrite.add(numSuccessful+" out of 4 Passed\n");
    }

    @Override
    public void feedbackTest(Logger logger) {
        logger.addToLogLines(this.linesToWrite);
    }
}