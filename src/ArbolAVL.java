public class ArbolAVL {

    private Nodo raiz;

    // Métodos 

    public void insertar(int clave) {
        raiz = insertar(raiz, clave);
    }

    public boolean buscar(int clave) {
        return buscarNodo(raiz, clave) != null;
    }

    public void eliminar(int clave) {
        raiz = eliminar(raiz, clave);
    }

    public void mostrarEnOrden() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
        } else {
            System.out.print("Elementos en orden: ");
            enOrden(raiz);
            System.out.println();
        }
    }

    //  Auxiliares internos

    private int altura(Nodo n) {
        return (n == null) ? 0 : n.altura;
    }

    private int factorBalance(Nodo n) {
        if (n == null) return 0;
        return altura(n.izquierdo) - altura(n.derecho);
    }

    private void actualizarAltura(Nodo n) {
        if (n != null) {
            n.altura = 1 + Math.max(altura(n.izquierdo), altura(n.derecho));
        }
    }

    // Inserción recursiva con rebalance
    private Nodo insertar(Nodo nodo, int clave) {
        if (nodo == null) {
            return new Nodo(clave);
        }

        if (clave < nodo.clave) {
            nodo.izquierdo = insertar(nodo.izquierdo, clave);
        } else if (clave > nodo.clave) {
            nodo.derecho = insertar(nodo.derecho, clave);
        } else {
            // Clave duplicada
            return nodo;
        }

        // Actualizar altura
        actualizarAltura(nodo);

        // Verificar balance y aplicar rotaciones si es necesario
        int fb = factorBalance(nodo);

        // Caso LL
        if (fb > 1 && clave < nodo.izquierdo.clave) {
            return Nodo.rotarDerecha(nodo);
        }

        // Caso RR
        if (fb < -1 && clave > nodo.derecho.clave) {
            return Nodo.rotarIzquierda(nodo);
        }

        // Caso LR
        if (fb > 1 && clave > nodo.izquierdo.clave) {
            return Nodo.rotarIzquierdaDerecha(nodo);
        }

        // Caso RL
        if (fb < -1 && clave < nodo.derecho.clave) {
            return Nodo.rotarDerechaIzquierda(nodo);
        }

        return nodo;
    }

    // Búsqueda recursiva
    private Nodo buscarNodo(Nodo nodo, int clave) {
        if (nodo == null || nodo.clave == clave) {
            return nodo;
        }

        if (clave < nodo.clave) {
            return buscarNodo(nodo.izquierdo, clave);
        } else {
            return buscarNodo(nodo.derecho, clave);
        }
    }

    // Eliminación recursiva con rebalance
    private Nodo eliminar(Nodo nodo, int clave) {
        if (nodo == null) {
            return null;
        }

  
        if (clave < nodo.clave) {
            nodo.izquierdo = eliminar(nodo.izquierdo, clave);
        } else if (clave > nodo.clave) {
            nodo.derecho = eliminar(nodo.derecho, clave);
        } else {
 
            if (nodo.izquierdo == null || nodo.derecho == null) {
                Nodo temp = (nodo.izquierdo != null) ? nodo.izquierdo : nodo.derecho;

                if (temp == null) {
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
g                Nodo sucesor = minimo(nodo.derecho);
                nodo.clave = sucesor.clave;
                nodo.derecho = eliminar(nodo.derecho, sucesor.clave);
            }
        }

        // Si el árbol quedó vacío
        if (nodo == null) {
            return null;
        }

        // Actualizar altura
        actualizarAltura(nodo);

        // Verificar balance
        int fb = factorBalance(nodo);

        // Caso LL
        if (fb > 1 && factorBalance(nodo.izquierdo) >= 0) {
            return Nodo.rotarDerecha(nodo);
        }

        // Caso LR
        if (fb > 1 && factorBalance(nodo.izquierdo) < 0) {
            return Nodo.rotarIzquierdaDerecha(nodo);
        }

        // Caso RR
        if (fb < -1 && factorBalance(nodo.derecho) <= 0) {
            return Nodo.rotarIzquierda(nodo);
        }

        // Caso RL
        if (fb < -1 && factorBalance(nodo.derecho) > 0) {
            return Nodo.rotarDerechaIzquierda(nodo);
        }

        return nodo;
    }

    // Mínimo de un subárbol (para la eliminación)
    private Nodo minimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual;
    }

    // Recorrido en orden para mostrar contenido del árbol
    private void enOrden(Nodo nodo) {
        if (nodo != null) {
            enOrden(nodo.izquierdo);
            System.out.print(nodo.clave + " ");
            enOrden(nodo.derecho);
        }
    }
}
