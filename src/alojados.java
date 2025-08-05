import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
/**
 * Ventana encargada de la visualizacion de los huespedes alojados
 */
public class alojados extends JFrame {
    private JButton regresarButton;
    private JButton checkInButton;
    private JPanel principal;
    private JLabel informaciontxt;

    public alojados() {
        setTitle("Huéspedes Alojados");
        setContentPane(principal);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        ThemeManager.aplicarTema(this);


        cargarHuespedesActuales();

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Recepcionista();
                dispose();
            }
        });

        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reserva();
                dispose();
            }
        });
    }

    private void cargarHuespedesActuales() {
        try (Connection con = Conexion.getConnection()) {
            String sql = "SELECT id, nombre FROM huesped WHERE salida >= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ResultSet rs = ps.executeQuery();

            StringBuilder sb = new StringBuilder("<html><b>Huéspedes Alojados:</b><br><br>");
            boolean hayHuespedes = false;

            while (rs.next()) {
                hayHuespedes = true;
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                sb.append("🆔 ").append(id).append(" - ").append(nombre).append("<br>");
            }

            if (!hayHuespedes) {
                sb.append("No hay huéspedes alojados actualmente.");
            }

            sb.append("</html>");
            informaciontxt.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
            informaciontxt.setText("❌ Error al cargar la información.");
        }
    }
}

