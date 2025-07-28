import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JComboBox hbitaciones;
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

            }
        });
        limpiarCamposButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
