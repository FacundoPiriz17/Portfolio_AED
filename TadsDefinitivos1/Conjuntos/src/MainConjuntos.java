package tda.Conjuntos;


public class MainConjuntos {
    public static void main(String[] args) {
        // Caso 1: Union de dos conjuntos
        IConjunto conjunto1 = new Conjunto();
        for (int i = 0; i < 5; i++) {
            conjunto1.insertar(i, i);
        }
        IConjunto conjunto2 = new Conjunto();
        for (int i = 0; i < 11; i++) {
            conjunto2.insertar(i, i);
        }

        // Imprimir unión de conjunto1 y conjunto2
        System.out.println("Union de conjunto1 y conjunto2: ");
        System.out.println(conjunto1.union(conjunto2).imprimir());

        // Caso 2: Intersección de dos conjuntos
        IConjunto interseccionConjuntos = conjunto1.interseccion(conjunto2);
        System.out.println("Intersección de conjunto1 y conjunto2: ");
        System.out.println(interseccionConjuntos.imprimir());

        // Caso 3: Union de un conjunto vacío con otro conjunto
        IConjunto conjuntoVacio = new Conjunto();
        IConjunto conjuntoConElementos = new Conjunto();
        for (int i = 1; i <= 5; i++) {
            conjuntoConElementos.insertar(i, i);
        }

        // Imprimir unión con conjunto vacío
        System.out.println("Unión de conjunto vacío con conjuntoConElementos: ");
        System.out.println(conjuntoVacio.union(conjuntoConElementos).imprimir());

        // Caso 4: Intersección de un conjunto vacío con otro conjunto
        IConjunto interseccionVacio = conjuntoVacio.interseccion(conjuntoConElementos);
        System.out.println("Intersección de conjunto vacío con conjuntoConElementos: ");
        System.out.println(interseccionVacio.imprimir());

        // Caso 5: Union de conjuntos sin elementos comunes
        IConjunto conjunto3 = new Conjunto();
        for (int i = 1; i <= 3; i++) {
            conjunto3.insertar(i, i);
        }
        IConjunto conjunto4 = new Conjunto();
        for (int i = 4; i <= 6; i++) {
            conjunto4.insertar(i, i);
        }

        // Imprimir unión sin elementos comunes
        System.out.println("Unión de conjunto3 y conjunto4 (sin elementos comunes): ");
        System.out.println(conjunto3.union(conjunto4).imprimir());

        // Caso 6: Intersección de conjuntos sin elementos comunes
        IConjunto interseccionSinComunes = conjunto3.interseccion(conjunto4);
        System.out.println("Intersección de conjunto3 y conjunto4 (sin elementos comunes): ");
        System.out.println(interseccionSinComunes.imprimir());

        Alumno alumno1 = new Alumno("Juan", Comparable.class.cast(1234), "Pérez");
        Alumno alumno2 = new Alumno("Ana", Comparable.class.cast(5678), "Gómez");
        Alumno alumno3 = new Alumno("Luis", Comparable.class.cast(9101), "Ramírez");
        Alumno alumno4 = new Alumno("Sofia", Comparable.class.cast(1213), "Rodriguez");
        Alumno alumno5 = new Alumno("Mateo", Comparable.class.cast(1415), "Correa");

        // Creamos la lista de alumnos
        Lista<Alumno> listaAlumnos = new Lista<>();

        // Creamos nodos con los alumnos
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

        // Creamos conjuntos
        IConjunto AED1 = new Conjunto<>();
        AED1.insertar(nodo1);
        AED1.insertar(nodo2);
        AED1.insertar(nodo3);

        IConjunto PF = new Conjunto<>();
        PF.insertar(nodo3);
        PF.insertar(nodo4);
        PF.insertar(nodo5);

        // Unión de conjuntos
        IConjunto union = AED1.union(PF);
        if(union.buscar(alumno1.getCedula())!=null && union.buscar(alumno3.getCedula())!=null && union.buscar(alumno5.getCedula())!=null)
        {
            System.out.println("Union Funiona");
        }
        else{
            System.out.println("Union no funciona");
        }
        System.out.println(union.imprimir());

        // Intersección
        IConjunto interseccion = AED1.interseccion(PF);
        if(interseccion.buscar(alumno1.getCedula())==null && interseccion.buscar(alumno3.getCedula())!=null && interseccion.buscar(alumno5.getCedula())==null )
        {
            System.out.println("Interseccion Funiona");
        }
        else{
            System.out.println("Interseccion no funciona");
        }
        System.out.println(AED1.interseccion(PF).imprimir());

        IConjunto difSim = conjunto1.diferenciaSimetrica(conjunto2);
        System.out.println(difSim.imprimir());
        IConjunto complemento = conjunto1.complemento(conjunto2);
        System.out.println(complemento.imprimir());
    }

}
