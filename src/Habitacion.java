import javax.swing.*;

public class Habitacion extends JFrame{
    private JTextField textField1;
    private JButton buscarButton;
    private JButton regresarButton;
    private JPanel principal;

    public Habitacion() {
        setTitle("Gestión de Habitaciones");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setContentPane(principal);
    }
}
