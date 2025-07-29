import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField textField1;
    private JButton recepcionistaButton;
    private JButton administradorButton;
    private JPanel principal;
    private JPasswordField passwordField1;

    public Login() {
        setTitle("Login de Usuarios");
        setContentPane(principal);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Validación para ADMIN
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
        // Validación para RECEPCIONISTA

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
