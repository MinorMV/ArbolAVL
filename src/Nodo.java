public class Nodo {

    int clave;
    int altura;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(int clave) {
        this.clave = clave;
        this.altura = 1; 
    }

    // Métodos 

    private static int altura(Nodo n) {
        return (n == null) ? 0 : n.altura;
    }

    // Recalcula la altura de un nodo 
    private static void actualizarAltura(Nodo n) {
        if (n != null) {
            n.altura = 1 + Math.max(altura(n.izquierdo), altura(n.derecho));
        }
    }

    // Factor de balance de ESTE nodo 
    public int obtenerFactorBalance() {
        return altura(this.izquierdo) - altura(this.derecho);
    }


    // Caso LL
    public static Nodo rotarDerecha(Nodo y) {
        Nodo x = y.izquierdo;
        Nodo subArbolDerechoDeX = x.derecho;

        // Rotación
        x.derecho = y;
        y.izquierdo = subArbolDerechoDeX;

        // Actualizar alturas
        actualizarAltura(y);
        actualizarAltura(x);

        // Nueva raíz del subárbol
        return x;
    }

    // Caso RR

    public static Nodo rotarIzquierda(Nodo x) {
        Nodo y = x.derecho;
        Nodo subArbolIzquierdoDeY = y.izquierdo;

        // Rotación
        y.izquierdo = x;
        x.derecho = subArbolIzquierdoDeY;

        // Actualizar alturas
        actualizarAltura(x);
        actualizarAltura(y);

        // Nueva raíz del subárbol
        return y;
    }

    // Caso LR
    public static Nodo rotarIzquierdaDerecha(Nodo nodo) {
        nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
        return rotarDerecha(nodo);
    }

    // Caso RL
    public static Nodo rotarDerechaIzquierda(Nodo nodo) {
        nodo.derecho = rotarDerecha(nodo.derecho);
        return rotarIzquierda(nodo);
    }
}
