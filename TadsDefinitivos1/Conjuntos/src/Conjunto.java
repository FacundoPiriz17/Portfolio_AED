package tda.Conjuntos;

public class Conjunto<T> extends ListaOrdenada<T> implements IConjunto<T>{

    public Conjunto(){

    }
    @Override
    public void insertar(Nodo<T> nodo){
        super.insertar(nodo);
    }
    public IConjunto<T> union(IConjunto<T> otroConjunto){
        IConjunto nuevoConjunto = new Conjunto<>();
        Nodo<T> ele0 = this.getPrimero();
        while(ele0!=null){
            Nodo<T> nuevo=new Nodo<T>();
            nuevo.setDato(ele0.getDato());
            nuevo.setEtiqueta(ele0.getEtiqueta());
            nuevoConjunto.insertar(nuevo);
            ele0=ele0.getSiguiente();
        }
        Nodo<T> ele1 = otroConjunto.getPrimero();
        while(ele1!=null){
            Nodo<T> nuevo=new Nodo<>();
            nuevo.setDato(ele1.getDato());
            nuevo.setEtiqueta(ele1.getEtiqueta());
            nuevoConjunto.insertar(nuevo);
            ele1=ele1.getSiguiente();
        }
        return nuevoConjunto;
    }

    public IConjunto<T> complemento(IConjunto<T> conjuntoUniversal) {
        Conjunto<T> resultado = new Conjunto<>();

        Nodo<T> nodoU = ((Lista<T>) conjuntoUniversal).getPrimero();
        Nodo<T> nodoA = this.getPrimero();

        while (nodoU != null && nodoA != null) {
            int comparador = nodoU.getEtiqueta().compareTo(nodoA.getEtiqueta());

            if (comparador < 0) {
                resultado.insertar(nodoU.getEtiqueta(), nodoU.getDato());
                nodoU = nodoU.getSiguiente();
            } else if (comparador > 0) {

                nodoA = nodoA.getSiguiente();
            } else {

                nodoU = nodoU.getSiguiente();
                nodoA = nodoA.getSiguiente();
            }
        }

        while (nodoU != null) {
            resultado.insertar( nodoU.getEtiqueta(), nodoU.getDato());
            nodoU = nodoU.getSiguiente();
        }

        return resultado;
    }
    public IConjunto<T> interseccion(IConjunto<T> otroConjunto) {
        IConjunto nuevoConjunto = new Conjunto<>();
        Nodo<T> ele0 = otroConjunto.getPrimero();

        while (ele0 != null) {
            Nodo<T> clonado = new Nodo<>();
            clonado.setDato(ele0.getDato());
            clonado.setEtiqueta(ele0.getEtiqueta());
            if (this.buscar(ele0.getEtiqueta()) != null) {
                nuevoConjunto.insertar(clonado);
            }
            ele0 = ele0.getSiguiente();
        }
        return nuevoConjunto;
    }

    public IConjunto<T> diferenciaSimetrica(IConjunto otroConjunto) {
        Conjunto<T> resultado = new Conjunto<>();

        Nodo<T> nodo1 = this.getPrimero();
        Nodo<T> nodo2 = ( otroConjunto).getPrimero();

        while (nodo1 != null && nodo2 != null) {
            int comparador = nodo1.getEtiqueta().compareTo(nodo2.getEtiqueta());

            if (comparador < 0) {
                resultado.insertar(nodo1.getEtiqueta(), nodo1.getDato());
                nodo1 = nodo1.getSiguiente();
            } else if (comparador > 0) {
                resultado.insertar(nodo2.getEtiqueta(), nodo2.getDato());
                nodo2 = nodo2.getSiguiente();
            } else {

                nodo1 = nodo1.getSiguiente();
                nodo2 = nodo2.getSiguiente();
            }
        }

        while (nodo1 != null) {
            resultado.insertar(nodo1.getEtiqueta(), nodo1.getDato());
            nodo1 = nodo1.getSiguiente();
        }

        while (nodo2 != null) {
            resultado.insertar(nodo2.getEtiqueta(), nodo2.getDato());
            nodo2 = nodo2.getSiguiente();
        }
        return resultado;
    }

}
