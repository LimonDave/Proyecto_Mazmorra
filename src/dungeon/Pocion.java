package dungeon;

public class Pocion extends Objeto { //Subclase de la clase abstracta Objeto
	
	//Atributos
    private int puntosCuracion; 

    public Pocion(String nombre, int puntosCuracion) { 
        super(nombre);
        this.puntosCuracion = puntosCuracion;
    }

    @Override
    public void usar(Jugador jugador) {
        jugador.setSalud(jugador.getSalud() + puntosCuracion);
        System.out.println(jugador.getNombre() + " ha usado " + getNombre() + " y ha recuperado " + puntosCuracion + " puntos de salud.");
    }
}
