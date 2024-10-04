package dungeon;

import java.util.Random;

public class Mazmorra {
    private int tamanio; //Entero que representa el tamaño de la mazmorras
    private Jugador jugador; //Instancia que representa al jugador que está explorando la mazmorras
    private Enemigo[][] mapa; //Matriz de objetos que representa la ubicación de los enemigos en la mazmorras
    private Objeto[][] objetos;
    private Posicion posicionJugador; //Instancia que almacena la posición actual del jugador en la mazmorras
    private Posicion posicionSalida; //Instancia que representa la ubicación de la salida de la mazmorras
    private boolean[][] paredes;  // Mapa de paredes

    //El constructor inicializa la mazmorra
    public Mazmorra(int tamanio, Jugador jugador) {
        this.tamanio = tamanio;
        this.jugador = jugador;
        this.mapa = new Enemigo[tamanio][tamanio];
        this.objetos = new Objeto[tamanio][tamanio];
        this.paredes = new boolean[tamanio][tamanio];  // Inicializar mapa de paredes
        this.posicionJugador = new Posicion(tamanio / 2, tamanio / 2); // Jugador empieza en el centro
        this.posicionSalida = generarSalida();
        poblarMazmorra();
        colocarObjetos();
        generarParedes();
    }
    
    // Método que verifica si una posición está ocupada
    private boolean esPosicionOcupada(int x, int y) {
    	if (posicionSalida != null && x == posicionSalida.getX() && y == posicionSalida.getY()) {
            return true; // Si la salida ya fue generada y está en esta posición
        }
        return (mapa[x][y] != null || objetos[x][y] != null || paredes[x][y] || 
                (x == posicionJugador.getX() && y == posicionJugador.getY()));
    } 

 // Generar una salida en una posición aleatoria
    private Posicion generarSalida() {
        Random random = new Random();
        int x, y;
        do {
        	x = random.nextInt(tamanio);
        	y = random.nextInt(tamanio);
        	}while (esPosicionOcupada(x, y)); // Reintenta si la posición está ocupada
        return new Posicion(x, y);  // Posición aleatoria para la salida
    }

    
 // Poblar la mazmorra con enemigos
    private void poblarMazmorra() {
        Random random = new Random();
        
        for (int i = 0; i < 4; i++) {  // Numero de enemigos
            int x, y;
            do {
                x = random.nextInt(tamanio);
                y = random.nextInt(tamanio);
            } while (esPosicionOcupada(x, y)); // Reintenta si la posición está ocupada

            // Seleccionamos aleatoriamente el tipo de enemigo
            int tipoEnemigo = random.nextInt(3);  // 3 tipos de enemigos

            Enemigo enemigo;
            switch (tipoEnemigo) {
                case 0:
                    enemigo = new EnemigoGoblin();  // Crear un goblin
                    break;
                case 1:
                    enemigo = new EnemigoLobo();  // Crear un Lobo
                    break;
                default:
                    enemigo = new EnemigoEsqueleto();  // Crear un esqueleto
                    break;
         }
            
            // Colocar el enemigo en el mapa
           
            mapa[x][y] = enemigo;
        }
    }
    
