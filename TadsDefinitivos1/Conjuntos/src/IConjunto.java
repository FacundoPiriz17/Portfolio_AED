package tda.Conjuntos;


public interface IConjunto<T> extends ILista<T> {
    public void insertar(Nodo<T> unNodo);
    public IConjunto<T> union(IConjunto<T> otroConjunto);
    public IConjunto<T> interseccion(IConjunto<T> otroConjunto);
    public IConjunto<T> diferenciaSimetrica(IConjunto otroConjunto);
    public IConjunto<T> complemento(IConjunto<T> conjuntoUniversal);
}
