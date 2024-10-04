package dungeon;

import java.util.Scanner;

// Bucle Principal del Juego
public class Juego {
	// Instancias
    private Jugador jugador;
    private Mazmorra mazmorra;
    //Indica si el juego está activo o si ha terminado
    private boolean juegoActivo;

    public Juego() {
        this.setJuegoActivo(true);
    }

    public void iniciar(Scanner scanner) {
        System.out.print("Ingresa el nombre de tu personaje: ");
        String nombreJugador = scanner.nextLine();
        this.jugador = new Jugador(nombreJugador);
        this.mazmorra = new Mazmorra(11, jugador); // Tamaño de Mapa

        // Bucle principal: Mientras el jugador esté en juego, sigue jugando
        while (jugador.isEnJuego()) {
            mazmorra.mostrarMapa();
            //Información del Jugador
            System.out.println("\nSalud: " + jugador.getSalud() + " | Ataque: " + jugador.getPuntosAtaque() + " | Defensa: " + jugador.getPuntosDefensa());
            System.out.println("Inventario: " + jugador.getInventarioNombres());
            
            System.out.print("\nIngresa un comando de movimiento (w/s/d/a) \no 'u' para usar un objeto o 'e' para salir: ");
            String comando = scanner.nextLine().toLowerCase();

            //Comandos
            // Opción para salir del juego
            if (comando.equals("e")) {
                System.out.println("\nVale vamos de vuelta al menu\n");
                setJuegoActivo(false);  // Terminar el bucle del juego
                break;
            }
            
            if (comando.equals("u")) {
                usarObjeto(jugador, scanner);
            } else {
                mazmorra.moverJugador(comando);
                verificarEstado();
            }
        }

        System.out.println("\nFin del juego.\n");  // Mensaje final al salir del bucle
    }

    private void verificarEstado() {
   
        if (jugador.getSalud() <= 0) {
            System.out.println("\n¡Que Nub!.");
            setJuegoActivo(false);
        }
    }

    public boolean isJuegoActivo() {
		return juegoActivo;
	}

	public void setJuegoActivo(boolean juegoActivo) {
		this.juegoActivo = juegoActivo;
	}

	private void usarObjeto(Jugador jugador, Scanner scanner) {
        if (jugador.getInventario().isEmpty()) {
            System.out.println("\nTu inventario está vacío.");
            return;
        }

        System.out.println("\nObjetos en tu inventario:");
        for (int i = 0; i < jugador.getInventario().size(); i++) {
            System.out.println((i + 1) + ". " + jugador.getInventario().get(i).getNombre());
        }
        System.out.print("\nElige el número del objeto que deseas usar: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            if (indice >= 0 && indice < jugador.getInventario().size()) {
                Objeto objeto = jugador.getInventario().get(indice);
                jugador.usarObjeto(objeto);
            } else {
                System.out.println("\nNúmero de objeto inválido.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nEntrada no válida.\n");
        }
    }
}
