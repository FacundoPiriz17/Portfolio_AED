import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Parcial 1
 *
 */
public class MainParcial1 
{
    public static void main( String[] args )
    {
        LinkedList<Alumno> listaAlumnos = new LinkedList<>();
        LinkedList<Curso> listaCursos = new LinkedList<>();
        // Cargar alumnos
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/alumnos.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int identificador = Integer.parseInt(fields[0]);
                String nombre = fields[1];
                String apellido = fields[2];
                List<Integer> _cursos = new ArrayList<>();
                for (int i = 3; i < fields.length; i++) {
                    _cursos.add(Integer.parseInt(fields[i]));
                }

                Alumno nuevoAlumno = new Alumno(identificador, nombre, apellido, _cursos);
                listaAlumnos.add(nuevoAlumno);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar alumnos: " + e.getMessage());
        }

        // Cargar cursos
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/cursos.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int identificador = Integer.parseInt(fields[0]);
                String nombre = fields[1];

                Curso nuevoCurso = new Curso(identificador, nombre);
                listaCursos.add(nuevoCurso);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar cursos: " + e.getMessage());
        }

        // Crear Facultad (modificar según cambio de parámetros)
        Facultad facultad = new Facultad(listaAlumnos, listaCursos);

        // Armar alumnos de curso
        String cursoNombre = "Algoritmos";

        // Invocar método desarrollado
        TArbolBBAlumnos alumnosDelCurso = facultad.armarIndiceCurso(cursoNombre);

        LinkedList<Alumno> inorden = alumnosDelCurso.inOrden();
        for (Alumno alumno : inorden) {
            System.out.print(alumno.nombre + " " + alumno.apellido +"-");
        }

        System.out.println();

        // Armar subgrupos
        TArbolBB<Alumno> gruposPares = new TArbolBBAlumnos();
        TArbolBB<Alumno> gruposImpares = new TArbolBBAlumnos();

        alumnosDelCurso.armarSubgrupos(gruposImpares, gruposPares);
        LinkedList<Alumno> lista1 = gruposImpares.inOrden();
        LinkedList<Alumno> lista2 = gruposPares.inOrden();
        System.out.println("Grupos Impares:");
        for (Alumno alumno : lista1) {
            System.out.println(alumno.getNombre());
        }
        System.out.println("");
        System.out.println("Grupos pares:");
        for (Alumno alumno : lista2) {
            System.out.println(alumno.getNombre());
        }
        System.out.println("");

        // Mostrar en consola alumnos del curso (alumnosDelCurso)

        LinkedList<Alumno> lista = alumnosDelCurso.inOrden();
        String[] strings = new String[lista.size()];
        int i = 0;
        for (Alumno alumno : lista) {
            System.out.println(alumno.nombre + " " + alumno.apellido);
            strings[i] =  alumno.getNombre() + "," + alumno.getApellido();
            i++;
        }

        // Guardar alumnos del curso en archivo
        ManejadorArchivosGenerico.escribirArchivo("salida.txt", strings);
    }
}
