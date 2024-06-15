package Menu;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MenuPrincipal extends JFrame implements ActionListener{
    private JButton btnClientes = new JButton("Clientes");
    private JButton btnLibros = new JButton("Libros");
    private JButton btnAutores = new JButton("Autores");
    private JButton btnVentas = new JButton("Ventas");
    private JPanel panel;
    private JLabel mensajeInicial= new JLabel("Bienvenido al Sistema, Usted" +
            "puede realizar las siguentes acciones:");

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel= new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setBackground(Color.LIGHT_GRAY);

        btnAutores.addActionListener(this);
        btnClientes.addActionListener(this);
        btnLibros.addActionListener(this);
        btnVentas.addActionListener(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        //Configuración para el mensaje
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Hace que el mensaje ocupe todas las columnas
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(mensajeInicial, gbc);

        // Configuración para los botones
        gbc.gridwidth = 1; // Resetea gridwidth a 1 para los botones
        gbc.gridy = 1;
        gbc.gridx = 1;
        panel.add(btnClientes, gbc);

        gbc.gridy = 1;
        gbc.gridx = 2;
        panel.add(btnLibros, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        panel.add(btnVentas, gbc);

        gbc.gridy = 2;
        gbc.gridx = 2;
        panel.add(btnAutores, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == btnClientes){
            MenuClientes menuClientes = new MenuClientes();
            menuClientes.setVisible(true);

        }else if(event.getSource() == btnLibros){
            MenuLibros menuLibros = new MenuLibros();
            menuLibros.setVisible(true);

        }else if(event.getSource() == btnAutores){
            MenuAutores menuAutores = new MenuAutores();
            menuAutores.setVisible(true);
        }else if(event.getSource() == btnVentas){
            //Me

        }

    }
}
