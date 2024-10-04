package dungeon;

public class EnemigoEsqueleto extends Enemigo {
    public EnemigoEsqueleto() {
        super("Esqueleto", 40, 13, 15);
    }

    @Override
    public String getIcono() {
        return "💀";  // Icono para Esqueleto
    }
}