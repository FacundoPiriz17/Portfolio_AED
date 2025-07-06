public class Almacen implements IAlmacen {

    private String nombre;
    private String direccion;
    private String telefono;

    private TArbolBB<Producto> productos;

    public Almacen(String nombre) {
        this.nombre = nombre;
        this.productos = new TArbolBB<Producto>();
    }

    @Override
    public void insertarProducto(Producto unProducto) {
        TElementoAB<Producto> nuevoElemento = new TElementoAB<>(unProducto.getEtiqueta(), unProducto);
        productos.insertar(nuevoElemento);
    }

    @Override
    public String imprimirProductos() {
        return productos.inOrden();
    }

    @Override
    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> producto = productos.buscar(clave);
        if (producto != null) {
            producto.getDatos().agergarStock(cantidad);
            return true;
        }
        return false;
    }

    @Override
    public Integer restarStock(Comparable clave, Integer cantidad) {
        TElementoAB<Producto> producto = productos.buscar(clave);
        if (producto != null) {
            Producto p = producto.getDatos();
            if (p.getStock() >= cantidad) {
                p.restarStock(cantidad);
                return cantidad;
            }
        }
        return 0;
    }

    @Override
    public Producto buscarPorCodigo(Comparable clave) {
        TElementoAB<Producto> elemento = productos.buscar(clave);
        return (elemento != null) ? elemento.getDatos() : null;
    }

    @Override
    public boolean eliminarProducto(Comparable clave) {
        TElementoAB<Producto> prod = productos.buscar(clave);
        if (prod != null) {
            productos.eliminar(clave);
            return true;
        }
        return false;
    }
}
