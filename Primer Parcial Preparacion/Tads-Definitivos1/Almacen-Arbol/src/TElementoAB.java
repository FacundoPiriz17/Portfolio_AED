public class TElementoAB<T> implements IElementoAB<T> {

    private Comparable etiqueta;
    private TElementoAB<T> hijoIzq;
    private TElementoAB<T> hijoDer;
    private T datos;

    /**
     * @param unaEtiqueta
     * @param unosDatos 
     */
    @SuppressWarnings("unchecked")
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
    }

    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }

    /**
     * @param unElemento
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (unElemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().insertar(unElemento);
            } else {
                hijoIzq = unElemento;
                return true;
            }
        } else if (unElemento.getEtiqueta().compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                hijoDer = unElemento;
                return true;
            }
        } else {
            // ya existe un elemento con la misma etiqueta.-
            return false;
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {

        if (unaEtiqueta.equals(etiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDer != null) {
            return getHijoDer().buscar(unaEtiqueta);
        } else {
            return null;
        }
    }

    /**
     * @return recorrida en inorden del subArbol que cuelga del elemento actual
     */
    @Override
    public String inOrden() {
        String res = "";
        if (hijoIzq != null) {
           res += hijoIzq.inOrden();
        }
        res += datos.toString();
        if (hijoDer != null) {
            res += hijoDer.inOrden();
        }
        return res;
    }

   @Override
    public void inOrden(Lista<T> unaLista) {
        if (hijoIzq != null) {
           hijoIzq.inOrden(unaLista);
        }
       unaLista.insertar(new Nodo<>(etiqueta,datos));
       if (hijoDer != null) {
           hijoDer.inOrden(unaLista);
       }
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    /**
     * @return
     */
    public String imprimir() {
        return (etiqueta.toString());
    }

    @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {
        this.hijoIzq = elemento;

    }

    @Override
    public void setHijoDer(TElementoAB<T> elemento) {
        this.hijoDer = elemento;
    }

    

    @Override
    public int obtenerAltura() {
        int a = -1;
        int b = -1;
        if (hijoIzq != null) {
            a = hijoIzq.obtenerAltura();
        }
        if (hijoDer != null) {
            b = hijoDer.obtenerAltura();
        }
        return (Math.max(a, b) + 1);
    }

    @Override
    public int obtenerTamanio() {
        int tamano = 1;
        if (hijoIzq != null) {
            tamano += hijoIzq.obtenerTamanio();
        }
        if (hijoDer != null) {
            tamano += hijoDer.obtenerTamanio();
        }
        return tamano;    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(this.etiqueta) == 0) {
            return 0;
        }
        int nivel = -1;
        if (hijoIzq != null) {
            nivel = hijoIzq.obtenerNivel(unaEtiqueta);
            if (nivel != -1) {
                return nivel +1;
            }
        }
        if (hijoDer != null) {
            nivel = hijoDer.obtenerNivel(unaEtiqueta);
            if (nivel != -1) {
                return nivel +1;
            }
        }

        return nivel;    }

    @Override
    public int obtenerCantidadHojas() {
        if (hijoIzq == null && hijoDer == null) {
            return 1;
        }
        int hojas = 0;
        if (hijoIzq != null) {
            hojas = hijoIzq.obtenerCantidadHojas();
        }
        if (hijoDer != null) {
            hojas = hijoDer.obtenerCantidadHojas();
        }
        return hojas;
    }

    @Override
    public TElementoAB<T> eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(this.etiqueta) < 0) {
            if (hijoIzq != null) {
                hijoIzq = hijoIzq.eliminar(unaEtiqueta);
            }
        } else if (unaEtiqueta.compareTo(this.etiqueta) > 0) {
            if (hijoDer != null) {
                hijoDer = hijoDer.eliminar(unaEtiqueta);
            }
        } else {
            if (hijoIzq == null) {
                return hijoDer;
            }
            if (hijoDer == null) {
                return hijoIzq;
            }
            TElementoAB<T> sucesor = hijoDer.quitaElNodo();
            this.etiqueta = sucesor.getEtiqueta();
            this.datos = sucesor.getDatos();
            hijoDer = hijoDer.eliminar(sucesor.getEtiqueta());
        }
        return this;
    }

    private TElementoAB quitaElNodo() {
        if (hijoIzq == null) {
            return this;
        } else {
            return hijoIzq.quitaElNodo();
        }
    }
   	
}
