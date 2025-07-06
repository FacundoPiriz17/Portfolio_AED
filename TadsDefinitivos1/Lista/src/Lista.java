package uy.edu.ucu.aed.tdas;

public class Lista<T> implements ILista<T> {

    protected class Nodo<T> {

        private final Comparable etiqueta;
        private T dato;
        private Nodo<T> siguiente = null;

        public Nodo(Comparable etiqueta, T dato ) {
            this.etiqueta = etiqueta;
            this.dato = dato;
        }

        public Nodo<T> getSiguiente() {
            return siguiente;
        }

        public T getDato() {
            return dato;
        }
    }

    private Nodo<T> primero;

    public Lista() {
        primero = null;
    }

    @Override
    public void insertar(T dato, Comparable clave) {
        Nodo<T> nuevoNodo = new Nodo<T>(clave, dato);

        if (primero == null) {
            primero = nuevoNodo;
        } else {
            Nodo<T> actual = primero;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    @Override
    public T buscar(Comparable clave) {
        int i = 0;
        Nodo<T> actual = primero;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
            i++;

            if (clave.equals(actual.dato)) {
                return actual.dato;
            }
        }

        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        int i = 0;
        Nodo<T> actual = primero;
        Nodo<T> anterior = null;
        while (actual != null) {
            if (((Comparable) actual.dato).compareTo(clave) == 0) {
                if (anterior == null) {
                    primero = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
        
    }

    @Override
    public String imprimir() {
        int i = 0;
        Nodo<T> actual = primero;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
            i++;
            System.out.println(actual.dato);
        }

        return null;
    }

    @Override
    public String imprimir(String separador) {
        int i = 0;
        Nodo<T> actual = primero;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
            i++;

            System.out.println(separador + actual.dato);
        }

        return null;
    }

    @Override
    public int cantElementos() {
        if (primero == null) {
            return 0;
        }
        int i = 0;
        Nodo<T> actual = primero;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
            i++;
        }
        return i;
    }

    @Override
    public boolean esVacia() {
        if (primero == null) {
            return true;
        }
        return false;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }
}
