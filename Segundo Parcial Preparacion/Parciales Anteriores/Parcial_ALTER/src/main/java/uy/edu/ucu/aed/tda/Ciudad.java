package uy.edu.ucu.aed.tda;

import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Ciudad implements IVertice {

    private final Comparable etiqueta;
    private final LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }


    public Ciudad(Comparable unaEtiqueta) {
        etiqueta = unaEtiqueta;
        adyacentes = new LinkedList<>();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Ciudad verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(Ciudad verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, Ciudad verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public Ciudad primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public void bpf(Collection<Ciudad> visitados) {
        setVisitado(true);
        visitados.add(this);
        for (TAdyacencia adyacente : adyacentes) {
            Ciudad vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.bpf(visitados);
            }
        }
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            Ciudad destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                    caminoPrevio.eliminarAdyacencia(adyacencia);
                }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }

    @Override
    public void bea(Collection<Ciudad> visitados) {
        this.visitado = true;
        LinkedList<Ciudad> lista = new LinkedList();
        lista.add(this);
        visitados.add(this);
        while(!lista.isEmpty()){
            Ciudad primero = lista.remove(0);
            LinkedList<TAdyacencia> ady = primero.getAdyacentes();
            for(TAdyacencia t : ady){
                if(!t.getDestino().getVisitado()){
                    t.getDestino().setVisitado(true);
                    lista.add(t.getDestino());
                    visitados.add(t.getDestino());
                }
            }
        }
    }

    @Override
    public Ciudad siguienteAdyacente(Ciudad w) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

 
    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        setVisitado(true);
        camino.add(this.getEtiqueta());
        boolean ciclo = false;
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            Ciudad w = adyacencia.getDestino();
            if (!w.getVisitado()) {
                ciclo = w.tieneCiclo(camino);
                if (ciclo) {
                    break;
                }
            } else {
                if (camino.contains(w.getEtiqueta())) {
                    ciclo = true;
                    break;
                }

            }

        }
        camino.remove(this.getEtiqueta());
        return ciclo;
    }


    @Override
    public boolean conectadoCon(Ciudad etVertDest) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            Ciudad destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {                    
                    return true;
                } else {                    
                    boolean existe =  destino.conectadoCon(etVertDest);
                    if (existe){
                       return existe;
                    }
                }                
            }
        }
        this.setVisitado(false);
        return false;
    }

}
