import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CheckIn extends JFrame {
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
    private JTextField id;
    private JButton buscarPorIDButton;

    public CheckIn() {
        setTitle("Check-In");
        setContentPane(principal);
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        ThemeManager.aplicarTema(this);


        regresarAlMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Recepcionista();
                dispose();

            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idHuesped = id.getText().trim();
                String nom = nombre.getText();
                String ced = cedula.getText();
                String tel = telefono.getText();
                String corr = correo.getText();
                String nac = nacimiento.getText();
                String habSeleccionada = (String) numhabitaciones.getSelectedItem();
                String fechaIngreso = ingreso.getText();
                String fechaSalida = salida.getText();

                if (idHuesped.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese el ID del huésped que desea modificar.");
                    return;
                }

                try {
                    Connection con = Conexion.getConnection();
                    String sql = "UPDATE huesped SET nombre=?, cedula=?, telefono=?, correo=?, nacimiento=?, ingreso=?, salida=?, habitacion=? WHERE id=?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, nom);
                    ps.setString(2, ced);
                    ps.setString(3, tel);
                    ps.setString(4, corr);
                    ps.setDate(5, java.sql.Date.valueOf(nac));
                    ps.setDate(6, java.sql.Date.valueOf(fechaIngreso));
                    ps.setDate(7, java.sql.Date.valueOf(fechaSalida));
                    ps.setString(8, habSeleccionada);
                    ps.setInt(9, Integer.parseInt(idHuesped));

                    int res = ps.executeUpdate();

                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "Datos actualizados correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró un huésped con ese ID.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage());
                }

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idHuesped = id.getText().trim();

                if (idHuesped.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese el ID del huésped que desea eliminar.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este huésped?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                try {
                    Connection con = Conexion.getConnection();
                    String sql = "DELETE FROM huesped WHERE id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(idHuesped));

                    int res = ps.executeUpdate();

                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "Huésped eliminado correctamente.");
                        limpiarCamposButton1.doClick(); // Limpia los campos después de eliminar
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró un huésped con ese ID.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
                }
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

                    // Verificar si la habitación está ocupada
                    String sqlVerificar = "SELECT * FROM huesped WHERE habitacion = ? AND ingreso <= ? AND salida >= ?";
                    PreparedStatement psVerificar = con.prepareStatement(sqlVerificar);
                    psVerificar.setString(1, habSeleccionada);
                    psVerificar.setDate(2, java.sql.Date.valueOf(fechaSalida));
                    psVerificar.setDate(3, java.sql.Date.valueOf(fechaIngreso));

                    ResultSet rs = psVerificar.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Habitación ocupada. Elija otra o cambie las fechas.");
                        return;
                    }

                    // ✅ Insertar la nueva reserva y obtener el ID generado
                    String sql = "INSERT INTO huesped (nombre, cedula, telefono, correo, nacimiento, ingreso, salida, habitacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, nom);
                    ps.setString(2, ced);
                    ps.setString(3, tel);
                    ps.setString(4, corr);
                    ps.setDate(5, java.sql.Date.valueOf(nac));
                    ps.setDate(6, java.sql.Date.valueOf(fechaIngreso));
                    ps.setDate(7, java.sql.Date.valueOf(fechaSalida));
                    ps.setString(8, habSeleccionada);

                    int res = ps.executeUpdate();

                    if (res > 0) {
                        ResultSet generatedKeys = ps.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int idGenerado = generatedKeys.getInt(1);
                            JOptionPane.showMessageDialog(null, "✅ Reserva registrada con éxito.\nSu ID de reserva es: " + idGenerado);
                        } else {
                            JOptionPane.showMessageDialog(null, "Reserva registrada, pero no se pudo obtener el ID.");
                        }
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
                nombre.setText("");
                cedula.setText("");
                telefono.setText("");
                correo.setText("");
                nacimiento.setText("");
                ingreso.setText("");
                salida.setText("");
                id.setText("");
                numhabitaciones.setSelectedIndex(0);
            }
        });

        buscarPorIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idHuesped = id.getText().trim();

                if (idHuesped.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese el ID del huésped que desea buscar.");
                    return;
                }

                try {
                    Connection con = Conexion.getConnection();
                    String sql = "SELECT * FROM huesped WHERE id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(idHuesped));

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        nombre.setText(rs.getString("nombre"));
                        cedula.setText(rs.getString("cedula"));
                        telefono.setText(rs.getString("telefono"));
                        correo.setText(rs.getString("correo"));
                        nacimiento.setText(rs.getString("nacimiento"));
                        ingreso.setText(rs.getString("ingreso"));
                        salida.setText(rs.getString("salida"));
                        numhabitaciones.setSelectedItem(rs.getString("habitacion"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró un huésped con ese ID.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar: " + ex.getMessage());
                }
            }
        });
    }
}
