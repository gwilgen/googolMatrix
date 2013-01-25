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
public class EndException extends Exception {

    private Route route = null;

    /**
     * Creates a new instance of <code>EndException</code> without detail message.
     */
    public EndException() {
    }

    /**
     * Constructs an instance of <code>EndException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public EndException(String msg, Route r) {
        super(msg);
        route = r;
    }

    public Route getRoute() {
        return route;
    }
}
