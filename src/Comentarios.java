import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 * Ventana para que los usuarios puedan dar sus recomendaciones o comentarios
 */
public class Comentarios extends JFrame{
    private JTextField textField1;
    private JPanel principal;
    private JButton guardarComentarioButton;
    private JButton regresarButton;


    public Comentarios(){
        setTitle("Check-In");
        setContentPane(principal);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        ThemeManager.aplicarTema(this);

        guardarComentarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comentario = textField1.getText().trim();

                if (comentario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El comentario no puede estar vacío.");
                    return;
                }

                try (Connection con = Conexion.getConnection()) {
                    String sql = "INSERT INTO comentarios (comentario) VALUES (?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, comentario);
                    int res = ps.executeUpdate();

                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "¡Comentario guardado!");
                        textField1.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo guardar el comentario.");
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
                new Recepcionista();
                dispose();
            }
        });
    }
}
