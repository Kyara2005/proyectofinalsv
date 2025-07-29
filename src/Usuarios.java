    import javax.swing.*;
    
    public class Usuarios extends JFrame{
        private JTextField textField1;
        private JButton buscarButton;
        private JButton regresarButton;
        private JPanel principal;

        public Usuarios() {
            setTitle("Gestión de Usuarios");
            setSize(300, 200);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
            setContentPane(principal);
        }
    }
