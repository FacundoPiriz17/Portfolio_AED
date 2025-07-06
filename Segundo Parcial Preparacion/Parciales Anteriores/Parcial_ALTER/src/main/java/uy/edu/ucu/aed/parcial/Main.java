package uy.edu.ucu.aed.parcial;

import uy.edu.ucu.aed.tda.Ciudad;
import uy.edu.ucu.aed.tda.Ruta;

public class Main {
    public static void main(String[] args) {

        RedDistribucion red = new RedDistribucion();
        Ciudad mvd = new Ciudad("Montevideo");
        Ciudad c1 = new Ciudad("Canelones");
        Ciudad c2 = new Ciudad("Florida");
        Ciudad c3 = new Ciudad("Rivera");

        red.insertarVertice(mvd);
        red.insertarVertice(c1);
        red.insertarVertice(c2);
        red.insertarVertice(c3);

        red.insertarArista(new Ruta("Montevideo", "Canelones", 5, true));
        red.insertarArista(new Ruta("Montevideo", "Florida", 10, false));
        red.insertarArista(new Ruta("Canelones", "Rivera", 3, false));
        red.insertarArista(new Ruta("Florida", "Rivera", 2, true));

        System.out.println("Rutas seleccionadas:");
        for (Ruta ruta : red.construirRedMinimaConPrioridad()) {
            System.out.println(ruta.getEtiquetaOrigen() + " -> " +
                    ruta.getEtiquetaDestino() + " (" +
                    ruta.getCosto() + ")");
        }
    }
}
