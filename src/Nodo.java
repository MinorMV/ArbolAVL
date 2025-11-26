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

    // Factor de balance de ESTE nodo (opcional, por si se quiere usar)
    public int obtenerFactorBalance() {
        return altura(this.izquierdo) - altura(this.derecho);
    }


    // Rotación simple a la derecha (caso LL)
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

    // Rotación simple a la izquierda (caso RR)
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

    // Rotación doble Izquierda–Derecha (caso LR)
    public static Nodo rotarIzquierdaDerecha(Nodo nodo) {
        // Primero, rotación simple a la izquierda en el hijo izquierdo
        nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
        // Luego, rotación simple a la derecha en el nodo desbalanceado
        return rotarDerecha(nodo);
    }

    // Rotación doble Derecha–Izquierda (caso RL)
    public static Nodo rotarDerechaIzquierda(Nodo nodo) {
        nodo.derecho = rotarDerecha(nodo.derecho);
        return rotarIzquierda(nodo);
    }
}
