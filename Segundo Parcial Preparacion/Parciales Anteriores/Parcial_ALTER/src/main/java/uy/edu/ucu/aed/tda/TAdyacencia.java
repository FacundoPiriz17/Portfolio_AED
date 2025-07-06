package uy.edu.ucu.aed.tda;

public class TAdyacencia implements IAdyacencia {

    private Comparable etiqueta;
    private double costo;
    private Ciudad destino;

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public double getCosto() {
        return costo;
    }

    @Override
    public Ciudad getDestino() {
        return destino;
    }

    public TAdyacencia(double costo, Ciudad destino) {
        this.etiqueta = destino.getEtiqueta();
        this.costo = costo;
        this.destino = destino;
    }
}
