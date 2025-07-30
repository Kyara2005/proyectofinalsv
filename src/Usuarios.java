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

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = textField1.getText().trim();
                LocalDate hoy = LocalDate.now();

                if (idTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del huésped.");
                    return;
                }

                try {
                    int id = Integer.parseInt(idTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un ID numérico válido.");
                    return;
                }

                try {
                    Connection con = Conexion.getConnection();
                    String sql = "SELECT nombre, ingreso, salida FROM huesped WHERE id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(idTexto));

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        String nombre = rs.getString("nombre");
                        LocalDate salida = rs.getDate("salida").toLocalDate();

                        if (hoy.isBefore(salida) || hoy.isEqual(salida)) {
                            JOptionPane.showMessageDialog(null, "El huésped \"" + nombre + "\" sigue alojado.");
                        } else {
                            JOptionPane.showMessageDialog(null, "El huésped \"" + nombre + "\" ya se retiró.");
                        }
                        if (rs.next()) {
                            textField2.setText("El huesped ya se retiró");
                        } else {
                            textField2.setText("Sigue alojado");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún huésped con ese ID.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
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
