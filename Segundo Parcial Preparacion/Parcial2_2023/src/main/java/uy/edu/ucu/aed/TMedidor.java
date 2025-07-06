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
        for (TDato dato : datos){
            if(dato.getValor()>dato.getValor()){
                return dato;
            }
        }
        return null;
    }


}
