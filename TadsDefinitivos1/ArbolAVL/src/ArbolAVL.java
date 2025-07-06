public class ArbolAVL<T> implements IArbolAVL<T> {

    private IElementoAVL<T> raiz;

    @Override
    public boolean insertar(Comparable etiqueta, T unDato) {
        ElementoAVL<T> nuevoElemento = new ElementoAVL<>(etiqueta, unDato);
        if (raiz == null) {
            raiz = nuevoElemento;
            return true;
        } else if (raiz.buscar(etiqueta) == null) {
            raiz = raiz.insertar(nuevoElemento);
            return true;
        }
        return false;
    }

    @Override
    public IElementoAVL<T> getRaiz() {
        return raiz;
    }

    @Override
    public T buscar(Comparable unaEtiqueta) {
        IElementoAVL<T> elem = (raiz != null) ? raiz.buscar(unaEtiqueta) : null;
        return ((elem != null) ? elem.getDato() : null);
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (raiz != null) {
            raiz = raiz.eliminar(unaEtiqueta);
        }
    }

    @Override
    public String preOrden() {
        if (raiz != null) {
            return raiz.preOrden();
        }
        return null;
    }

    @Override
    public String inOrden() {
        if (raiz != null) {
            return raiz.inOrden();
        }
        return null;
    }

    @Override
    public String postOrden() {
        if (raiz != null) {
            return raiz.postOrden();
        }
        return null;
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public boolean vaciar() {
        boolean estabaLleno = (raiz != null);
        raiz = null;
        return estabaLleno;
    }

    @Override
    public int getAltura() {
        return (raiz == null) ? -1 : raiz.getAltura();
    }

    @Override
    public int getHojas() {
        return (raiz == null) ? 0 : raiz.getHojas();
    }

    @Override
    public int getNivel(Comparable unaEtiqueta) {
        return (raiz == null) ? -1 : raiz.getNivel(unaEtiqueta);
    }

    @Override
    public int getTamano() {
        return (raiz == null) ? 0 : raiz.obtenerTamano();
    }
}
