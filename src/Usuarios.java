import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Usuarios extends JFrame {
    private JTextField textField1;
    private JButton buscarButton;
    private JButton regresarButton;
    private JPanel principal;
    private JLabel textField2;

    public Usuarios() {
        setTitle("Gestión de Usuarios");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(principal);
        setVisible(true);
        ThemeManager.aplicarTema(this);


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = textField1.getText().trim();
                LocalDate hoy = LocalDate.now();

                if (idTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del huésped.");
                    return;
                }

                int id;
                try {
                    id = Integer.parseInt(idTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un ID numérico válido.");
                    return;
                }

                try (Connection con = Conexion.getConnection()) {
                    String sql = "SELECT nombre, ingreso, salida, habitacion FROM huesped WHERE id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String nombre = rs.getString("nombre");
                        String habitacion = rs.getString("habitacion");
                        LocalDate ingreso = rs.getDate("ingreso").toLocalDate();
                        LocalDate salida = rs.getDate("salida").toLocalDate();

                        boolean alojado = !hoy.isAfter(salida);
                        long diasEstadia = java.time.temporal.ChronoUnit.DAYS.between(ingreso, salida);

                        String estado = alojado ? "Sigue alojado 🟢" : "Ya se retiró 🔴";

                        textField2.setText("<html><b>Nombre:</b> " + nombre +
                                "<br><b>Habitación:</b> " + habitacion +
                                "<br><b>Ingreso:</b> " + ingreso +
                                "<br><b>Salida:</b> " + salida +
                                "<br><b>Días de estadía:</b> " + diasEstadia +
                                "<br><b>Estado:</b> " + estado + "</html>");

                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún huésped con ese ID.");
                        textField2.setText("Sin resultados");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al consultar el huésped: " + ex.getMessage());
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
