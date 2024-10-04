package dungeon;

import java.util.ArrayList;

public class Jugador { //Atributos que definen las características del jugador
    private String nombre;
    private int salud;
    private int puntosAtaque;
    private int puntosDefensa;
    private ArrayList<Objeto> inventario; //Lista de objetos que el jugador puede recoger y usar durante el juego
    private boolean enJuego; 

    // Atributos iniciales del jugador
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.salud = 100;
        this.puntosAtaque = 20;
        this.puntosDefensa = 10;
        this.inventario = new ArrayList<>();
        this.enJuego = true; 
    }
    
    public void setEnJuego(boolean enJuego) {
        this.enJuego = enJuego;
    }

    public boolean isEnJuego() {
        return enJuego;
    }

    // Movimiento
    public void moverse(String direccion) {
        System.out.println(nombre + " se mueve hacia " + direccion);
    }

    // Atacar a un enemigo
    public void atacar(Enemigo enemigo) {
        System.out.println(nombre + " ataca a " + enemigo.getTipo() + " con " + puntosAtaque + " puntos de ataque.");
        enemigo.recibirDanio(puntosAtaque);  // Restar salud del enemigo
    }

    // Recibir daño
    public void recibirDanio(int danio) {
        int danioFinal = danio - puntosDefensa;
        if (danioFinal < 0) {
            danioFinal = 0;
        }
        this.salud -= danioFinal;
        System.out.println(nombre + " recibe " + danioFinal + " puntos de daño. Salud restante: " + salud);

        if (salud <= 0) {
            System.out.println(nombre + " ha sido derrotado.");
        }
    }

    // Recoger objeto
    public void recogerObjeto(Objeto objeto) {
        inventario.add(objeto);
        System.out.println(nombre + " ha recogido " + objeto.getNombre());
    }

    // Usar objeto
    public void usarObjeto(Objeto objeto) {
        if (inventario.contains(objeto)) {
            objeto.usar(this);
            inventario.remove(objeto);
        } else {
            System.out.println("El objeto no está en el inventario.");
        }
    }

    // Getters y Setters: Permiten acceder y modificar los atributos del jugador
    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }

    public int getPuntosDefensa() {
        return puntosDefensa;
    }

    public void setPuntosDefensa(int puntosDefensa) {
        this.puntosDefensa = puntosDefensa;
    }

    public ArrayList<Objeto> getInventario() {
        return inventario;
    }

    public String getInventarioNombres() {
        if (inventario.isEmpty()) {
            return "Vacio";
        }
        StringBuilder nombres = new StringBuilder();
        for (Objeto objeto : inventario) {
            nombres.append(objeto.getNombre()).append(", ");
        }
        return nombres.substring(0, nombres.length() - 2);
    }
}