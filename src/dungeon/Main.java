package dungeon;

import java.util.Scanner; //Se usa para leer la entrada del usuario desde la consola

public class Main {
    public static void main(String[] args) { //Método main
        Scanner scanner = new Scanner(System.in); //Lee la entrada del usuario
        Juego juego = new Juego(); //Maneja la lógica del juego
        boolean juegoActivo = true; //Controla si el juego está en funcionamiento

        //Bucle Principal del Juego
        while (juegoActivo) {
            mostrarMenuPrincipal();
            String opcion = scanner.nextLine(); //Manejo de Opciones

            switch (opcion) {
                case "1":
                    juego.iniciar(scanner);  // Mover la lógica del juego a la clase `Juego`
                    break;
                case "2":
                    mostrarInstrucciones();
                    break;
                case "3":
                	mostrarCreditos();
                	break;
                case "4":
                    System.out.println("\n¡Gracias por jugar!\n"); //Salida del Bucle
                    juegoActivo = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del 1 al 3.\n");
            }
        }

        scanner.close();
    }
    
    //Métodos para mostrar el Menú y las Instrucciones
    private static void mostrarMenuPrincipal() {
        System.out.println("=== Aventura en la Mazmorra ===\n");
        System.out.println("1. Iniciar Nuevo Juego");
        System.out.println("2. Ver Instrucciones");
        System.out.println("3. Creditos");
        System.out.println("4. Salir");
        System.out.print("\nElige una opción: ");
    }

    private static void mostrarInstrucciones() {
        System.out.println("\n=== Instrucciones del Juego ===\n");
        System.out.println("Comandos: w (Arriba), s (Abajo), d (Derecha), a (Izquierda)\n");
        System.out.println("Enfréntate a enemigos (👹, 🐺, 💀), usa objetos (🥑) para mejorar tus habilidades,");
        System.out.println("esquiva las paredes (🧱) y arriesga tu vida hasta encontrar la salida \nsi es que hay una 👿.\n");
        System.out.println("\nTu objetivo es encontrar la salida (🚪) de la mazmorra.\n");
        System.out.println("¡Buena suerte! 😀👍\n");
    }
    
    private static void mostrarCreditos() {
    	System.out.println("\n=== Creditos ===\n");
    	System.out.println("Esta Dungeon fue creado por Perez Limon David Enrique.\nEstudiante de Ingenieria en Mecatronica.\nProyecto de la Materia de Programacion Avanzada.\n");
    	System.out.println("Inspirado en Tragones y Mazmorras.\n");
    	
    	
    }
}
