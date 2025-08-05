import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Ventana encargada del menu de acciones permitidas para el recepcionista
 */
public class Recepcionista extends JFrame {
    private JButton checkInButton;
    private JButton checkOutButton;
    private JButton cerrarSesionButton;
    private JPanel RecepPrincipal;
    private JButton inrformacionDeHuespedesAlojadosButton;

    public Recepcionista() {
        setTitle("Panel de Recepcionista");
        setContentPane(RecepPrincipal);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        ThemeManager.aplicarTema(this);


        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckIn();
                dispose();
            }
        });

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reserva();
                dispose();
            }
        });

        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });
        inrformacionDeHuespedesAlojadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new alojados();
                dispose();
            }
        });
    }
}
