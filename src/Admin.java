import javax.swing.*;

public class Admin extends JFrame {
    private JButton gestionarHabitacionesButton;
    private JButton gestionarUsuariosButton;
    private JButton verReportesButton;
    private JButton cerrarSesionButton;

    public Admin() {
        setTitle("Panel de Administrador");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

}
