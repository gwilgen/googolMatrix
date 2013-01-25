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
public class Coordinate {

    private ArrayList<Integer> coord;

    public boolean equals(Coordinate other) {
        boolean b = true;
        for (int i = 0; i < coord.size(); i++) {
            if (!coord.get(i).equals(other.getCoord().get(i))) {
                b = false;
                break;
            }
        }
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinate) {
            return equals((Coordinate) o);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.coord != null ? this.coord.hashCode() : 0);
        return hash;
    }

    public Coordinate(ArrayList<Integer> param) {
        coord = param;
    }

    public ArrayList<Integer> getCoord() {
        return coord;
    }

    @Override
    public String toString() {
        String s = new String();
        for (int i = 0; i < coord.size(); i++) {
            s += coord.get(i)+(coord.size()==(i+1)?"":",");
        }
        return s;
    }
}
