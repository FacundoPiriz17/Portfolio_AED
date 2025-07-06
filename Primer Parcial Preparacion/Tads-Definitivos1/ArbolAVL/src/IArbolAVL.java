public interface IArbolAVL<T> extends IArbolBB<T> {
    int getAltura();

    int getHojas();

    int getNivel(Comparable unaEtiqueta);

    int getTamano();

    IElementoAVL<T> getRaiz();
}
