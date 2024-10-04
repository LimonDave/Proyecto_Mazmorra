package dungeon;

public class Posicion {
	// Atributos Cordenadas
    private int x;
    private int y;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método que pone limites a las cordenadas
    public Posicion mover(String direccion, int limite) {
        switch (direccion) {
            case "w":
                return new Posicion(Math.max(x - 1, 0), y);
            case "s":
                return new Posicion(Math.min(x + 1, limite - 1), y);
            case "d":
                return new Posicion(x, Math.min(y + 1, limite - 1));
            case "a":
                return new Posicion(x, Math.max(y - 1, 0));
            default:
                System.out.println("\nDirección no válida.\n");
                return this; // Devuelve la posición actual si la dirección no es válida
        }
    }

    //Métodos getter: Permiten acceder a las coordenadas desde fuera de la clase
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    // Método equals para comparar posiciones
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  // Si es la misma instancia
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;  // Si no es de la clase Posicion
        }
        Posicion otra = (Posicion) obj;  // Convertir a Posicion
        return x == otra.x && y == otra.y;  // Comparar coordenadas
    }
}
