package uy.edu.ucu.aed.tda;

@SuppressWarnings("rawtypes")
public class Ruta implements IArista, Comparable<Ruta> {

    protected Comparable etiquetaOrigen;
    protected Comparable etiquetaDestino;
    protected double costo;
    protected boolean esPrioritaria;

    public Ruta(Comparable etiquetaOrigen, Comparable etiquetaDestino, double costo, boolean  esPrioritaria) {
        this.etiquetaOrigen = etiquetaOrigen;
        this.etiquetaDestino = etiquetaDestino;
        this.costo = costo;
        this.esPrioritaria = esPrioritaria;
    }

    public boolean getEsPrioritaria() {
        return esPrioritaria;
    }

    @Override
    public Comparable getEtiquetaOrigen() {
        return etiquetaOrigen;
    }

    @Override
    public void setEtiquetaOrigen(Comparable etiquetaOrigen) {
        this.etiquetaOrigen = etiquetaOrigen;
    }

    @Override
    public Comparable getEtiquetaDestino() {
        return etiquetaDestino;
    }

    @Override
    public void setEtiquetaDestino(Comparable etiquetaDestino) {
        this.etiquetaDestino = etiquetaDestino;
    }

    @Override
    public double getCosto() {
        return costo;
    }

    @Override
    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Ruta aristaInversa() {
        return new Ruta(this.getEtiquetaDestino(), this.getEtiquetaOrigen(), this.getCosto(), this.esPrioritaria);
    }

    @Override
    public int compareTo(Ruta o) {
        return Double.compare(this.costo, o.costo);
    }

}
