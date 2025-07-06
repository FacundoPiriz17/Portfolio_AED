package uy.edu.ucu.aed.tda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@SuppressWarnings({"rawtypes"})
public class TCaminos {

    private Collection<TCamino> caminos;
    private Map<Comparable, Comparable> padre = new HashMap<>();

    public TCaminos() {
        this.caminos = new LinkedList<>();
        padre = new HashMap<>();
    }

    public String imprimirCaminos() {
        StringBuilder sb = new StringBuilder();
        for (TCamino camino : caminos) {
            sb.append(camino.imprimirEtiquetas() + "\n");
        }
        return sb.toString();
    }

    public void imprimirCaminosConsola() {
        System.out.println(imprimirCaminos());
    }

    public Collection<TCamino> getCaminos() {
        return caminos;
    }

    /**
     * Create un componente formado por el vertice
     */
    public void crearComponente(Comparable vertice) {
        if (!padre.containsKey(vertice)) {
            padre.put(vertice, vertice);
        }
    }

    private Comparable buscar(Comparable item) {
        if (!padre.get(item).equals(item)) {
            padre.put(item, buscar(padre.get(item)));
        }
        return padre.get(item);
    }

    /**
     * Une los componentes el cual a y b pertenecen
     */
    public void conectar(Comparable a, Comparable b) {
        Comparable rootA = buscar(a);
        Comparable rootB = buscar(b);
        if (!rootA.equals(rootB)) {
            padre.put(rootA, rootB);
        }
    }

    /**
     * Devuelve true si los vertices a y b están en el mismo componente
     */
    public boolean conectados(Comparable a, Comparable b) {
        return buscar(a).equals(buscar(b));
    }
}
