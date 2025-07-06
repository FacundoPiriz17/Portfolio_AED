package uy.edu.ucu.aed.parcial2;

import uy.edu.ucu.aed.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class TVerticeDeLaRed extends TVertice<TEstacionDeLaRed> {

    private static TEstacionDeLaRed crearNodo(String unaEtiqueta, String tipoDeVertice) {
        String[] parts = unaEtiqueta.split("_");
        int id = Integer.parseInt(parts[1]);

        TipoDeEstacion tipo = TipoDeEstacion.fromInt(Integer.parseInt(tipoDeVertice));

        return new TEstacionDeLaRed(id, unaEtiqueta, tipo);
    }

    public TVerticeDeLaRed(Object... args) { 
        super((String) args[0]);
    
        this.datos = crearNodo((String) args[0], (String) args[1]);
    }

    public void caminosDesdeHasta(Comparable destino, TCamino caminoActual, TCaminos<TEstacionDeLaRed> todosLosCaminos) {
        this.setVisitado(true);

        for (IAdyacencia adyacencia : this.getAdyacentes()) {
            TVerticeDeLaRed siguiente = (TVerticeDeLaRed) adyacencia.getDestino();

            if (!siguiente.getVisitado() && conexionPermitida(this, siguiente)) {
                caminoActual.agregarAdyacencia(adyacencia);

                if (siguiente.getEtiqueta().equals(destino)) {
                    todosLosCaminos.getCaminos().add(caminoActual.copiar());
                } else {
                    siguiente.caminosDesdeHasta(destino, caminoActual, todosLosCaminos);
                }

                caminoActual.eliminarAdyacencia(adyacencia);
            }
        }

        this.setVisitado(false);
    }

    private boolean conexionPermitida(TVerticeDeLaRed desde, TVerticeDeLaRed hacia) {
        TipoDeEstacion tipoDesde = desde.getDatos().getTipo();
        TipoDeEstacion tipoHacia = hacia.getDatos().getTipo();

        if (tipoDesde == TipoDeEstacion.AUTOBUSES && tipoHacia == TipoDeEstacion.AUTOBUSES) {
            return false;
        }
        return true;
    }

}
