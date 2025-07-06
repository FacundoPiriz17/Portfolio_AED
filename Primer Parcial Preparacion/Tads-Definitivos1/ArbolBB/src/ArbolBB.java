import java.util.LinkedList;
import java.util.List;
public class ArbolBB<T> implements IArbolBB<T> {
    private IElementoAB<T> raiz;
    
    @Override
    public boolean insertar(Comparable etiqueta, T unDato) {
        if (raiz == null) {
            raiz = new ElementoAB<>(etiqueta, unDato);
            return true;
        } else if (raiz.buscar(etiqueta) == null) {
            return raiz.insertar(new ElementoAB<>(etiqueta, unDato));
        } else {
            return false;
        }
    }

    @Override
    public T buscar(Comparable unaEtiqueta) {
        IElementoAB<T> elem = (raiz != null) ? raiz.buscar(unaEtiqueta) : null;
        return (elem != null) ? elem.getDatos() : null;
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (raiz != null) {
            raiz = raiz.eliminar(unaEtiqueta);
        }
    }

    @Override
    public List<T> preOrden() {
        List<T> lista = new LinkedList<>();
        if (raiz != null) {
            raiz.preOrden((LinkedList<T>) lista);
        }
        return lista;
    }

    @Override
    public List<T> inOrden() {
        List<T> lista = new LinkedList<>();
        if (raiz != null) {
            raiz.inOrden((LinkedList<T>) lista);
        }
        if (lista.isEmpty()) {
            return null;
        }
        return lista;
    }

    @Override
    public List<T> postOrden() {
        if (esVacio()) {
            return null;
        }
        List<T> lista = new LinkedList<>();
        if (raiz != null) {
            raiz.postOrden((LinkedList<T>) lista);
        }
        return lista;
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public boolean vaciar() {
        boolean estabaLleno = raiz != null;
        raiz = null;
        return estabaLleno;
    }

    public int getAltura(){
        if (esVacio()){
            return -1;
        }
        return raiz.getAltura();
    }

    public int getHojas(){
        if (esVacio()) {
            return 0;
        }
        return raiz.getHojas();
    }

    public int getNivel(Comparable unaEtiqueta){
        if (esVacio()) {
            return -1;
        }
        return raiz.getNivel(unaEtiqueta);
    }

    public int getTamano(){
        if (esVacio()) {
            return 0;
        }
        return raiz.obtenerTamano();
    }

    public Comparable obtenerMenorClave() {
        if (raiz != null) {
            return raiz.obtenerMenorClave();
        } else {
            return null;
        }
    }

    public Comparable obtenerMayorClave() {
        if (raiz != null) {
            return raiz.obtenerMayorClave();
        } else {
            return null;
        }
    }

    public Comparable obtenerClaveAnterior(Comparable clave) {
        if (raiz != null) {
            Comparable[] anterior = new Comparable[]{null};
            return raiz.obtenerClaveAnterior(clave, anterior);
        } else {
            return null;
        }
    }

    public int cantidadNodosPorNivel(int nivel) {
        if (raiz != null) {
            return raiz.cantidadNodosPorNivel(nivel);
        } else {
            return 0;
        }
    }

    public List<String> listarHojasConNivel() {
        List<String> lista = new LinkedList<>();
        if (raiz != null) {
            raiz.listarHojasConNivel(0, lista);
        }
        return lista;
    }

    public boolean esDeBusqueda() {
        return (raiz == null) || raiz.esDeBusqueda();
    }

    public List<Comparable> obtenerCamino(Comparable unaEtiqueta) {
        LinkedList<Comparable> camino = new LinkedList<>();
        if (raiz != null) {
            boolean encontrado = raiz.obtenerCamino(unaEtiqueta, camino);
            if (encontrado) {
                return camino;
            }
        }
        return null;
    }

    public List<Comparable> obtenerCaminoEntre(Comparable desde, Comparable hasta) {
        if (raiz == null) {
            return null;
        }
        List<Comparable> camino = new LinkedList<>();

        if (raiz.buscar(desde) == null) {
            return camino;
        }

        if (raiz.buscar(hasta) == null ) {
            return camino;
        }

        LinkedList<Comparable> caminoDesdeRaizA = new LinkedList<>();
        LinkedList<Comparable> caminoDesdeRaizB = new LinkedList<>();

        if (!raiz.obtenerCamino(desde, caminoDesdeRaizA) || !raiz.obtenerCamino(hasta, caminoDesdeRaizB)) {
            return camino;
        }

        int i = 0;
        while (i < caminoDesdeRaizA.size() && i < caminoDesdeRaizB.size() &&
                caminoDesdeRaizA.get(i).equals(caminoDesdeRaizB.get(i))) {
            i++;
        }


        for (int j = caminoDesdeRaizA.size() - 1; j >= i; j--) {
            camino.add(caminoDesdeRaizA.get(j));
        }

        for (int j = i - 1; j < caminoDesdeRaizB.size(); j++) {
            camino.add(caminoDesdeRaizB.get(j));
        }
        return camino;
    }

}
