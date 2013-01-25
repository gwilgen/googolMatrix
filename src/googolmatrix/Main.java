/* 
 	googolMatrix: calculates possible solutions for the problem shown in "Wonders of Numbers", chapter 23, "Cube labyrinth"
   
    Copyright (C) 2013  Guillermo Barbero Maiz

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/    

package googolmatrix;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author guillermo.barbero.m1
 */
public class Main {

    static MatrixManager mm = null;

    /**
     * As several command line arguments are vectors, this function makes the conversion to an ArrayList object
     * @param param
     * @param vectorName
     * @return
     * @throws NumberFormatException
     */
    private static ArrayList<Integer> initialize(String param, String vectorName) throws NumberFormatException {
        ArrayList<Integer> vector = new ArrayList();
        try {
            StringTokenizer st = new StringTokenizer(param, ",");
            for (; st.hasMoreTokens();) {
                vector.add(Integer.valueOf(st.nextToken()));
            }
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("The " + vectorName + " is not a valid vector");
        }
        return vector;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> initPos, lastPos, maxSize, valueList, base = new ArrayList();
        Integer objective = null;
        boolean bDebug = false, bAll = false;

        try {
            int iArg = 0, numMaxParams = 5;
            String s = args[iArg++];
            if (s.equals("-debug")) {
                bDebug = true;
                s = args[iArg++];
                numMaxParams++;
            }
            if (s.equals("-all")) {
                bAll = true;
                s = args[iArg++];
                numMaxParams++;
            }
            objective = Integer.parseInt(s);
            lastPos = initialize(args[iArg++], "Last Position");
            if (args.length == numMaxParams) {
                initPos = initialize(args[iArg++], "Initial Position");
            } else {
                initPos = new ArrayList();
                for (int i = 0; i < lastPos.size(); i++) {
                    initPos.add(0);
                }
            }
            maxSize = initialize(args[iArg++], "Max Size");
            valueList = initialize(args[iArg++], "Value List");
            int matrixSize = 1; // This value is to check the volume the matrix has and the number of values given as argument
            for (int i = maxSize.size() - 1; i >= 0; i--) {
                base.add(0, matrixSize); // Later it will be needed to allocate the value in the correct coordinate
                matrixSize *= maxSize.get(i);
            }
            if (matrixSize != valueList.size()) {
                throw new Exception("The matrix is up to " + matrixSize + " values, but the values list has " + valueList.size() + " values. They must be equal");
            }
            if (initPos.size() != lastPos.size() || initPos.size() != maxSize.size()) {
                throw new Exception("The size of the vectors ('Initial' and 'Last Position') and 'Max Size' must be the same");
            }
            for(int i = 0; i<maxSize.size(); i++){
            	int dimensionLimit = maxSize.get(i);
            	int initValueForThisDimension = initPos.get(i);
            	int lastValueForThisDimension = lastPos.get(i);
            	if (initValueForThisDimension < 0 || initValueForThisDimension >= dimensionLimit)
            		throw new Exception("The init value for the dimension "+i+" must be between 0 and 'max value - 1'");
            	if (lastValueForThisDimension < 0 || lastValueForThisDimension >= dimensionLimit)
            		throw new Exception("The last value for the dimension "+i+" must be between 0 and 'max value - 1'");
            }
            mm = new MatrixManager(new Coordinate(lastPos), new Coordinate(maxSize), bDebug, bAll);
            mm.populate(base, valueList); // Fills the matrix with the values given in the command line
            try {
                mm.processPoint(new Coordinate(initPos), new Route(), 0, objective);
                if (bAll) {
                    System.out.println("Found " + mm.getRouteList().size() + " posibilities");
                    if (mm.getRouteList().size() > 0) {
                    	Route shortestRoute = mm.getShortestRoute();
                        throw new EndException("The shortest posibility found ("+shortestRoute.size()+" steps):", shortestRoute);
                    }
                } else {
                    System.out.println("Finished all posibilities without finding an answer");
                }
            } catch (EndException ee) {
                System.out.println(ee.getMessage());
                mm.printRoute(ee.getRoute());
            } catch (RouteException re) {
                System.out.println("Uncached exception: " + re);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("The arguments expected are -debug|-all 'Objective' 'Last Position' ['Initial Position'] 'Max Size' 'Value List'");
            for (int i = 0; i < args.length; i++) {
                System.out.println("param " + i + " = " + args[i]);
            }
        }
        
        System.out.println("googolMatrix  Copyright (C) 2013  Guillermo Barbero Maiz");
        System.out.println("This program comes with ABSOLUTELY NO WARRANTY.");
        System.out.println("This is free software, and you are welcome to redistribute it");
        System.out.println("under certain conditions");

    }
}
