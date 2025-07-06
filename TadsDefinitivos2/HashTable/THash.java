package uy.edu.ucu.aed.tdas;

public class THash<K, V> implements IHash<K, V> {

    // Implementación de la tabla hash
    // Aquí se pueden definir los atributos necesarios, como un array de buckets, etc.
    private K[] claves;
    private V[] valores;

    private int tamanio;
    private int comparacionesInsercion = 0;
    private int comparacionesBusquedaExitosa = 0;
    private int comparacionesBusquedaNoExitosa = 0;
    private int cantidadBusquedasExitosas = 0;
    private int cantidadBusquedasNoExitosas = 0;

    public THash(int tamanio) {
        this.claves = (K[]) new Object[tamanio];
        this.valores = (V[]) new Object[tamanio];
        this.tamanio = tamanio;
    }

    @Override
    public V buscar(K unaClave) {
        int indice = funcionHashing(unaClave);
        for (int i = 0; i < tamanio; i++) {
            int nuevoIndice = (indice + i) % tamanio;
            if (claves[nuevoIndice] == null) {
                comparacionesBusquedaNoExitosa += (i + 1);
                cantidadBusquedasNoExitosas++;
                return null;
            }
            if (claves[nuevoIndice].equals(unaClave)) {
                comparacionesBusquedaExitosa += (i + 1);
                cantidadBusquedasExitosas++;
                return valores[nuevoIndice];
            }
        }
        comparacionesInsercion += tamanio;
        cantidadBusquedasNoExitosas++;
        return null;
    }

    @Override
    public boolean insertar(K unaClave, V unValor) {
        int indice = funcionHashing(unaClave);
        for (int i = 0; i < tamanio; i++) {
            int nuevoIndice = (indice + i) % tamanio;
            comparacionesInsercion++;
            if (claves[nuevoIndice] == null || claves[nuevoIndice].equals(unaClave)) {
                claves[nuevoIndice] = unaClave;
                valores[nuevoIndice] = unValor;
                return true;
            }
        }
        return false;
    }

    
    /**
     * Función de hashing que convierte una clave en un índice de la tabla hash.
     * 
     * @param clave la clave a convertir en índice.
     * @return el índice correspondiente a la clave.
    */

    protected int funcionHashing(K clave) {
        int hash = clave.hashCode() % tamanio;
        if (hash < 0) {
            hash += tamanio;
        }
        return hash;
    }

    public int getComparacionesInsercion() {
        return comparacionesInsercion;
    }

    public int getComparacionesBusquedaExitosa() {
        return comparacionesBusquedaExitosa;
    }

    public int getComparacionesBusquedaNoExitosa() {
        return comparacionesBusquedaNoExitosa;
    }

    public int getCantidadBusquedasExitosas() {
        return cantidadBusquedasExitosas;
    }

    public int getCantidadBusquedasNoExitosas() {
        return cantidadBusquedasNoExitosas;
    }


    
}
