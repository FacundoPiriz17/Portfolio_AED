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
public class TGrafoRedDatos extends TGrafoNoDirigido implements ITGrafoRedDatos
{

    public TGrafoRedDatos(Collection<TVertice> vertices, Collection<TArista> aristas)
    {
        // Descomentar la siguiente línea luego de elegir una superclase
        super(vertices, aristas);
    }

    @Override
    public boolean conectados(Comparable a, Comparable b)
    {
        desvisitarVertices();
        TVertice verticeA = this.buscarVertice(a);
        TVertice verticeB = this.buscarVertice(b);
        if (verticeA == null || verticeB == null) {
            return false;
        }
        return verticeA.conectadoCon(verticeB);
    }


}
