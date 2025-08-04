import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Reportes extends JFrame {
    private JPanel principal;
    private JButton regresarButton;
    private JLabel comentariotxt;

    public Reportes() {
        setTitle("Reportes");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(principal);
        setVisible(true);
        ThemeManager.aplicarTema(this);


        cargarComentario();

        regresarButton.addActionListener(e -> dispose());
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Admin();
                dispose();
            }
        });
    }

    private void cargarComentario() {
        try (Connection con = Conexion.getConnection()) {
            String sql = "SELECT comentario FROM comentarios ORDER BY comentario DESC LIMIT 1"; // para MySQL usa ID si tienes
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                comentariotxt.setText("<html><body style='width: 200px'>" + rs.getString("comentario") + "</body></html>");
            } else {
                comentariotxt.setText("No hay comentarios aún.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            comentariotxt.setText("Error al cargar comentario.");
        }
    }
}

