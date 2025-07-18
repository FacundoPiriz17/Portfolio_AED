package uy.edu.ucu.aed.parcial2;

import java.util.Collection;

import uy.edu.ucu.aed.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class TGrafoDeLaRed extends TGrafoNoDirigido<TNodoDeLaRed> implements IGrafoDeLaRed {

    public TGrafoDeLaRed(Collection<TVerticeDeLaRed> vertices, Collection<IArista> aristas) {
        super((Collection<IVertice<TNodoDeLaRed>>) (Collection<?>) vertices, aristas);
    }

    @Override
    public TCaminos<TNodoDeLaRed> caminosDesdeHasta(Comparable nodoOrigen, Comparable nodoDestino) {
            TVerticeDeLaRed v = (TVerticeDeLaRed) buscarVertice(nodoOrigen);
            TVerticeDeLaRed u = (TVerticeDeLaRed) buscarVertice(nodoDestino);
            if ((v != null)&&(u != null)) {
                TCaminos todosLosCaminos = new TCaminos();
                TCamino caminoPrevio = new TCamino(v);
                v.caminosDesdeHasta(nodoDestino, caminoPrevio, todosLosCaminos);
                return todosLosCaminos;
            }
            return null;
        }
    }
