import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva extends JFrame {
    private JButton buscarButton;
    private JButton checkOutButton;
    private JButton regresarButton;
    private JTextField textField1; // Aquí se ingresa el ID
    private JButton comentariosButton;
    private JPanel ReservaPanel;
    private JLabel resumenLabel;

    private String nombre;
    private String habitacion;
    private LocalDate ingreso;
    private LocalDate salida;
    private int idActual;

    public Reserva() {
        setTitle("Check-Out / Reserva");
        setContentPane(ReservaPanel);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        ThemeManager.aplicarTema(this);


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = textField1.getText().trim();
                if (idTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese el ID del huésped.");
                    return;
                }

                try {
                    int id = Integer.parseInt(idTexto);
                    idActual = id;

                    Connection con = Conexion.getConnection();
                    String sql = "SELECT nombre, habitacion, ingreso, salida FROM huesped WHERE id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        nombre = rs.getString("nombre");
                        habitacion = rs.getString("habitacion");
                        ingreso = rs.getDate("ingreso").toLocalDate();
                        salida = rs.getDate("salida").toLocalDate();

                        long dias = ChronoUnit.DAYS.between(ingreso, salida);
                        double total = dias * 50;

                        resumenLabel.setText("<html><b>Nombre:</b> " + nombre +
                                "<br><b>Habitación:</b> " + habitacion +
                                "<br><b>Ingreso:</b> " + ingreso +
                                "<br><b>Salida:</b> " + salida +
                                "<br><b>Noches:</b> " + dias +
                                "<br><b>Total a pagar:</b> $" + total + "</html>");
                    } else {
                        resumenLabel.setText("No se encontró ningún huésped con ese ID.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser numérico.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al consultar: " + ex.getMessage());
                }
            }
        });

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idActual == 0) {
                    JOptionPane.showMessageDialog(null, "Primero debe buscar al huésped.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Confirmar check-out de " + nombre + "?", "Confirmar",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Connection con = Conexion.getConnection();
                        String sql = "DELETE FROM huesped WHERE id = ?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, idActual);
                        int res = ps.executeUpdate();

                        if (res > 0) {
                            JOptionPane.showMessageDialog(null, "Check-out realizado correctamente.");
                            resumenLabel.setText("Check-out completado para " + nombre);
                            idActual = 0;
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo realizar el check-out.");
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al hacer check-out: " + ex.getMessage());
                    }
                }
            }
        });

        regresarButton.addActionListener(e -> {
            new Recepcionista();
            dispose();
        });

        comentariosButton.addActionListener(e -> new Comentarios());
    }
}
