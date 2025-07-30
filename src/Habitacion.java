import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.ResultSet;

public class Habitacion extends JFrame {
    private JTextField textField1;
    private JButton buscarButton;
    private JButton regresarButton;
    private JPanel principal;
    private JLabel estadoHabitacion;

    public Habitacion() {
        setTitle("Gestión de Habitaciones");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(principal);
        setVisible(true);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String habitacionIngresada = textField1.getText().trim();
                LocalDate hoy = LocalDate.now();

                // Validar que no esté vacío y que sea un número válido
                if (habitacionIngresada.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número de habitación.");
                    return;
                }

                try {
                    int numero = Integer.parseInt(habitacionIngresada);
                    if (numero < 101 || numero > 109) {
                        JOptionPane.showMessageDialog(null, "El número de habitación debe estar entre 101 y 109.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido de habitación.");
                    return;
                }

                // Consulta en la base de datos
                try {
                    Connection con = Conexion.getConnection();
                    String sql = "SELECT * FROM huesped WHERE habitacion = ? AND ? BETWEEN ingreso AND salida";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, habitacionIngresada);
                    ps.setDate(2, java.sql.Date.valueOf(hoy));

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        estadoHabitacion.setText("OCUPADA");
                    } else {
                        estadoHabitacion.setText("DISPONIBLE");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al consultar el estado: " + ex.getMessage());
                }
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Admin();
                dispose();
            }
        });
    }
}
