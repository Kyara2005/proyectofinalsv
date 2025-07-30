import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame {
    private JButton gestionarHabitacionesButton;
    private JButton gestionarUsuariosButton;
    private JButton verReportesButton;
    private JButton cerrarSesionButton;
    private JPanel AdminPanel;
    private JButton salirButton;

    public Admin() {
        setTitle("Panel de Administrador");
        setContentPane(AdminPanel);
        setSize(550, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        gestionarHabitacionesButton.addActionListener(e -> {
            // Ventana de gestión de habitaciones
            JOptionPane.showMessageDialog(null, "Abriendo la sección de gestión de habitaciones");
            new Habitacion();
            dispose();
        });

        gestionarUsuariosButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Abriendo la sección de gestión de usuarios");
            new Usuarios();
            dispose();
        });

        verReportesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Abriendo la sección de reportes");
            new Reportes();
            dispose();
        });

        cerrarSesionButton.addActionListener(e -> {
            new Login();
            dispose();
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


}
