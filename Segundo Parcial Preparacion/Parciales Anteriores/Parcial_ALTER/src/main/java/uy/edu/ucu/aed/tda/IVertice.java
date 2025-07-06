package uy.edu.ucu.aed.tda;

import java.util.Collection;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ernesto
 */
public interface IVertice {

    TAdyacencia buscarAdyacencia(Ciudad verticeDestino);

    TAdyacencia buscarAdyacencia(Comparable etiquetaDestino);

    boolean eliminarAdyacencia(Comparable nomVerticeDestino);

    boolean insertarAdyacencia(Double costo, Ciudad verticeDestino);

    Double obtenerCostoAdyacencia(Ciudad verticeDestino);

    Ciudad primerAdyacente();

    Ciudad siguienteAdyacente(Ciudad w);

    public void bpf(Collection<Ciudad> visitados);

    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos);

    public boolean tieneCiclo(LinkedList<Comparable> camino);

    public void bea(Collection<Ciudad> visitados);

    public boolean conectadoCon(Ciudad destino);
}
