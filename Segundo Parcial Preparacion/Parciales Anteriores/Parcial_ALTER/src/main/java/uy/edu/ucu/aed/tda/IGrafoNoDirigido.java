package uy.edu.ucu.aed.tda;

import java.util.Collection;

public interface IGrafoNoDirigido {

    public Collection<Ciudad> bea();

    public Collection<Ciudad> bea(Comparable etiquetaOrigen);

    boolean esConexo();

    public boolean conectados(Ciudad origen, Ciudad destino);

    public TGrafoNoDirigido Prim();

    public TGrafoNoDirigido Kruskal();
}
