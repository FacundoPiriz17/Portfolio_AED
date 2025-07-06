package uy.edu.ucu.aed.tdas;

public class Producto implements IProducto {

    private Comparable<String> etiqueta;
    private String nombre;
    private Integer precio;
    private Integer stock;
    public String descripcion;

    public Producto(Comparable<String> etiqueta,String descripcion, String nombre, Integer precio, Integer stock) {
        this.etiqueta = etiqueta;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    @Override
    public Comparable<String> getEtiqueta() {
        return this.etiqueta;
    }

    @Override
    public Integer getPrecio(){
        return this.precio;
    }

    @Override
    public void setPrecio(Integer precio){
        this.precio = precio;
    }

    @Override
    public Integer getStock(){
        return this.stock;
    }

    @Override
    public void setStock(Integer stock){
        this.stock = stock;
    }

    @Override
    public String getNombre(){
        return this.nombre;
    }

    @Override
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
