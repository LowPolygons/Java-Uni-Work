import java.util.*; //p sure this just means all
import java.io.*;

public class fileIoTest {
    //vector functions
    public static double[] vecAdd(double[] p1, double[] p2) {
        double[] returnVal = new double[p1.length];
        for (int i = 0; i < p1.length; i++) {
            returnVal[i] = p1[i]+p2[i];
        } 
        return returnVal;
    }
    public static double[] vecSub(double[] p1, double[] p2) {
        double[] returnVal = new double[p1.length];
        for (int i = 0; i < p1.length; i++) {
            returnVal[i] = p1[i]-p2[i];
        } 

        return returnVal;
    }
    public static double[] vecMul(double[] p1, double[] p2) {
        double[] returnVal = new double[p1.length];
        for (int i = 0; i < p1.length; i++) {
            returnVal[i] = p1[i]*p2[i];
        } 

        return returnVal;
    }
    public static double[] vecDiv(double[] p1, double[] p2) {
        double[] returnVal = new double[p1.length];
        for (int i = 0; i < p1.length; i++) {
            returnVal[i] = p1[i]/p2[i];
        } 

        return returnVal;
    }
    public static double vecMod(double[] p1) {
        return Math.sqrt((p1[0]*p1[0]) + (p1[1]*p1[1]) + (p1[2]*p1[2]));
    }

    public static double[][] openParticleFile (int numParticles, double[][] particles) {
        //reading the file. Conveniently, this is easy provided the particle file is created in fortran with good formatting tools
        //with the FileNotFoundException, it didnt run so keep it
        try {
            File particleData = new File("pData.dat");
            Scanner scanner = new Scanner(particleData);

            for (int i = 0; i < numParticles; i++) {
                String line = scanner.nextLine();
                particles[i][0] = Double.parseDouble(line.substring(0, 19));
                particles[i][1] = Double.parseDouble(line.substring(23, 43));
                particles[i][2] = Double.parseDouble(line.substring(47, 67));
            }    
            scanner.close();
        } catch (FileNotFoundException err) {
            err.printStackTrace();
        }
        return particles;
    }

    public static int inRange(double[] p1, double[] p2, double[] lowerBound, double[] upperBound, double cutOff) {
        int num_Interactions = 0;
        double[] p2Offset = vecSub(p2, lowerBound);
        double[] boundDiff = vecSub(upperBound, lowerBound);

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    double[] targetPPos = { 
                        lowerBound[0] + x*boundDiff[0] + p2Offset[0], 
                        lowerBound[1] + y*boundDiff[1] + p2Offset[1],
                        lowerBound[2] + z*boundDiff[2] + p2Offset[2]
                    };
                    if ( vecMod(vecSub(targetPPos, p1)) <= cutOff ) {
                        num_Interactions = num_Interactions + 1; 
                    } 
                }
            }
        }

        return num_Interactions;
    }

    public static void main ( String[] args) {
        //ArrayList<double[]> particles
        int numParticles = 10000;
        int numInteractions = 0;
        
        double cutOff = 0.49;
        double[][] particles = new double[numParticles][3];
        double[] lowerBounds = {0.0, 0.0, 0.0};
        double[] upperBounds = {1.0, 1.0, 1.0};
        particles = openParticleFile(numParticles, particles);

        //Works
        // for (int i = 0; i < particles.length-1; i++) {
        //     System.out.println( Double.toString(particles[i][0]) + ", " + Double.toString(particles[i][1]) + ", " + Double.toString(particles[i][2]) + ", Sum: " + Double.toString( particles[i][0] + particles[i][1] + particles[i][2]));
        // }

        for (int currParticle = 0; currParticle < particles.length - 1; currParticle++){
            for (int targetParticle = currParticle+1; targetParticle < particles.length - 1; targetParticle++ ){ //so there arent duplicates,start at currParticle
                
                double[] cParticle = particles[currParticle];
                double[] tParticle = particles[targetParticle];
                numInteractions = numInteractions + inRange(cParticle, tParticle, lowerBounds, upperBounds, cutOff);
                //System.out.println(vecAdd(cParticle, tParticle)[0] + "," + vecAdd(cParticle, tParticle)[1] + "," + vecAdd(cParticle, tParticle)[2]);
            }
        }

        System.out.println("Number of interactions: " + numInteractions);
    }
}
