import javax.swing.*;
import java.awt.*;

public class PanelConFondoGIF extends JPanel {
    private final ImageIcon fondo;

    public PanelConFondoGIF(String rutaGIF) {
        this.fondo = new ImageIcon(getClass().getResource(rutaGIF));
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
