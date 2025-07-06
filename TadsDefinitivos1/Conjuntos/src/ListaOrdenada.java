package tda.Conjuntos;

import tda.Conjuntos.Nodo;

public class ListaOrdenada<T> extends Lista<T> {
    public void insertar(Nodo<T> unNodo) {
        Nodo<T> aux1= this.getPrimero();
        if(aux1==null){
            this.setPrimero(unNodo);
        }
        else{
            if(this.buscar(unNodo.getEtiqueta())==null) {//Para evitar repeticiones
                Nodo<T> aux2 = aux1.getSiguiente();
                //Caso en el cual el nodo va antes del primero
                if (aux1.getEtiqueta().compareTo(unNodo.getEtiqueta()) > 0) {
                    unNodo.setSiguiente(aux1);
                    this.setPrimero(unNodo);
                }//Caso cuando el nodo va en medio
                else {
                    boolean insertado = false;
                    while (aux2 != null) {
                        if (aux2.getEtiqueta().compareTo(unNodo.getEtiqueta()) > 0) {
                            unNodo.setSiguiente(aux2);
                            aux1.setSiguiente(unNodo);
                            insertado = true;
                            break;
                        }
                        aux1 = aux2;
                        aux2 = aux2.getSiguiente();
                    }
                    if (!insertado) {
                        aux1.setSiguiente(unNodo);
                    }

                }
            }
        }
    }
}
