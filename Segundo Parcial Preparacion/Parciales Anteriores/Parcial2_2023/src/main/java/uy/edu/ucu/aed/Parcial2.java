package uy.edu.ucu.aed;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parcial2
{    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // 1 - Cargar el Grafo
        TGrafoRedDatos grafo = UtilGrafos.cargarGrafo("src/main/dispositivos.txt", "src/main/conexiones.txt", false, TGrafoRedDatos.class);

        // 2 - Verificar que los componentes se encuentren conectados
        boolean conectados = grafo.conectados("nodoX","nodoY");
        
        // 3 - Leer y cargar archivo mediciones.txt
        TDato[] datos = Parcial2.cargarMediciones("src/main/mediciones.txt");
        
        // 4 - Obtener dato de mayor medicion.
        TMedidor medidor = new TMedidor();
        TDato mayorMedicion = medidor.obtenerMayorMedicion(datos);
        
        // 5 - Emitir archivo de salida salida.txt
        // COMPLETAR CÓDIGO
        List<String> salida = new ArrayList<>();

        salida.add("¿Están conectados nodoX y nodoY?: " + (conectados ? "Sí" : "No"));

        if (mayorMedicion != null) {
            salida.add("Mayor medición:");
            salida.add("Fecha: " + mayorMedicion.getFecha());
            salida.add("Valor: " + mayorMedicion.getValor());
        } else {
            salida.add("No se encontraron mediciones.");
        }

        ManejadorArchivosGenerico.escribirArchivo("src/main/java/salida.txt", salida.toArray(new String[0]));
    }

    private static TDato[] cargarMediciones(String rutaAlArchivo) {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(rutaAlArchivo, false);
        
        TDato[] mediciones = new TDato[lineas.length];
        for (int i = 0; i < lineas.length; i++) {
            String[] datos = lineas[i].split(",");
            mediciones[i] = new TDato(Double.parseDouble(datos[1]), Integer.parseInt(datos[0]));
        }

        return mediciones;
    }
}
