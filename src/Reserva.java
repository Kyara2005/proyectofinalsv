import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.*;


public class Reserva extends JFrame{
    private JButton buscarButton;
    private JButton checkOutButton;
    private JButton regresarButton;
    private JTextField buscartxt;
    private JButton comentariosButton;
    private JPanel ReservaPanel;
    private JLabel resumen;
    private JLabel total;
    private int idReserva;
    private String numeroHabitacion;
    private double precioPorNoche;
    private int noches;


    public Reserva() {
        setTitle("Check-Out / Reserva");
        setContentPane(ReservaPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        comentariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Comentarios();
                dispose();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = buscartxt.getText();

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base", "usuario", "contraseña")) {
                    String sql = "SELECT * FROM reservas WHERE id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(texto));
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        idReserva = rs.getInt("id");
                        numeroHabitacion = rs.getString("habitacion");
                        noches = rs.getInt("noches");
                        precioPorNoche = rs.getDouble("precio_por_noche");

                        double totalReserva = noches * precioPorNoche;

                        resumen.setText("Habitación: " + numeroHabitacion + " | Noches: " + noches + " | Precio/Noche: $" + precioPorNoche);
                        total.setText("Total: $" + totalReserva);
                    } else {
                        JOptionPane.showMessageDialog(null, "Reserva no encontrada.");
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar reserva: " + ex.getMessage());
                }
            }
        });

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = buscartxt.getText().trim();
                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresa la cédula del huésped.");
                    return;
                }

                try (Connection conn = Conexion.getConnection()) {
                    if (conn == null) {
                        JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
                        return;
                    }

                    // Buscar datos de la reserva activa
                    String query = "SELECT r.id_reserva, r.total, r.habitacion_id FROM reservas r " +
                            "JOIN huespedes h ON r.huesped_id = h.id " +
                            "WHERE h.cedula = ? AND r.estado = 'activa'";

                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, cedula);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        int reservaId = rs.getInt("id_reserva");
                        double totalPagar = rs.getDouble("total");
                        int habitacionId = rs.getInt("habitacion_id");

                        // Mostrar resumen y total
                        resumen.setText("Reserva #" + reservaId + " - Habitación: " + habitacionId);
                        total.setText("$" + totalPagar);

                        // Marcar la reserva como finalizada
                        String actualizarReserva = "UPDATE reservas SET estado = 'finalizada' WHERE id_reserva = ?";
                        PreparedStatement updateReserva = conn.prepareStatement(actualizarReserva);
                        updateReserva.setInt(1, reservaId);
                        updateReserva.executeUpdate();

                        // Marcar la habitación como disponible
                        String actualizarHabitacion = "UPDATE habitaciones SET estado = 'disponible' WHERE id = ?";
                        PreparedStatement updateHab = conn.prepareStatement(actualizarHabitacion);
                        updateHab.setInt(1, habitacionId);
                        updateHab.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Check-Out exitoso. Habitación liberada.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró una reserva activa para esa cédula.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Recepcionista();
                dispose();
            }
        });
    }
}
