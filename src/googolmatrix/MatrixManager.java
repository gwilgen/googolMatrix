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
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author guillermo.barbero.m1
 */
public class MatrixManager {

    private Coordinate lastPosition = null;
    private HashMap<Coordinate, Integer> matrix = null;
    private boolean flagDebug, flagAll;
    private ArrayList<Route> routeList = new ArrayList(); // To store all the possible ways to get an answer for the problem

    public MatrixManager(Coordinate last, Coordinate maxSize, boolean bDebug, boolean bAll) {
        lastPosition = last;
        matrix = new HashMap();
        flagDebug = bDebug;
        flagAll = bAll;
    }

    public ArrayList<Route> getRouteList() {
        return routeList;
    }
    
    /**
     * Returns the shortest route of the ones avaiable in routeList
     * @return
     */
    public Route getShortestRoute() {
    	Route shortestRoute = null;
    	if (routeList.size()>0)
    	{
    		shortestRoute = routeList.get(0);
	    	for (int i = 0; i < routeList.size(); i++){
	    		Route currentRoute = routeList.get(i);
	    		if (shortestRoute.size() > currentRoute.size())
	    			shortestRoute = currentRoute;
	    	}
	    }
    	return shortestRoute;
    }

    /**
     * Adds the value that has the node that belongs to this coordinate
     * @param coord
     * @param i
     */
    private void add(Coordinate coord, Integer i) {
        matrix.put(coord, i);
        if (flagDebug) {
            System.out.println("Adding: " + coord + " -> " + i);
        }
    }

    /**
     * Puts the values among the matrix based in the "base" array wich determines where are the limits of each dimension
     * @param base
     * @param values
     */
    public void populate(ArrayList<Integer> base, ArrayList<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            ArrayList<Integer> actualCoord = new ArrayList();
            int j = i;
            for (Iterator<Integer> iBase = base.iterator(); iBase.hasNext();) {
                int actualBase = iBase.next();
                actualCoord.add(j / actualBase);
                j %= actualBase;
            }
            add(new Coordinate(actualCoord), values.get(i));
        }
    }

    public Integer pointValue(Coordinate coord) {
        return matrix.get(coord);
    }

    /**
     * Returns the coordinates that are next to the one given as an argument
     * @param coord
     * @return
     */
    private ArrayList<Coordinate> getNeighbours(Coordinate coord) {
        ArrayList<Coordinate> coords = new ArrayList();
        int sign = -1;
        for (int j = 0; j < 2; j++) {
            sign *= -1;
            for (int i = 0; i < coord.getCoord().size(); i++) {
                ArrayList<Integer> newCoord = new ArrayList(coord.getCoord());
                newCoord.add(i, newCoord.remove(i) + sign);
                Coordinate c = new Coordinate(newCoord);
                if (matrix.containsKey(c)) {
                    coords.add(c);
                }
            }
        }
        return coords;
    }

    private boolean isLast(Coordinate coord) {
        return coord.equals(lastPosition);
    }

    /**
     * If has arrived to the last position, and the accumulated ammount is ok, it is done.
     * If not, checks the possibility of going to every neighbour that hasn't been used before.
     * @param coord
     * @param route
     * @param accum
     * @param objective
     * @throws EndException
     * @throws RouteException
     */
    public void processPoint(Coordinate coord, Route route, Integer accum, Integer objective)
            throws EndException, RouteException {
        if (!route.contains(coord)) {
            Route newRoute = new Route(route);
            newRoute.add(coord);
            accum += pointValue(coord);
            if (isLast(coord)) {
                if (accum.equals(objective)) {
                    throw new EndException("Objective reached ("+newRoute.size()+" steps):", newRoute);
                } else {
                    throw new RouteException("Got to last position without the correct amount (" + accum + ")");
                }
            }
            if (accum > objective) {
                throw new RouteException("Passed the correct amount without being in the last position (" + accum + ")");
            }
            for (Iterator<Coordinate> iNeighbours = getNeighbours(coord).iterator(); iNeighbours.hasNext();) {
                try {
                    processPoint(iNeighbours.next(), newRoute, accum, objective);
                } catch (RouteException re) {
                    if (flagDebug) {
                        System.out.println(re.getMessage());
                    }
                } catch (EndException ee) {
                    if (flagAll) {
                        routeList.add(ee.getRoute());
                    } else {
                        throw ee;
                    }
                }
            }
        }
    }

    public void printRoute(Route r) {
        for (Iterator<Coordinate> iCoord = r.getRoute().iterator(); iCoord.hasNext();) {
            Coordinate c = iCoord.next();
            System.out.println(c + " -> " + pointValue(c));
        }
    }
}
