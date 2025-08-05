import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Ventana encargada de la entrada identificando el que quiera entrar como administrador o recepcionista
 */
public class Login extends JFrame {
    private JTextField textField1;
    private JButton recepcionistaButton;
    private JButton administradorButton;
    private JPanel principal;
    private JPasswordField passwordField1;
    private JLabel usuarioIcono;
    private JLabel contraseñaa;

    public Login() {

        setContentPane(principal);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        ThemeManager.aplicarTema(this);


        ImageIcon icono = new ImageIcon("src/recursos/agregar-usuario.png");
        ImageIcon icono2 = new ImageIcon("src/recursos/seguro.png");
        Image image = icono.getImage();
        Image image1 = icono2.getImage();

        Image imagenRedimensionada = image.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon iconoPeque = new ImageIcon(imagenRedimensionada);

        Image dimensiones = image1.getScaledInstance(32,32, image1.SCALE_SMOOTH);
        ImageIcon iconPeque1 = new ImageIcon(dimensiones);

        usuarioIcono.setIcon(iconoPeque);
        contraseñaa.setIcon(iconPeque1);

        administradorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textField1.getText();
                String contrasenia = String.valueOf(passwordField1.getPassword());

                if (usuario.isEmpty() || contrasenia.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos");
                    return;
                }

                if (usuario.equals("admin") && contrasenia.equals("admin123")) {
                    JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                    new Admin();  // Abre la ventana Admin.java
                    dispose();    // Cierra la ventana Login
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales inválidas para administrador");
                }
            }
        });

        recepcionistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textField1.getText();
                String contrasenia = String.valueOf(passwordField1.getPassword());

                if (usuario.isEmpty() || contrasenia.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos");
                    return;
                }

                if (usuario.equals("recepcion") && contrasenia.equals("recep123")) {
                    JOptionPane.showMessageDialog(null, "Bienvenido Recepcionista");
                    new Recepcionista();  // Abre la ventana Recepcionista.java
                    dispose();            // Cierra la ventana Login
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales inválidas para recepcionista");
                }
            }
        });
    }
}