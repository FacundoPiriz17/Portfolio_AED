package uy.edu.ucu.aed;

/*
 * NO LICENCE 
 * Author: Ing. Agustin Paredes
 */

import java.util.Collection;
import java.util.LinkedList;


/**
 *
 * @author agustinp
 */
public class TMedidor
{
    public TDato obtenerMayorMedicion(TDato[] datos)
    {
        if (datos == null || datos.length == 0) {
            return null;
        }

        TDato mayor = datos[0];
        for (TDato dato : datos) {
            if (dato.getValor() > mayor.getValor()) {
                mayor = dato;
            }
        }
        return mayor;
    }


}
