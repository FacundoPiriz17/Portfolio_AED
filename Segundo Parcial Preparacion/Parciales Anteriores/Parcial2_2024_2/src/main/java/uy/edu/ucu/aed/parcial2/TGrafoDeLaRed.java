package uy.edu.ucu.aed.parcial2;

import java.util.Collection;

import uy.edu.ucu.aed.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class TGrafoDeLaRed extends TGrafoNoDirigido<TEstacionDeLaRed> implements IGrafoDeLaRed {

    public TGrafoDeLaRed(Collection<TVerticeDeLaRed> vertices, Collection<IArista> aristas) {
        super((Collection<IVertice<TEstacionDeLaRed>>) (Collection<?>) vertices, aristas);
    }

    @Override
    public TCaminos<TEstacionDeLaRed> caminosDesdeHasta(Comparable nodoOrigen, Comparable nodoDestino) {
        TVerticeDeLaRed origen = (TVerticeDeLaRed) buscarVertice(nodoOrigen);
        TVerticeDeLaRed destino = (TVerticeDeLaRed) buscarVertice(nodoDestino);

        if (origen != null && destino != null
                && origen.getDatos().getTipo() == TipoDeEstacion.AUTOBUSES
                && destino.getDatos().getTipo() == TipoDeEstacion.AUTOBUSES) {

            TCaminos<TEstacionDeLaRed> caminos = new TCaminos<>();
            TCamino caminoInicial = new TCamino(origen);
            origen.caminosDesdeHasta(nodoDestino, caminoInicial, caminos);
            return caminos;
        }
        return null;

    }
}