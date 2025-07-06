import java.util.LinkedList;

public class Facultad {
    LinkedList<Alumno> listaAlumnos;
    LinkedList<Curso> listaCursos;
    Facultad(LinkedList<Alumno> lista1, LinkedList<Curso> lista2) {
        listaAlumnos = lista1;
        listaCursos = lista2;
    }

    public TArbolBBAlumnos armarIndiceCurso(String cursoNombre) //O(n cuadrado)
    {
        TArbolBBAlumnos alumnosEnCurso = new TArbolBBAlumnos();

        Integer identificador = null;
        for (Curso curso : listaCursos) {
            String nombreCurso = curso.getNombre();
            if (nombreCurso.equals(cursoNombre)) {
                identificador = curso.getIdentificador();
                break;
            }
        }

        if (identificador != null) {
            for (Alumno alumno : listaAlumnos) {
                for (Integer idCurso : alumno.getCursos()) {
                    if (idCurso.equals(identificador)) {
                        String clave = alumno.getApellido().trim() + alumno.getNombre().trim();
                        alumnosEnCurso.insertar(new TElementoAB<>(clave, alumno));
                        break;
                    }
                }
            }
        }
        return alumnosEnCurso;
    }
}
