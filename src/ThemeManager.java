import javax.swing.*;
import java.awt.*;

public class ThemeManager {

    public static void aplicarTema(JFrame frame) {
        // Fondo crema
        frame.getContentPane().setBackground(Color.decode("#F5F5DC"));

        // Fuente elegante
        UIManager.put("Label.font", new Font("Georgia", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Georgia", Font.BOLD, 13));
        UIManager.put("TextField.font", new Font("Georgia", Font.PLAIN, 13));
        UIManager.put("ComboBox.font", new Font("Georgia", Font.PLAIN, 13));
        UIManager.put("Panel.background", Color.decode("#F5F5DC"));

        // Colores de texto y fondo
        UIManager.put("Button.background", Color.decode("#D7CCC8"));
        UIManager.put("Button.foreground", Color.decode("#4E342E"));
        UIManager.put("Label.foreground", Color.decode("#4E342E"));
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("TextField.foreground", Color.decode("#4E342E"));
        UIManager.put("ComboBox.background", Color.WHITE);
        UIManager.put("ComboBox.foreground", Color.decode("#4E342E"));
    }
}
