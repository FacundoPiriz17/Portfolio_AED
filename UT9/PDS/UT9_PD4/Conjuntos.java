import java.util.Set;

public class Conjuntos {
    //*** Caso promedio O(n) ***//
    //*** Caso promedio O(m * n) ***//
    public static boolean sonDisjuntos(Set<Integer> conjunto1, Set<Integer> conjunto2) {
        for (Integer elemento:conjunto1) {
            if (conjunto2.contains(elemento)) {
                return false;
            }
        }
        return true;
    }
}