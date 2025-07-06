package tda.ListaEnlazada;

public class ListaOrdenada<T> extends Lista<T> {

    @Override
    public void insertar(T dato, Comparable clave) {
        Nodo nuevoNodo = new Nodo(clave, dato);


        if (getPrimero() == null) {
            nuevoNodo.setSiguiente(null);
            this.SetPrimero(nuevoNodo);
        }
        else {

            if (this.getCodigoPrimero().compareTo(clave) >= 0) {
                nuevoNodo.setSiguiente(getPrimero());
                this.SetPrimero(nuevoNodo);
                return;
            }

            Nodo actual = getPrimero();
            while (actual.getSiguiente() != null && this.getCodigo(actual.getSiguiente()).compareTo(clave) < 0){
                actual = actual.getSiguiente();
            }

            nuevoNodo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevoNodo);
        }
    }

}
