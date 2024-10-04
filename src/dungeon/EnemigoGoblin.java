package dungeon;

public class EnemigoGoblin extends Enemigo {
    public EnemigoGoblin() {
        super("Goblin", 60, 15, 15);
    }
    
    @Override
    public String getIcono() {
        return "👺";  // Icono para Goblin
    }
}