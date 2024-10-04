package dungeon;

public class EnemigoLobo extends Enemigo {
    public EnemigoLobo() {
        super("Lobo", 40, 18, 16);
    }
    
    @Override
    public String getIcono() {
        return "🐺";  // Icono para Lobo
    }
}