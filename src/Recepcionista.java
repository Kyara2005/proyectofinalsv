import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Recepcionista extends JFrame {
    private JButton checkInButton;
    private JButton checkOutButton;
    private JButton cerrarSesionButton;
    private JPanel RecepPrincipal;

    public Recepcionista() {
        setTitle("Panel de Recepcionista");
        setContentPane(RecepPrincipal);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

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
    }
}
