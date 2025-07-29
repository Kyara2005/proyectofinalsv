import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Huesped {
    private JButton modificarButton;
    private JButton regresarAlMenuButton;
    private JButton eliminarButton;
    private JButton reservarButton;
    private JButton limpiarCamposButton1;
    private JTextField nombre;
    private JTextField cedula;
    private JTextField telefono;
    private JTextField correo;
    private JTextField nacimiento;
    private JComboBox numhabitaciones;
    private JTextField ingreso;
    private JTextField salida;
    private JPanel principal;
    private JLabel Nombretxt;
    private JLabel cedulatxt;
    private JLabel telefonotxt;
    private JLabel correotxt;
    private JLabel fechanacimientotxt;
    private JLabel ingresotxt;
    private JLabel salidatxt;
    private JLabel habitaciones;
    private JTextField id;
    private JLabel IDtxt;

    public Huesped() {
        regresarAlMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nombre.getText();
                String ced = cedula.getText();
                String tel = telefono.getText();
                String corr = correo.getText();
                String nac = nacimiento.getText();
                String habSeleccionada = (String) numhabitaciones.getSelectedItem();
                String fechaIngreso = ingreso.getText();
                String fechaSalida = salida.getText();

                if (nom.isEmpty() || ced.isEmpty() || fechaIngreso.isEmpty() || fechaSalida.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos obligatorios");
                    return;
                }

                try {
                    Connection con = Conexion.getConnection();

                    // Verificamos si la habitación ya está ocupada
                    String sqlVerificar = "SELECT * FROM huesped WHERE habitacion = ? AND (fecha_ingreso <= ? AND fecha_salida >= ?)";
                    PreparedStatement psVerificar = con.prepareStatement(sqlVerificar);
                    psVerificar.setString(1, habSeleccionada);
                    psVerificar.setDate(2, java.sql.Date.valueOf(fechaSalida));  // se quiere reservar hasta esta fecha
                    psVerificar.setDate(3, java.sql.Date.valueOf(fechaIngreso)); // se quiere ingresar en esta fecha

                    ResultSet rs = psVerificar.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Esa habitación ya está ocupada para las fechas seleccionadas. Elija otra.");
                        return;
                    }

                    // Si no está ocupada, insertamos la reserva
                    String sql = "INSERT INTO huesped (nombre, cedula, telefono, correo, nacimiento, fecha_ingreso, fecha_salida, habitacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, nom);
                    ps.setString(2, ced);
                    ps.setString(3, tel);
                    ps.setString(4, corr);
                    ps.setDate(5, java.sql.Date.valueOf(nac));
                    ps.setString(6, habSeleccionada);
                    ps.setDate(7, java.sql.Date.valueOf(fechaIngreso));
                    ps.setDate(8, java.sql.Date.valueOf(fechaSalida));

                    int res = ps.executeUpdate();

                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "Reserva registrada con éxito.");
                        // limpiar campos si deseas
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al registrar la reserva.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });


        limpiarCamposButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
