public interface IArbolBB<T> {

    boolean insertar(Comparable etiqueta, T unDato);

    T buscar(Comparable unaEtiqueta);

    void eliminar(Comparable unaEtiqueta);

    String preOrden();

    String inOrden();

    String postOrden();

    boolean esVacio();

    boolean vaciar();
}
