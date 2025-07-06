package tda.Conjuntos;

public class Lista<T> implements ILista<T> {

    private Nodo<T> primero;

    public Lista() {
        primero = null;
    }
    /**
     * Metodo encargado de agregar un nodo al final de la lista.
     *
     * @param nodo - Nodo a agregar
     */
    public void insertar(Nodo<T> nodo){
        if(esVacia()){
            setPrimero(nodo);
        }
        else{
            Nodo<T> actual=primero;
            while(actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo);
        }

    }


    public void insertar (Comparable etiqueta, T dato ){
        Nodo<T> nuevo=new Nodo<T>();
        nuevo.setDato(dato);
        nuevo.setEtiqueta(etiqueta);
        if(buscar(etiqueta)==null) {
            insertar(nuevo);
        }
    }


    /**
     *
     *
     * Metodo encargado de buscar un nodo cuya clave es la indicada.
     *
     * @param clave - Clave del nodo a buscar.
     * @return El nodo encontrado. En caso de no encontrarlo, retornar null.
     */
    public Nodo<T> buscar(Comparable clave){
        if(esVacia()){
            return null;
        }
        Nodo<T> actual=primero;
        while(actual!=null){
            if(actual.getEtiqueta().compareTo(clave)==0){
                return actual;
            }
            else{
                actual = actual.getSiguiente();
            }
        }
        return null;
    }

    /**
     * Metodo encargado de eliminar un nodo cuya clave es la indicada.
     *
     * @param clave Clave del nodo a eliminar.
     * @return True en caso de que la eliminaci�n haya sido efectuada con �xito.
     */
    public boolean eliminar(Comparable clave){
        if(buscar(clave)!=null) {
            boolean encontrado = false;
            if (esVacia()) {
                return encontrado;
            } else {
                if (primero.getEtiqueta().compareTo(clave) == 0) {//Caso si es el primero
                    encontrado = true;
                    primero = primero.getSiguiente();
                } else {//caso si es en el medio
                    Nodo<T> aux1 = primero;
                    Nodo<T> aux2 = primero.getSiguiente();
                    while (aux2.getSiguiente() != null) {
                        if (aux2.getEtiqueta().compareTo(clave) == 0) {
                            encontrado = true;
                            aux1.setSiguiente(aux2.getSiguiente());//No pongo un break para que siga recorriendo en caso de que haya otro elemento para eliminar
                        }
                        aux1 = aux1.getSiguiente();
                        aux2 = aux2.getSiguiente();

                    }
                    if (aux2.getEtiqueta().compareTo(clave) == 0) {//Caso si es el ultimo
                        encontrado = true;
                        aux1.setSiguiente(null);
                    }
                }
            }
            return encontrado;
        }
        else{
            return false;
        }
    }

    /**
     * Metodo encargado de imprimir en consola las claves de los nodos
     * contenidos en la lista.
     * @return
     */
    public String imprimir(){
        StringBuilder str= new StringBuilder();
        if(!esVacia()){
            Nodo<T> actual=primero;
            while(actual!=null){
                str.append(actual.getEtiqueta()+"\n");
                actual=actual.getSiguiente();
            }

        }
        return str.toString();
    }

    /**
     * Retorna un String con las claves separadas por el separador pasado por
     * par�metro.
     *
     * @param separador Separa las claves
     * @return
     */
    public String imprimir(String separador){
        StringBuilder str= new StringBuilder();
        if(!esVacia()){
            Nodo<T> actual=primero;
            while(actual!=null){
                str.append(actual.getEtiqueta()+separador);
                actual=actual.getSiguiente();
            }

        }
        return str.toString();
    }

    /**
     * Retorna la cantidad de elementos de la lista. En caso de que la lista
     * este vac�a, retornar 0.
     *
     * @return Cantidad de elementos de la lista.
     */
    public int cantElementos(){
        int contador = 0;
        Nodo<T> actual = primero;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    /**
     * Indica si la lista contiene o no elementos.
     *
     * @return Si tiene elementos o no.
     */
    public boolean esVacia(){
        return primero == null;
    }


    public void setPrimero( Nodo<T> unNodo){
        primero = unNodo;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }
}
