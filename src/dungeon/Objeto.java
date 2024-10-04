package dungeon;

//No se puede instanciar directamente. Solo las subclases que la extiendan pueden ser creadas
public abstract class Objeto {
    private String nombre; // Atributo

    // Métodos
    public Objeto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void usar(Jugador jugador);
}
