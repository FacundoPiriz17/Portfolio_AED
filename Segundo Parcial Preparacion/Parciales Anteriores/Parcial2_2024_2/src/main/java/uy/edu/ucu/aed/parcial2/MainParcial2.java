package uy.edu.ucu.aed.parcial2;

import uy.edu.ucu.aed.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Algoritmo y Estrucutras de Datos
 * Parcial 2 - Parte 3
 *
 */
public class MainParcial2 {
    public static void main(String[] args) {
        // Cargar grafo a partir de archivos de entrada
        TGrafoDeLaRed grafo = UtilGrafos.cargarGrafo("./src/main/java/uy/edu/ucu/aed/parcial2/vertices.txt",
                "./src/main/java/uy/edu/ucu/aed/parcial2/aristas.txt", false, TGrafoDeLaRed.class,
                TVerticeDeLaRed.class);

        // Calculo de todos los caminos entre dos vertices
        TCaminos<TEstacionDeLaRed> caminos = grafo.caminosDesdeHasta("Vertice_3", "Vertice_4");

        List<TCamino<TEstacionDeLaRed>> listaOrdenada = new ArrayList<>(caminos.getCaminos());
        listaOrdenada.sort(Comparator.comparingDouble(TCamino::getCostoTotal));

        // Preparar líneas para el archivo de salida
        List<String> lineasSalida = new ArrayList<>();
        for (TCamino<TEstacionDeLaRed> camino : listaOrdenada) {
            lineasSalida.add(camino.imprimirEtiquetas() + " - Costo: " + camino.getCostoTotal());
        }

        // Escribir archivo de salida con el resultado de la llamada anterior, con los
        // caminos ordenados de menor a mayor costo, uno por línea.
        ManejadorArchivosGenerico.escribirArchivo("salida.txt", lineasSalida.toArray(new String[0]));

    }
}
