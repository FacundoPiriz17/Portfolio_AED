package tda.Conjuntos;

import tda.Conjuntos.INodo;


public class Nodo<T> implements INodo<T> {

    private Comparable dato;

    private Nodo<T> siguiente;

    public void setDato(T dato){
        this.dato= (Comparable) dato;
    }

    public void setEtiqueta(Comparable etiqueta){
        this.dato = etiqueta;
    }


    public T getDato(){
        return (T) this.dato;
    }

    public Nodo<T> getSiguiente(){
        return this.siguiente;
    }


    /**
     * "engancha" otro nodo a continuacion
     *
     */
    public void setSiguiente(Nodo<T> nodo){
        this.siguiente = nodo;
    }

    /**
     * Imprime los datos del nodo
     */



    public void imprimir(){
        System.out.println(this.dato);
    }

    /**
     * Imprime la etiqueta del nodo
     */

    public void imprimirEtiqueta(){
        System.out.println(this.getEtiqueta());
    }


    /**
     * Retorna la etiqueta del nodo
     *
     * @return etiqueta del nodo
     */
    public Comparable getEtiqueta(){
        return this.dato;
    }
//	/**
//	 *
//	 * @param unNodo
//	 * @return devueve -1 si this tiene una clave menor, 0 si son iguales, 1 si es mayor
//	 */
//	public int compareTo(INodo<E> unNodo);

    /**
     *
     * @param etiqueta
     * @return devueve -1 si this tiene una etiqueta menor, 0 si son iguales, 1
     * si es mayor
     */
    public int compareTo(Comparable etiqueta){
        return etiqueta.compareTo(this.dato);
    }
}
