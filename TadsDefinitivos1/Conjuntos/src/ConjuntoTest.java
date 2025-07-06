package tda.Conjuntos;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ConjuntoTest {
    private IConjunto<Alumno> conjunto1;
    private IConjunto<Alumno> conjunto2;
    private IConjunto<Alumno> conjuntoVacío;

    @Before
    public void setUp() {
        conjunto1 = new Conjunto<>();
        conjunto2 = new Conjunto<>();
        conjuntoVacío = new Conjunto<>();

        Alumno alumno1 = new Alumno("Juan", 1234, "Pérez");
        Alumno alumno2 = new Alumno("Ana", 5678, "Gómez");
        Alumno alumno3 = new Alumno("Luis", 9101, "Ramírez");
        Alumno alumno4 = new Alumno("Sofia", 1213, "Rodriguez");
        Alumno alumno5 = new Alumno("Mateo", 1415, "Correa");

        Nodo<Alumno> nodo1 = new Nodo<>();
        nodo1.setDato(alumno1);
        nodo1.setEtiqueta(alumno1.getCedula());
        Nodo<Alumno> nodo2 = new Nodo<>();
        nodo2.setDato(alumno2);
        nodo2.setEtiqueta(alumno2.getCedula());
        Nodo<Alumno> nodo3 = new Nodo<>();
        nodo3.setDato(alumno3);
        nodo3.setEtiqueta(alumno3.getCedula());
        Nodo<Alumno> nodo4 = new Nodo<>();
        nodo4.setDato(alumno4);
        nodo4.setEtiqueta(alumno4.getCedula());
        Nodo<Alumno> nodo5 = new Nodo<>();
        nodo5.setDato(alumno5);
        nodo5.setEtiqueta(alumno5.getCedula());

        conjunto1.insertar(nodo1);
        conjunto1.insertar(nodo2);
        conjunto1.insertar(nodo3);

        conjunto2.insertar(nodo3);
        conjunto2.insertar(nodo4);
        conjunto2.insertar(nodo5);
    }

    // Prueba de unión
    @Test
    public void testUnion() {
        IConjunto<Alumno> union = conjunto1.union(conjunto2);

        // Verificar que la unión contenga a todos los alumnos sin repeticiones
        assertNotNull(union);
        assertNotNull(union.buscar(1234));
        assertNotNull(union.buscar(5678));
        assertNotNull(union.buscar(9101));
        assertNotNull(union.buscar(1213));
        assertNotNull(union.buscar(1415));
    }

    // Prueba de intersección
    @Test
    public void testInterseccion() {
        IConjunto<Alumno> interseccion = conjunto1.interseccion(conjunto2);

        // Verificar que la intersección solo contenga a los alumnos en comun
        assertNotNull(interseccion);
        assertNotNull(interseccion.buscar(9101)); // Luis está en ambos
        assertNull(interseccion.buscar(1234)); // Juan no está en conjunto2
        assertNull(interseccion.buscar(5678)); // Ana no está en conjunto2
        assertNull(interseccion.buscar(1213)); // Sofia no está en conjunto1
        assertNull(interseccion.buscar(1415)); // Mateo no está en conjunto1
    }

    // Prueba de unión con un conjunto vacío
    @Test
    public void testUnionConVacio() {
        IConjunto<Alumno> unionConVacio = conjuntoVacío.union(conjunto1);

        // Verificar que la unión con el conjunto vacío sea igual al conjunto original
        assertNotNull(unionConVacio);
        assertNotNull(unionConVacio.buscar(1234));
        assertNotNull(unionConVacio.buscar(5678));
        assertNotNull(unionConVacio.buscar(9101));
    }

    // Prueba de intersección con un conjunto vacío
    @Test
    public void testInterseccionConVacio() {
        IConjunto<Alumno> interseccionConVacio = conjuntoVacío.interseccion(conjunto1);

        // Verificar que la intersección con el conjunto vacío sea un conjunto vacío
        assertNotNull(interseccionConVacio);
        assertNull(interseccionConVacio.buscar(1234)); // Juan no está en conjunto vacío
        assertNull(interseccionConVacio.buscar(5678)); // Ana no está en conjunto vacío
        assertNull(interseccionConVacio.buscar(9101)); // Luis no está en conjunto vacío
    }

    // Prueba de unión de conjuntos sin elementos comunes
    @Test
    public void testUnionSinElementosComunes() {
        IConjunto<Alumno> conjunto3 = new Conjunto<>();
        IConjunto<Alumno> conjunto4 = new Conjunto<>();

        // Insertamos alumnos sin elementos comunes
        Alumno alumno1 = new Alumno("Juan", 1234, "Pérez");
        Alumno alumno2 = new Alumno("Ana", 5678, "Gómez");
        Alumno alumno3 = new Alumno("Luis", 9101, "Ramírez");
        Alumno alumno4 = new Alumno("Sofia", 1213, "Rodriguez");

        Nodo<Alumno> nodo1 = new Nodo<>();
        nodo1.setDato(alumno1);
        nodo1.setEtiqueta(alumno1.getCedula());
        Nodo<Alumno> nodo2 = new Nodo<>();
        nodo2.setDato(alumno2);
        nodo2.setEtiqueta(alumno2.getCedula());
        Nodo<Alumno> nodo3 = new Nodo<>();
        nodo3.setDato(alumno3);
        nodo3.setEtiqueta(alumno3.getCedula());
        Nodo<Alumno> nodo4 = new Nodo<>();
        nodo4.setDato(alumno4);
        nodo4.setEtiqueta(alumno4.getCedula());

        conjunto3.insertar(nodo1);
        conjunto3.insertar(nodo2);

        conjunto4.insertar(nodo3);
        conjunto4.insertar(nodo4);

        IConjunto<Alumno> union = conjunto3.union(conjunto4);

        assertNotNull(union);
        assertNotNull(union.buscar(1234));
        assertNotNull(union.buscar(5678));
        assertNotNull(union.buscar(9101));
        assertNotNull(union.buscar(1213));
    }
}
