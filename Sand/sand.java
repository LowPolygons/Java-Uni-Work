import java.util.*; //p sure this just means all

public class sand {
    static int[] dims   = {300,260};
    //static int[] dims   = {600,130};

    public static void printResult(int[] dims, int[][] positions, String[] orderOfWeight) {
        String currLine = "";
        for (int y = 1; y <= dims[1]; y++) {

            for (int x = 1; x <= dims[0]; x++) {
                String append = "";
                if (y == 1 || y == dims[1] || x == 1 || x == dims[0] ) {
                    //append = escapeCodes[orderOfWeight.length - 1] + orderOfWeight[orderOfWeight.length - 1];
                    append = "\033[37;47m" + orderOfWeight[orderOfWeight.length - 1] + "\033[;m";
                }
                else {
                   // append = escapeCodes[positions[x-2][y-2]] + orderOfWeight[positions[x-2][y-2]];
                    append = orderOfWeight[positions[x-2][y-2]] + "\033[;m";
                }

                currLine = currLine + append;
            }
            currLine = currLine + "\033[;m\n";
        }
        System.out.println(currLine);    
    }

    public static int[][] addObjects(int[] dims, int[][] positions, String[] orderOfWeight, int obstacleCount) {
        for (int i = 0; i < obstacleCount; i++) {
            // This is a mess but it gets random coordinates that:
            // - will not take up the majority of the window
            // - will not exceed the boundaries
            int[] dimensions = {(int)(Math.random()*(double)((dims[0]-2) / 3)),(int)(Math.random()*(double)((dims[1]-2)/3))};
            int[] _positions = {(int)(Math.random()*(double)(dims[0] - dimensions[0]-2)),(int)(Math.random()*(double)(dims[1] - dimensions[1] - 2))};
            for (int x = Math.max(1,_positions[0]-1); x < (_positions[0] + dimensions[0] - 1); x++ ) {
                for (int y = Math.max(1,_positions[1]-1); y < (_positions[1] + dimensions[1] - 1); y++ ) {
                    positions[x][y] = orderOfWeight.length - 1;
                }
            }
        }
        return positions;
    }

    //main program
    public static void main ( String[] args) {
        int[][] positions = new int[dims[0]-2][dims[1]-2];
        //the first and last item represent air and the borders respectfully
        String[] orderOfWeight = {" ","1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M", "=", "-", "[", "]", ";", "'", ",", ".", "/", "`", "(", ")", "\\", "!", "£", "$", "%", "^", "&", "*", "{", "}", ":", "@", "'", "~", "<", ">", "?", "#"};
        int timeSteps = 40000;
        int obstacleCount = 5;
        boolean hasMoved = false;
        //populate the positions array
        //-2 on the x and y because you dont want to populate the very outer indexes
        //whenever printing, always access the index+1
        for (int y = 0; y < dims[1]-2; y++) {
            for (int x = 0; x < dims[0]-2; x++) {
                double rand = Math.random();
                //Remember the beneath math.random is changing for every pixel, thats why the distribution gets so small towards the end
                if ( y <= (dims[1] * Math.random()) ) { //&& (x <= (dims[0]*Math.random()))
                    positions[x][y] = (int)(rand*(orderOfWeight.length-2)) + 1; //-2 becayse you want to ignroe the last and first item, +1 to offset
                }
                else {
                    positions[x][y] = 0;
                }
            }
        }

        //generate some obstacles
        positions = addObjects(dims, positions, orderOfWeight, obstacleCount);

        printResult(dims, positions, orderOfWeight);
        //simulation
        for (int i = 0; i < timeSteps; i++) {
            hasMoved = false;
            for (int y = dims[1]-4; y >= 0; y = y - 1){ //-4: -2 from it being 2 smaller than dims, -2 because you ignore the bottom layer
                for (int x = 0; x < dims[0]-2; x++){
                    //check if there is a space below it, if so swap it
                    int currentChar = positions[x][y];
                    int beneathChar = positions[x][y+1];
                    int leftChar = positions[Math.max(0,x-1)][y+1];
                    int rightChar = positions[Math.min(dims[0]-3,x+1)][y+1];
                    boolean[] leftMiddleRight = {false, false, false};
                    boolean notMoved = true;
                    //not memory efficient but cry more
                    if (currentChar > 0 && currentChar < (orderOfWeight.length - 1)) { //not zero, not the walls
                        if (beneathChar < currentChar) { leftMiddleRight[1] = true; }
                        if (leftChar < currentChar)    { leftMiddleRight[0] = true; }
                        if (rightChar < currentChar)   { leftMiddleRight[2] = true; }
                        //its a botch solution for now but it works
                        if (leftMiddleRight[0] && notMoved && (Math.random() < Math.random())) {
                            positions[x][y] = leftChar;
                            positions[Math.max(0,x-1)][y+1] = currentChar;
                            notMoved = false;
                            hasMoved = true;
                        }
                        else if (leftMiddleRight[2] && notMoved && (Math.random() < Math.random())) {
                            positions[x][y] = rightChar;
                            positions[Math.min(dims[0]-3,x+1)][y+1] = currentChar;
                            notMoved = false;
                            hasMoved = true;
                        }
                        //if it still hasnt moved, just go down
                        if (leftMiddleRight[1] && notMoved) {
                            positions[x][y] = beneathChar;
                            positions[x][y+1] = currentChar;       
                            hasMoved = true;               
                        }
                    }
                }
            }
            //printResult(dims, positions, orderOfWeight);
            if (!hasMoved) {
                break;
            }
        }
        printResult(dims, positions, orderOfWeight);
    }
}
