public interface IElementoAVL<T> {

    Comparable getEtiqueta();

    IElementoAVL<T> getHijoIzq();

    IElementoAVL<T> getHijoDer();

    void setHijoIzq(IElementoAVL<T> elemento);

    void setHijoDer(IElementoAVL<T> elemento);

    IElementoAVL<T> buscar(Comparable unaEtiqueta);

    String preOrden();

    String inOrden();

    String postOrden();

    T getDato();

    int obtenerTamano();

    int getAltura();

    int getHojas();

    int getNivel(Comparable unaEtiqueta);

    int getBalance();

    void actualizarAltura();

    IElementoAVL<T> insertar(IElementoAVL<T> elemento);

    IElementoAVL<T> eliminar(Comparable unaEtiqueta);
}
