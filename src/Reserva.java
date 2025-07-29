import javax.swing.*;

public class Reserva extends JFrame{
    private JButton buscarButton;
    private JButton checkOutButton;
    private JButton regresarButton;
    private JTextField textField1;
    private JButton comentariosButton;
    private JPanel ReservaPanel;

    public Reserva() {
        setTitle("Check-Out / Reserva");
        setContentPane(ReservaPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
