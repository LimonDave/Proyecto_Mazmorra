package dungeon;

public class Enemigo {
	// Atributos
    private String tipo;
    private int salud;
    private int puntosAtaque;
    private int puntosDefensa;
    // Cordenadas del enemigo en la Mazmorra
    private int posX;
    private int posY;

    public Enemigo(String tipo, int salud, int puntosAtaque, int puntosDefensa) {
        this.tipo = tipo;
        this.salud = salud;
        this.puntosAtaque = puntosAtaque;
        this.puntosDefensa = puntosDefensa;
    }

    // Metodos
    public void atacar(Jugador jugador) {
        System.out.println(tipo + " ataca al jugador con " + puntosAtaque + " puntos de ataque.");
        jugador.recibirDanio(puntosAtaque);  // Restar salud del jugador
    }

    public void recibirDanio(int danio) {
        int danioFinal = danio - puntosDefensa;
        if (danioFinal < 0) {
            danioFinal = 0;
        }
        this.salud -= danioFinal;
        System.out.println(tipo + " recibe " + danioFinal + " puntos de daño. Salud restante: " + salud);

        if (salud <= 0) {
            System.out.println(tipo + " ha sido derrotado.\n");
        }
    }

    
    // Método para obtener el ícono del enemigo
    public String getIcono() {
        return "💀";  // Icono por defecto para los enemigos
    }
    
    //Métodos Getters y Setters: Permiten acceder y modificar los atributos del enemigo
    public String getTipo() {
        return tipo;
    }

    public int getSalud() {
        return salud;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public int getPuntosDefensa() {
        return puntosDefensa;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    
    // Establecen las cordenadas de los enemigos
    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}