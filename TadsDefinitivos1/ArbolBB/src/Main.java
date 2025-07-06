
public class Main {
    public static void main(String[] args) {
        ArbolBB<Integer> arbol = new ArbolBB<>();

        arbol.insertar(50, 50);
        arbol.insertar(30, 30);
        arbol.insertar(70, 70);
        arbol.insertar(20, 20);
        arbol.insertar(40, 40);
        arbol.insertar(60, 60);
        arbol.insertar(80, 80);
        System.out.println("Menor clave: " + arbol.obtenerMenorClave());
        System.out.println("Mayor clave: " + arbol.obtenerMayorClave());
        System.out.println("Clave anterior a 60: " + arbol.obtenerClaveAnterior(60));
        System.out.println("Clave anterior a 30: " + arbol.obtenerClaveAnterior(30));
        System.out.println("Clave anterior a 20: " + arbol.obtenerClaveAnterior(20));
        System.out.println("Cantidad de nodos en nivel 0 (raíz): " + arbol.cantidadNodosPorNivel(3));
        System.out.println("Cantidad de nodos en nivel 1: " + arbol.cantidadNodosPorNivel(1));
        System.out.println("Cantidad de nodos en nivel 2: " + arbol.cantidadNodosPorNivel(2));
        System.out.println("Hojas con nivel:");
        for (String hoja : arbol.listarHojasConNivel()) {
            System.out.println(hoja);
        }
        System.out.println("¿Es árbol de búsqueda?: " + arbol.esDeBusqueda());
    }
}
