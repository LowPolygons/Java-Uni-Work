import java.util.*; //p sure this just means all

public class sand {
    //static int[] dims   = {1200,260};
    static int[] dims   = {600,130};

    public static void printResult(int[] dims, int[][] positions, String[] orderOfWeight, String[] escapeCodes) {
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
        String[] escapeCodes = {"\033[30;40m", "\033[31;40m", "\033[32;40m", "\033[33;40m", "\033[34;40m", "\033[35;40m", "\033[36;40m", "\033[37;40m", "\033[90;40m", "\033[91;40m", "\033[92;40m", "\033[93;40m", "\033[94;40m", "\033[95;40m", "\033[96;40m", "\033[97;40m", "\033[30;41m", "\033[31;41m", "\033[32;41m", "\033[33;41m", "\033[34;41m", "\033[35;41m", "\033[36;41m", "\033[37;41m", "\033[90;41m", "\033[91;41m", "\033[92;41m", "\033[93;41m", "\033[94;41m", "\033[95;41m", "\033[96;41m", "\033[97;41m", "\033[30;42m", "\033[31;42m", "\033[32;42m", "\033[33;42m", "\033[34;42m", "\033[35;42m", "\033[36;42m", "\033[37;42m", "\033[90;42m", "\033[91;42m", "\033[92;42m", "\033[93;42m", "\033[94;42m", "\033[95;42m", "\033[96;42m", "\033[97;42m", "\033[30;43m", "\033[31;43m", "\033[32;43m", "\033[33;43m", "\033[34;43m", "\033[35;43m", "\033[36;43m", "\033[37;43m", "\033[90;43m", "\033[91;43m", "\033[92;43m", "\033[93;43m", "\033[94;43m", "\033[95;43m", "\033[96;43m", "\033[97;43m", "\033[30;44m", "\033[31;44m", "\033[32;44m", "\033[33;44m", "\033[34;44m", "\033[35;44m", "\033[36;44m", "\033[37;44m", "\033[90;44m", "\033[91;44m", "\033[92;44m", "\033[93;44m", "\033[94;44m", "\033[95;44m", "\033[96;44m", "\033[97;44m", "\033[30;45m", "\033[31;45m", "\033[32;45m", "\033[33;45m", "\033[34;45m", "\033[35;45m", "\033[36;45m", "\033[37;45m", "\033[90;45m", "\033[91;45m", "\033[92;45m", "\033[93;45m", "\033[94;45m", "\033[95;45m", "\033[96;45m", "\033[97;45m", "\033[30;46m", "\033[31;46m", "\033[32;46m", "\033[33;46m", "\033[34;46m", "\033[35;46m", "\033[36;46m", "\033[37;46m", "\033[90;46m", "\033[91;46m", "\033[92;46m", "\033[93;46m", "\033[94;46m", "\033[95;46m", "\033[96;46m", "\033[97;46m", "\033[30;47m", "\033[31;47m", "\033[32;47m", "\033[33;47m", "\033[34;47m", "\033[35;47m", "\033[36;47m", "\033[37;47m", "\033[90;47m", "\033[91;47m", "\033[92;47m", "\033[93;47m", "\033[94;47m", "\033[95;47m", "\033[96;47m", "\033[97;47m"};
        int timeSteps = 40000;
        int obstacleCount = 5;
        boolean hasMoved = false;
        //populate the positions array
        //-2 on the x and y because you dont want to populate the very outer indexes
        //whenever printing, always access the index+1
        for (int y = 0; y < dims[1]-2; y++) {
            for (int x = 0; x < dims[0]-2; x++) {
                double rand = Math.random();
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

        printResult(dims, positions, orderOfWeight, escapeCodes);
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
        printResult(dims, positions, orderOfWeight, escapeCodes);
    }
}
