package Menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MenuBase extends JFrame implements ActionListener {
    protected JPanel contentPane;
    protected JMenu menu;
    protected JMenuBar menuBar;
    protected JMenuItem agregarItem;
    protected JMenuItem eliminarItem;
    protected JMenuItem modificarItem, mostrarItem;
    protected JTextField campo1;
    protected JTextField campo2;
    protected JTextField campo3;
    protected JTextField campo4;
    protected JTextField campo5;
    protected JButton btnEnviar;
    protected JTable tabla;
    protected JScrollPane scrollPane;
    DefaultTableModel model;

    public MenuBase() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        menuBar = new JMenuBar();
        menu = new JMenu("Acciones");
        menuBar.add(menu);
        setJMenuBar(menuBar);

        agregarItem = new JMenuItem("Agregar");
        agregarItem.addActionListener(this);
        menu.add(agregarItem);

        eliminarItem = new JMenuItem("Eliminar");
        eliminarItem.addActionListener(this);
        menu.add(eliminarItem);

        modificarItem = new JMenuItem("Modificar");
        modificarItem.addActionListener(this);
        menu.add(modificarItem);

        mostrarItem = new JMenuItem("Mostrar");
        mostrarItem.addActionListener(this);
        menu.add(mostrarItem);

        campo1 = new JTextField(20);
        campo2 = new JTextField(20);
        campo3 = new JTextField(20);
        campo4 = new JTextField(20);
        campo5 = new JTextField(20);

        btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(this);

        //agregar modelo y tabla.

        agregarComponentes("inicial");
    }

    protected void agregarComponentes(String accion) {
        contentPane.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if ("eliminar".equals(accion)) {
            gbc.gridx = 0;
            gbc.gridy = 0;
            contentPane.add(new JLabel("Campo 1:"), gbc);

            gbc.gridx = 1;
            contentPane.add(campo1, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            contentPane.add(btnEnviar, gbc);
        } else if("mostrar".equals(accion)){

        }else {
            gbc.gridx = 0;
            gbc.gridy = 0;
            contentPane.add(new JLabel("Campo 1:"), gbc);

            gbc.gridx = 1;
            contentPane.add(campo1, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            contentPane.add(new JLabel("Campo 2:"), gbc);

            gbc.gridx = 1;
            contentPane.add(campo2, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            contentPane.add(new JLabel("Campo 3:"), gbc);

            gbc.gridx = 1;
            contentPane.add(campo3, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            contentPane.add(new JLabel("Campo 4:"), gbc);

            gbc.gridx = 1;
            contentPane.add(campo4, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            contentPane.add(new JLabel("Campo 5:"), gbc);

            gbc.gridx = 1;
            contentPane.add(campo5, gbc);

            gbc.gridx = 1;
            gbc.gridy = 5;
            contentPane.add(btnEnviar, gbc);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == agregarItem) {
            agregarComponentes("agregar");
        } else if (e.getSource() == eliminarItem) {
            agregarComponentes("eliminar");
        } else if (e.getSource() == modificarItem) {
            agregarComponentes("modificar");
        } else if (e.getSource() == btnEnviar) {
            JOptionPane.showMessageDialog(this, "Acción realizada");
        }
    }
}
