public class ElementoAVL<T> implements IElementoAVL<T> {
    private Comparable etiqueta;
    private T dato;
    private IElementoAVL<T> hijoIzq, hijoDer;
    private int alturaNodo;

    public ElementoAVL(Comparable unaEtiqueta, T unDato) {
        this.etiqueta = unaEtiqueta;
        this.dato = unDato;
        this.hijoIzq = null;
        this.hijoDer = null;
        this.alturaNodo = 0;
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public IElementoAVL<T> getHijoIzq() {
        return hijoIzq;
    }

    @Override
    public IElementoAVL<T> getHijoDer() {
        return hijoDer;
    }

    @Override
    public void setHijoIzq(IElementoAVL<T> elemento) {
        this.hijoIzq = elemento;
    }

    @Override
    public void setHijoDer(IElementoAVL<T> elemento) {
        this.hijoDer = elemento;
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public int getAltura() {
        return alturaNodo;
    }

    @Override
    public int getBalance() {
        int altIzq = (hijoIzq != null) ? hijoIzq.getAltura() : -1;
        int altDer = (hijoDer != null) ? hijoDer.getAltura() : -1;
        return altIzq - altDer;
    }

    @Override
    public void actualizarAltura() {
        int altIzq = (hijoIzq != null) ? hijoIzq.getAltura() : -1;
        int altDer = (hijoDer != null) ? hijoDer.getAltura() : -1;
        alturaNodo = 1 + Math.max(altIzq, altDer);
    }


    @Override
    public IElementoAVL<T> insertar(IElementoAVL<T> elemento) {
        if (elemento.getEtiqueta().compareTo(this.etiqueta) < 0) {
            if (hijoIzq == null) {
                hijoIzq = elemento;
            } else {
                hijoIzq = hijoIzq.insertar(elemento);
            }
        } else if (elemento.getEtiqueta().compareTo(this.etiqueta) > 0) {
            if (hijoDer == null) {
                hijoDer = elemento;
            } else {
                hijoDer = hijoDer.insertar(elemento);
            }
        } else {
            return this;
        }


        actualizarAltura();
        return balancear();
    }

    @Override
    public IElementoAVL<T> eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) hijoIzq = hijoIzq.eliminar(unaEtiqueta);
        } else if (unaEtiqueta.compareTo(etiqueta) > 0) {
            if (hijoDer != null) hijoDer = hijoDer.eliminar(unaEtiqueta);
        } else {
            if (hijoIzq == null) return hijoDer;
            if (hijoDer == null) return hijoIzq;

            IElementoAVL<T> sucesor = hijoIzq;
            while (sucesor.getHijoDer() != null) sucesor = sucesor.getHijoDer();
            this.etiqueta = sucesor.getEtiqueta();
            this.dato = sucesor.getDato();
            hijoIzq = hijoIzq.eliminar(sucesor.getEtiqueta());
        }

        actualizarAltura();
        return balancear();
    }

    private IElementoAVL<T> balancear() {
        int balance = getBalance();
        if (balance > 1) {
            if (hijoIzq.getBalance() >= 0) {
                return rotacionLL(this);
            } else {
                return rotacionLR(this);
            }
        }
        if (balance < -1) {
            if (hijoDer.getBalance() <= 0) {
                return rotacionRR(this);
            } else {
                return rotacionRL(this);
            }
        }
        return this;
    }

    private IElementoAVL<T> rotacionLL(IElementoAVL<T> k2) {
        IElementoAVL<T> k1 = k2.getHijoIzq();
        k2.setHijoIzq(k1.getHijoDer());
        k1.setHijoDer(k2);
        k2.actualizarAltura();
        k1.actualizarAltura();
        return k1;
    }

    private IElementoAVL<T> rotacionRR(IElementoAVL<T> k1) {
        IElementoAVL<T> k2 = k1.getHijoDer();
        k1.setHijoDer(k2.getHijoIzq());
        k2.setHijoIzq(k1);
        k1.actualizarAltura();
        k2.actualizarAltura();
        return k2;
    }

    private IElementoAVL<T> rotacionLR(IElementoAVL<T> k3) {
        k3.setHijoIzq(rotacionRR(k3.getHijoIzq()));
        return rotacionLL(k3);
    }

    private IElementoAVL<T> rotacionRL(IElementoAVL<T> k1) {
        k1.setHijoDer(rotacionLL(k1.getHijoDer()));
        return rotacionRR(k1);
    }

    @Override
    public IElementoAVL<T> buscar(Comparable unaEtiqueta) {
        if (this.etiqueta.compareTo(unaEtiqueta) == 0) return this;
        if (this.etiqueta.compareTo(unaEtiqueta) > 0 && hijoIzq != null) return hijoIzq.buscar(unaEtiqueta);
        if (this.etiqueta.compareTo(unaEtiqueta) < 0 && hijoDer != null) return hijoDer.buscar(unaEtiqueta);
        return null;
    }

    @Override
    public String preOrden() {
        String res = this.getDato() + "-";
        if (hijoIzq != null) {
            res += hijoIzq.preOrden();
        }
        if (hijoDer != null) {
            res += hijoDer.preOrden();
        }
        return res;
    }

    @Override
    public String inOrden() {
        String res = "";
        if (hijoIzq != null) {
            res += hijoIzq.inOrden();
        }
        res += this.getDato() + "-";
        if (hijoDer != null) {
            res += hijoDer.inOrden();
        }
        return res;
    }

    @Override
    public String postOrden() {
        String res = "";
        if (hijoIzq != null) {
            res += hijoIzq.postOrden();
        }
        if (hijoDer != null) {
            res += hijoDer.postOrden();
        }
        res += this.getDato() + "-";
        return res;
    }

    @Override
    public int obtenerTamano() {
        int tam = 1;
        if (hijoIzq != null) tam += hijoIzq.obtenerTamano();
        if (hijoDer != null) tam += hijoDer.obtenerTamano();
        return tam;
    }

    @Override
    public int getHojas() {
        if (hijoIzq == null && hijoDer == null) return 1;
        int hojas = 0;
        if (hijoIzq != null) hojas += hijoIzq.getHojas();
        if (hijoDer != null) hojas += hijoDer.getHojas();
        return hojas;
    }

    @Override
    public int getNivel(Comparable unaEtiqueta) {
        if (this.etiqueta.compareTo(unaEtiqueta) == 0) return 0;
        int nivel;
        if (hijoIzq != null) {
            nivel = hijoIzq.getNivel(unaEtiqueta);
            if (nivel != -1) return nivel + 1;
        }
        if (hijoDer != null) {
            nivel = hijoDer.getNivel(unaEtiqueta);
            if (nivel != -1) return nivel + 1;
        }
        return -1;
    }
}
