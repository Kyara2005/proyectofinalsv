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

        gestionarHabitacionesButton.addActionListener(e -> {
            // Ventana de gestión de habitaciones
            JOptionPane.showMessageDialog(null, "Aquí irá la gestión de habitaciones");
            new Habitacion(); // Abre ventana (aún esqueleto)
        });

        gestionarUsuariosButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Aquí irá la gestión de usuarios");
            new Usuarios();
        });

        verReportesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Aquí irán los reportes");
            new Reportes();
        });

        cerrarSesionButton.addActionListener(e -> {
            new Login();
            dispose();
        });
        gestionarHabitacionesButton.addActionListener(e -> {
            // Ventana de gestión de habitaciones
            JOptionPane.showMessageDialog(null, "Aquí irá la gestión de habitaciones");
            new Habitacion(); // Abre ventana (aún esqueleto)
        });

        gestionarUsuariosButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Aquí irá la gestión de usuarios");
            new Usuarios();
        });

        verReportesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Aquí irán los reportes");
            new Reportes();
        });

        cerrarSesionButton.addActionListener(e -> {
            new Login();
            dispose();
        });

    }


}
