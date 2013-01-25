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
