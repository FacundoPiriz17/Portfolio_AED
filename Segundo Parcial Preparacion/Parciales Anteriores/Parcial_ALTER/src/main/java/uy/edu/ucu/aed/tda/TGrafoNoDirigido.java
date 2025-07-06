package uy.edu.ucu.aed.tda;

import java.util.*;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {
    protected Rutas lasAristas = new Rutas();

    public TGrafoNoDirigido(Collection<Ciudad> vertices, Collection<Ruta> aristas) {
        super(vertices, new ArrayList<>());
        lasAristas.insertarAmbosSentidos(aristas);
    }

    @Override
    public boolean insertarArista(Ruta arista) {
        Ruta arInv = new Ruta(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto(), arista.getEsPrioritaria());
        List<Ruta> r = new ArrayList<>( );
        r.add(arista);
        lasAristas.insertarAmbosSentidos(r);
        return true; //(super.insertarArista(arista) && super.insertarArista(arInv));
    }

    public Rutas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        TGrafoNoDirigido arbolPrim = new TGrafoNoDirigido(this.getVertices().values(), new LinkedList<>());
        double costoTotal = 0.0d;

        if (this.getVertices().isEmpty()) {
            return arbolPrim;
        }

        Rutas aristas = this.getLasAristas();
        LinkedList<Comparable> vertices = new LinkedList<>();
        for (Ciudad vertice : this.getVertices().values()) {
            vertices.add(vertice.getEtiqueta());
        }

        Collection<Comparable> U = new LinkedList<>();

        U.add(vertices.removeFirst());
        while (!vertices.isEmpty()) {
            Ruta a = aristas.buscarMin(U, vertices);
            Ciudad v = this.getVertices().get(a.getEtiquetaDestino());
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
        LinkedList<Ruta> aristasKruskal = new LinkedList(); //Aqui se almacenaran las aristas seleccionadas.
        Map<Comparable, Ciudad> vertices = getVertices();

        if (!vertices.isEmpty()) {
            desvisitarVertices();
            HashMap<Comparable, LinkedList<Ciudad>> colecciones = new HashMap(vertices.size());
            LinkedList<Ciudad> colTemp;

            //Creamos una "coleccion" para cada arista
            for (Ciudad v : vertices.values()) {
                colTemp = new LinkedList();
                colTemp.add(v);
                colecciones.put(v.getEtiqueta(), colTemp);
            }

            //Ordenamos todas las aristas del grafo de menor costo a mayor
            LinkedList<Ruta> aristasOrdenadas = new LinkedList<>();
            forAristas:
            for (Ruta a : lasAristas) {
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
            Ruta menorArista;
            LinkedList<Ciudad> colOrigen, colDestino;
            while (!aristasOrdenadas.isEmpty()) {
                menorArista = aristasOrdenadas.pollFirst();
                colOrigen = colecciones.get(menorArista.etiquetaOrigen);
                colDestino = colecciones.get(menorArista.etiquetaDestino);
                if (colOrigen != colDestino) {
                    colOrigen.addAll(colDestino);
                    for (Ciudad v : colDestino) {
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
    public Collection<Ciudad> bea(Comparable etiquetaOrigen) {
        if (this.getVertices().isEmpty()) {
            return null;
        } else {
            this.desvisitarVertices();
            if (this.existeVertice(etiquetaOrigen)) {
                Ciudad vert = super.buscarVertice(etiquetaOrigen);
                Collection<Ciudad> verts = new LinkedList<Ciudad>();
                vert.bea(verts);
                return verts;
            }
            return null;
        }
    }

    @Override
    public boolean esConexo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean conectados(Ciudad origen, Ciudad destino) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
