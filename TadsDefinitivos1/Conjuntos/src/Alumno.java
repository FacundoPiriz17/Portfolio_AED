package tda.Conjuntos;

public class Alumno implements Comparable<Alumno> {
    private String nombre;
    private Comparable cedula;
    private String apellido;

    public Alumno(String nombre, Comparable cedula, String apellido) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public Comparable getCedula() {
        return cedula;
    }

    public String getApellido() {
        return apellido;
    }

    @Override
    public int compareTo(Alumno other) {
        return this.cedula.compareTo(other.cedula);
    }
}
