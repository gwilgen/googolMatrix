/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googolmatrix;

import java.util.ArrayList;

/**
 *
 * @author guillermo.barbero.m1
 */
public class Route {

    private ArrayList<Coordinate> route;

    public Route() {
        route = new ArrayList();
    }

    public Route(Route r) {
        route = new ArrayList(r.route);
    }

    public void add(Coordinate c) {
        route.add(c);
    }

    public ArrayList<Coordinate> getRoute() {
        return route;
    }

    public boolean contains(Coordinate c) {
        return route.contains(c);
    }
    
    public int size(){
    	return route.size();
    }
}
