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
