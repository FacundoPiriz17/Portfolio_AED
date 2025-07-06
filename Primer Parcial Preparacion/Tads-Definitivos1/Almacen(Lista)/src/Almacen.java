import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Almacen extends Lista<Producto> implements IAlmacen {
    private String direccion;
    private String telefono;
    private String nombre;
    private Lista<Producto> listaProductos;


    public Almacen(String direccion, String telefono, String nombre) {
        this.direccion = direccion;
        this.telefono = telefono;
        this.nombre = nombre;
        this.listaProductos = new Lista<Producto>();
    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public Lista<Producto> getListaProductos() {
        return listaProductos;
    }
    public void insertarProducto(Producto unProducto) {
        listaProductos.insertar(unProducto, unProducto.getEtiqueta());
    }
    public boolean eliminar(Comparable clave) {
        return listaProductos.eliminar(clave);
    }

    public String imprimirProductos() {
        return listaProductos.imprimir();
    }

    public String imprimirSeparador(String separador) {
        return listaProductos.imprimir(separador);
    }

    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        Producto unProducto = (Producto) listaProductos.buscar(clave);
        if (unProducto != null) {
            unProducto.setStock(unProducto.getStock() + cantidad);
            return true;
        }
        return false;
    }

    public Integer restarStock(Comparable clave, Integer cantidad){
        Producto unProducto = (Producto) listaProductos.buscar(clave);
        if (unProducto != null) {
            if (unProducto.getStock() >= cantidad) {
                unProducto.setStock(unProducto.getStock() - cantidad);
                subEquipoB(unProducto, cantidad);
                return unProducto.getStock();
            } else{
                unProducto.setStock(unProducto.getStock() - unProducto.getStock());
                subEquipoB(unProducto, cantidad);
                return unProducto.getStock();
            }
        }
        return null;
    }

    public Producto buscarPorCodigo(Comparable clave) {
        return (Producto) listaProductos.buscar(clave);
    }

    public void listarOrdenadoPorNombre() {
        Producto unProducto = null;
        List<Producto> listaOrdenada = new ArrayList<>(); 
        Nodo<Producto> actual = listaProductos.getPrimero();
        for (int i = 0; i < listaProductos.cantElementos(); i++) {
            unProducto = actual.getDato();
            listaOrdenada.add(unProducto);
        }
        listaOrdenada.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
        System.out.println("Lista de productos ordenada por nombre:");
        for (Producto producto : listaOrdenada) {
            System.out.println(producto.getNombre() + " - " + producto.getPrecio() + " - " + producto.getStock());
        }
    }

    public Producto buscarPorDescripcion(String descripcion){
        Producto unProducto = null;
        int i = 0;
        Nodo<Producto> actual = listaProductos.getPrimero();
        while(actual != null && i < listaProductos.cantElementos()){
            actual = actual.getSiguiente();
            unProducto = actual.getDato();
            if (unProducto.descripcion.equalsIgnoreCase(descripcion)){
                return unProducto;
            }
        }
        return null;
    }

    public int cantidadProductos() {
        return listaProductos.cantElementos();
    }

    public void subEquipoB(Producto producto, int cantidad){
        FileWriter fw;
        String nombreCompletoArchivo = "src\\main\\java\\uy\\edu\\ucu\\aed\\tdas\\ventas.txt";
        try {
            fw = new FileWriter(nombreCompletoArchivo, true);

            BufferedWriter bw = new BufferedWriter(fw);

            String lineaActual = producto.getEtiqueta().toString() + ", " + cantidad;
            bw.write(lineaActual);
            bw.newLine();

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "+nombreCompletoArchivo);
            e.printStackTrace();
        }
    }
    public String altas(String archivo){
        ManejadorArchivosGenerico manejador = new ManejadorArchivosGenerico();
        String[] listaLineasArchivo = manejador.leerArchivo(archivo);
        String resultado = "";
        int monto = 0;

        for (int i = 0; i < listaLineasArchivo.length-1; i++) {
            String linea = listaLineasArchivo[i];
            String[] lineaSeparada = linea.split(",");
            Producto producto = new Producto(lineaSeparada[0], lineaSeparada[1], lineaSeparada[1], Integer.parseInt(lineaSeparada[2]), Integer.parseInt(lineaSeparada[3]));

            if (listaProductos.esVacia()) {
                insertarProducto(producto);
                resultado += producto.getNombre() + " - " + producto.getStock() + "/" +"\n";
                monto += producto.getPrecio() * producto.getStock();
            }

            if (listaProductos.buscar(producto.getEtiqueta()) != null) {
                agregarStock(lineaSeparada[0], Integer.parseInt(lineaSeparada[3]));
                resultado += producto.getNombre() + " - " + producto.getStock() + "/" + "\n";
                monto += producto.getPrecio() * producto.getStock();
            }
            else {
                insertarProducto(producto);
                resultado += producto.getNombre() + " - " + producto.getStock() + "/" +"\n";
                monto += producto.getPrecio() * producto.getStock();
            }
        }
        resultado += "El monto total es " + monto;
        String[] a = resultado.split("/");
        manejador.escribirArchivo("AltasHechas", a);
        return resultado;
    }

    public static void main(String[] args) {
        Almacen almacen = new Almacen("direccion", "telefono", "nombre");

        Producto producto = new Producto("1", "descripcion", "nombre", 100, 10);
        Producto producto2 = new Producto("2", "descripcion2", "nombre2", 200, 20);
        Producto producto3 = new Producto("3", "descripcion3", "nombre3", 300, 30);
        Producto producto4 = new Producto("4", "descripcion4", "nombre4", 400, 40);

        almacen.insertarProducto(producto);
        almacen.insertarProducto(producto2);
        almacen.insertarProducto(producto3);
        almacen.insertarProducto(producto4);
        almacen.restarStock("2", 2);
        System.out.println();

        String archivo = "src\\main\\java\\uy\\edu\\ucu\\aed\\tdas\\altas.txt";
        String almacenAltas = almacen.altas(archivo);
        System.out.println(almacenAltas);
    }
}
