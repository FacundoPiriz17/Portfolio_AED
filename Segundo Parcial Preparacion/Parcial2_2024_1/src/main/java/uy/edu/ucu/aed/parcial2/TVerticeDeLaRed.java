package uy.edu.ucu.aed.parcial2;

import uy.edu.ucu.aed.*;

import static uy.edu.ucu.aed.parcial2.TipoDeNodo.NODO_DE_PROCESAMIENTO;

@SuppressWarnings({"rawtypes", "unchecked"})
public class TVerticeDeLaRed extends TVertice<TNodoDeLaRed> {

    private static TNodoDeLaRed crearNodo(String unaEtiqueta, String tipoDeVertice) {
        String[] parts = unaEtiqueta.split("_");
        int id = Integer.parseInt(parts[1]);

        TipoDeNodo tipo = TipoDeNodo.fromInt(Integer.parseInt(tipoDeVertice));

        return new TNodoDeLaRed(id, unaEtiqueta, tipo);
    }

    public TVerticeDeLaRed(Object... args) { 
        super((String) args[0]);
    
        this.datos = crearNodo((String) args[0], (String) args[1]);
    }

    public TCaminos caminosDesdeHasta(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for (IAdyacencia adyacencia : this.getAdyacentes()) {
            TVerticeDeLaRed destino = (TVerticeDeLaRed) adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (this.datos.getTipo().equals(NODO_DE_PROCESAMIENTO)) {
                    if (destino.datos.getTipo().equals(NODO_DE_PROCESAMIENTO)) continue;
                }
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    if (this.datos.getTipo().equals(NODO_DE_PROCESAMIENTO)) {
                        if (destino.datos.getTipo().equals(NODO_DE_PROCESAMIENTO)) continue;
                    }
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                    caminoPrevio.eliminarAdyacencia(adyacencia);
                }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }
}
