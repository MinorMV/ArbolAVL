import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolAVL arbol = new ArbolAVL();
        int opcion = -1;

        do {
            System.out.println("\n====================================");
            System.out.println("       MENÚ ÁRBOL AVL - Minor Mora");
            System.out.println("====================================");
            System.out.println("1. Insertar clave");
            System.out.println("2. Buscar clave");
            System.out.println("3. Eliminar clave");
            System.out.println("4. Mostrar árbol en orden");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido.");
                sc.nextLine(); // limpiar buffer
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la clave a insertar: ");
                    try {
                        int claveInsertar = sc.nextInt();
                        arbol.insertar(claveInsertar);
                        System.out.println("Clave insertada correctamente.");
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido. Debe ser un número entero.");
                        sc.nextLine();
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la clave a buscar: ");
                    try {
                        int claveBuscar = sc.nextInt();
                        boolean encontrada = arbol.buscar(claveBuscar);
                        if (encontrada) {
                            System.out.println("La clave " + claveBuscar + " SÍ se encuentra en el árbol.");
                        } else {
                            System.out.println("La clave " + claveBuscar + " NO se encuentra en el árbol.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido. Debe ser un número entero.");
                        sc.nextLine();
                    }
                    break;

                case 3:
                    System.out.print("Ingrese la clave a eliminar: ");
                    try {
                        int claveEliminar = sc.nextInt();
                        arbol.eliminar(claveEliminar);
                        System.out.println("Si la clave existía, ha sido eliminada.");
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido. Debe ser un número entero.");
                        sc.nextLine();
                    }
                    break;

                case 4:
                    arbol.mostrarEnOrden();
                    break;

                case 0:
                    System.out.println("Saliendo del programa. ¡Gracias!");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 0);

        sc.close();
        
    }
}
