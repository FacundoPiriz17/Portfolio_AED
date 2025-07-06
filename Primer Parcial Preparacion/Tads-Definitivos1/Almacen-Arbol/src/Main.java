
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Almacen almacen = new Almacen("Prueba");
        // cargar los productos desde el archivo "altasprueba.txt"
        String[] lineasAltas = ManejadorArchivosGenerico.leerArchivo("altasPrueba.txt");
        for (String linea : lineasAltas) {
            String[] partes = linea.split(",");
            String codigo = partes[0];
            String nombre = partes[1];
            int precio = Integer.parseInt(partes[2]);
            int stock = Integer.parseInt(partes[3]);

            Producto existente = almacen.buscarPorCodigo(codigo);
            if (existente != null) {
                almacen.agregarStock(codigo, stock);
            } else {
                Producto nuevo = new Producto(codigo, nombre);
                nuevo.agergarStock(stock);
                nuevo.setPrecio(precio);
                almacen.insertarProducto(nuevo);
            }
        }

            // listar los productos ordenados por codigo, junto con su cantidad existente
        System.out.println("Productos actuales:");
        System.out.println(almacen.imprimirProductos());

        // Emitir el valor del stock
        int totalStock = 0;
        for (String linea1 : lineasAltas) {
            String[] partes1 = linea1.split(",");
            String codigo2 = partes1[0];
            Producto p = almacen.buscarPorCodigo(codigo2);
            if (p != null) {
                totalStock += p.getPrecio() * p.getStock();
            }
        }
        System.out.println("Valor total del stock: $" + totalStock);

            // simular las ventas a partir del archivo "ventaspruebas.txt"
        String[] ventas = ManejadorArchivosGenerico.leerArchivo("ventasPrueba.txt");
        for (String venta : ventas) {
            String[] partes2 = venta.split(",");
            String codigo2 = partes2[0];
            int cantidad = Integer.parseInt(partes2[1]);
            almacen.restarStock(codigo2, cantidad);
        }
            // simular la eliminación de productos a partir del archivo "elimPrueba.txt"
        String[] eliminaciones = ManejadorArchivosGenerico.leerArchivo("elimPrueba.txt");
        for (String codigo4 : eliminaciones) {
            almacen.eliminarProducto(codigo4);
        }
            // listar los productos ordenados por codigo, junto con su cantidad existente
        System.out.println("Listado final de productos ordenados por código:");
        System.out.println(almacen.imprimirProductos());
        }
    }