    // Colocar objetos en el mapa
    private void colocarObjetos() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) { // Numero de objetos
            int x,y;
            do{
            	x = random.nextInt(tamanio);
            	y = random.nextInt(tamanio);
            } while (esPosicionOcupada(x, y)); // Reintenta si la posición está ocupada
            
            objetos[x][y] = new Pocion("Poción de curación", 10);
        }
    }
    
    // Generar paredes aleatorias
    private void generarParedes() {
        Random random = new Random();
        int cantidadParedes = 10;  // Número de paredes a generar
        for (int i = 0; i < cantidadParedes; i++) {
            int x, y;
            do {
                x = random.nextInt(tamanio);
                y = random.nextInt(tamanio);
            } while (esPosicionOcupada(x, y)); // Reintenta si la posición está ocupada

            paredes[x][y] = true;
        }
    }
    

    // Mostrar el mapa con símbolos representativos
    public void mostrarMapa() {
        for (int i = 0; i < tamanio; i++) {
        	for (int j = 0; j < tamanio; j++) {  
                String simbolo = ". "; // Espacio vacío por defecto  
                if (i == posicionJugador.getX() && j == posicionJugador.getY()) {  
                    simbolo = "🙍"; // Jugador  
                } else if (i == posicionSalida.getX() && j == posicionSalida.getY()) {  
                    simbolo = "🚪"; // Salida  
                } else if (mapa[i][j] != null) {  
                    // Obtener el icono del enemigo  
                    simbolo = mapa[i][j].getIcono(); // Asegúrate de que este icono sea de ancho uniforme  
                } else if (objetos[i][j] != null) {  
                    simbolo = "🥑"; // Objeto  
                } else if (paredes[i][j]) {  
                    simbolo = "🧱"; // Pared  
                }  
                
             // Imprimir con formato fijo 
                System.out.printf("%-1s", simbolo); // Utiliza un ancho fijo para cada símbolo 
            }  
            System.out.println(); 
        }
    }
    
 // Método para verificar si la nueva posición es válida
    private boolean esPosicionValida(Posicion nuevaPosicion) {
        int x = nuevaPosicion.getX();
        int y = nuevaPosicion.getY();
        // Verificar que la posición no esté fuera del límite y que no sea una pared
        return (x >= 0 && x < tamanio && y >= 0 && y < tamanio && !paredes[x][y]);
    }

    public void moverJugador(String direccion) {
        // Nueva posición propuesta basada en la dirección
        Posicion nuevaPosicion = posicionJugador.mover(direccion, tamanio);

        // Verificamos si la nueva posición es válida (sin paredes)
        if (!esPosicionValida(nuevaPosicion)) {
            System.out.println("\nNo puedes moverte en esa dirección. Hay una pared.\n");
            return;
        }

        // Verificamos si hay un enemigo en la nueva posición
        Enemigo enemigo = mapa[nuevaPosicion.getX()][nuevaPosicion.getY()];
        if (enemigo != null) {
        	;
            iniciarCombate(enemigo);
            
            // Si el enemigo ha sido derrotado, permitir mover al jugador
            if (enemigo.getSalud() <= 0) {
                posicionJugador = nuevaPosicion;  // Mover al jugador a la posición del enemigo derrotado
                mapa[nuevaPosicion.getX()][nuevaPosicion.getY()] = null;  // Eliminar al enemigo del mapa
                System.out.println("Te has movido a la posición (" + nuevaPosicion.getX() + ", " + nuevaPosicion.getY() + ").");
            }
            return;
        }

        // Si no hay enemigo y la posición es válida, mover al jugador
        posicionJugador = nuevaPosicion;
        System.out.println("Te has movido a la posición (" + nuevaPosicion.getX() + ", " + nuevaPosicion.getY() + ").");
        
        // El jugador interactua con un objeto    
	    if (objetos[nuevaPosicion.getX()][nuevaPosicion.getY()] != null) {
	        Objeto objeto = objetos[nuevaPosicion.getX()][nuevaPosicion.getY()];
	        jugador.recogerObjeto(objeto);
	        objetos[nuevaPosicion.getX()][nuevaPosicion.getY()] = null; // Eliminar objeto después de recogerlo
	        
	    } else {
	        posicionJugador = nuevaPosicion;
	    }

	    
        // Verificar si el jugador ha llegado a la salida
        if (nuevaPosicion.equals(posicionSalida)) {
            System.out.println("\n¡Felicidades! Has encontrado la salida de la mazmorra.\nGanaste la partida, ¡Que Pro!.\n");
            jugador.setEnJuego(false);  // Finalizar el juego
        }
    }


 // Inicia un combate entre el jugador y un enemigo
    private void iniciarCombate(Enemigo enemigo) {
        System.out.println("\nTe has encontrado con un " + enemigo.getTipo() + "!\n");

        // El jugador ataca primero
        jugador.atacar(enemigo);

        // Si el enemigo sobrevive, puede atacar de vuelta
        if (enemigo.getSalud() > 0) {
            enemigo.atacar(jugador);

            // Verificar si el jugador ha sido derrotado
            if (jugador.getSalud() <= 0) {
                System.out.println("¡\nEl que te derroto fue un " + enemigo.getTipo() + "!\n");
                jugador.setEnJuego(false);  // Finalizar el juego si el jugador muere
            }
        } else {
            // Eliminar al enemigo del mapa si su salud es 0 o menor
            eliminarEnemigoDelMapa(enemigo);
            System.out.println(enemigo.getTipo() + " ha sido derrotado y eliminado del mapa.");
        }
    }

    // Eliminar al enemigo del mapa
    private void eliminarEnemigoDelMapa(Enemigo enemigo) {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                // Si encontramos al enemigo en la posición [i][j], lo eliminamos
                if (mapa[i][j] == enemigo) {
                    mapa[i][j] = null;  // Eliminar al enemigo de esta posición
                    return;  // Salir del método después de eliminar al enemigo
                }
            }
        }
    }

    public boolean estaEnSalida() {
        // Implementación de regla de salida
        return false;
    }
}
