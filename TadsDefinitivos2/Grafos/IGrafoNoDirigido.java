package uy.edu.ucu.aed;

import java.util.Collection;
import java.util.Map;

public interface IGrafoNoDirigido {

    public Collection<TVertice> bea();

    public Collection<TVertice> bea(Comparable etiquetaOrigen);

    Boolean esConexo();

    public boolean conectados(TVertice origen, TVertice destino);

    public TGrafoNoDirigido Prim();

    public TGrafoNoDirigido Kruskal();
}
