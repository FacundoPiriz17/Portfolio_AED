package uy.edu.ucu.aed;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {
    protected TAristas lasAristas = new TAristas();

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        TGrafoNoDirigido arbolPrim = new TGrafoNoDirigido(this.getVertices().values(), new LinkedList<>());
        double costoTotal = 0.0d;

        if (this.getVertices().isEmpty()) {
            return arbolPrim;
        }

        TAristas aristas = this.getLasAristas();
        LinkedList<Comparable> vertices = new LinkedList<>();
        for (TVertice vertice : this.getVertices().values()) {
            vertices.add(vertice.getEtiqueta());
        }

        Collection<Comparable> U = new LinkedList<>();

        U.add(vertices.removeFirst());
        while (!vertices.isEmpty()) {
            TArista a = aristas.buscarMin(U, vertices);
            TVertice v = this.getVertices().get(a.getEtiquetaDestino());
            vertices.remove(v.getEtiqueta());
            U.add(v.getEtiqueta());
            arbolPrim.insertarArista(a);
            costoTotal += a.getCosto();
        }
        System.out.println(costoTotal);
        return arbolPrim;
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        LinkedList<TArista> aristasKruskal = new LinkedList(); //Aqui se almacenaran las aristas seleccionadas.
        Map<Comparable, TVertice> vertices = getVertices();

        if (!vertices.isEmpty()) {
            desvisitarVertices();
            HashMap<Comparable, LinkedList<TVertice>> colecciones = new HashMap(vertices.size());
            LinkedList<TVertice> colTemp;

            //Creamos una "coleccion" para cada arista
            for (TVertice v : vertices.values()) {
                colTemp = new LinkedList();
                colTemp.add(v);
                colecciones.put(v.getEtiqueta(), colTemp);
            }

            //Ordenamos todas las aristas del grafo de menor costo a mayor
            LinkedList<TArista> aristasOrdenadas = new LinkedList();
            forAristas:
            for (TArista a : lasAristas) {
                if (aristasOrdenadas.isEmpty() || aristasOrdenadas.getFirst().getCosto() > a.getCosto()) {
                    aristasOrdenadas.addFirst(a);
                    continue;
                }
                for (int i = 1; i < aristasOrdenadas.size(); i++) {
                    if (aristasOrdenadas.get(i).getCosto() > a.getCosto()) {
                        aristasOrdenadas.add(i - 1, a);
                        continue forAristas;
                    }
                }
                aristasOrdenadas.add(a);
            }

            //Conectamos las colecciones de vertices (no conectados) mediante la arista de menor costo posible
            TArista menorArista;
            LinkedList<TVertice> colOrigen, colDestino;
            while (!aristasOrdenadas.isEmpty()) {
                menorArista = aristasOrdenadas.pollFirst();
                colOrigen = colecciones.get(menorArista.etiquetaOrigen);
                colDestino = colecciones.get(menorArista.etiquetaDestino);
                if (colOrigen != colDestino) {
                    colOrigen.addAll(colDestino);
                    for (TVertice v : colDestino) {
                        if (colecciones.get(v.getEtiqueta()) != colOrigen) {
                            colecciones.replace(v.getEtiqueta(), colOrigen);
                        }
                    }
                    aristasKruskal.add(menorArista);
                }
            }
        }

        TGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices.values(), aristasKruskal);
        return grafo;
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
         if (this.getVertices().isEmpty()) {
            return null;
        } else {
            this.desvisitarVertices();
            if(this.existeVertice(etiquetaOrigen))
            {
                TVertice vert= super.buscarVertice(etiquetaOrigen);
                Collection<TVertice> verts = new LinkedList<TVertice>();
                vert.bea(verts);
                return verts;
            }
            return null;
        }
    }

    @Override
    public Boolean esConexo() {
        if (!getVertices().values().iterator().hasNext()) return null;
        desvisitarVertices();
        TVertice verticeCualquiera = getVertices().values().iterator().next();
        LinkedList<TVertice> vertices = new LinkedList<>();
        verticeCualquiera.bea(vertices);
        if (vertices.size() != getVertices().size()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean conectados(TVertice origen, TVertice destino) {
        if (origen == null || destino == null || getVertices().get(origen) == null || getVertices().get(destino) == null) {
            return false;
        }
        desvisitarVertices();
        LinkedList<IVertice> hijosEnBPF = new LinkedList<>();
        getVertices().get(origen).bpf(hijosEnBPF);
        if (hijosEnBPF.contains(getVertices().get(destino)))
        {
            return true;
        }
        return false;
    }

    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        LinkedList<TVertice> puntos = new LinkedList<>();
        if (!this.getVertices().containsKey(etOrigen)) {
            System.out.println("El vertice no se encuentra");
            return puntos;
        }

        for (TVertice v : this.getVertices().values()) {
            v.setVisitado(false);
            v.setNumBp(-1);
            v.setNumBajo(-1);
        }

        int[] tiempo = {0};
        puntosArticulacionDFS(this.getVertices().get(etOrigen), null, tiempo, puntos);
        return puntos;
    }

    private void puntosArticulacionDFS(TVertice actual, TVertice padre, int[] tiempo, LinkedList<TVertice> puntos) {
        actual.setVisitado(true);
        actual.setNumBp(tiempo[0]);
        actual.setNumBajo(tiempo[0]);
        tiempo[0]++;

        int hijos = 0;
        boolean esPunto = false;

        for (Object adyacencia1 : actual.getAdyacentes()) {
            TAdyacencia adyacencia = (TAdyacencia) adyacencia1;
            TVertice ady = adyacencia.getDestino();
            if (!ady.getVisitado()) {
                hijos++;
                puntosArticulacionDFS(ady, actual, tiempo, puntos);
                actual.setNumBajo(Math.min(actual.getNumBajo(), ady.getNumBajo()));

                if (padre != null && ady.getNumBajo() >= actual.getNumBp()) {
                    esPunto = true;
                }
            } else if (ady != padre) {
                actual.setNumBajo(Math.min(actual.getNumBajo(), ady.getNumBp()));
            }
        }

        if ((padre == null && hijos > 1) || (padre != null && esPunto)) {
            puntos.add(actual);
        }
    }

}
