package Menu;

import BBDD.Consulta;
import BBDD.SQL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
    protected DefaultTableModel model;
    protected JLabel labelCampo1;
    protected JLabel labelCampo2;
    protected JLabel labelCampo3;
    protected JLabel labelCampo4;
    protected JLabel labelCampo5;
    protected JLabel labelInstruccion;
    protected String estadoActual = "inicial";

    public MenuBase() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
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

        labelCampo1 = new JLabel("Campo 1:");
        labelCampo2 = new JLabel("Campo 2:");
        labelCampo3 = new JLabel("Campo 3:");
        labelCampo4 = new JLabel("Campo 4:");
        labelCampo5 = new JLabel("Campo 5:");
        labelInstruccion = new JLabel("Instruccion:");

        btnEnviar = new JButton("Enviar");
        btnEnviar.addActionListener(this);

        tabla = new JTable();
        scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(780, 400));

        agregarComponentes("inicial");
    }

    protected String getEstadoActual(){ return this.estadoActual;}

    protected void limpiarCampos() {
        campo1.setText("");
        campo2.setText("");
        campo3.setText("");
        campo4.setText("");
        campo5.setText("");
    }



    protected DefaultTableModel obtenerDatosRS(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnas = metaData.getColumnCount();
        String[] titulos = new String[columnas];

        for (int i = 1; i <= columnas; i++) {
            titulos[i - 1] = metaData.getColumnName(i);
        }
        DefaultTableModel model = new DefaultTableModel(titulos, 0);
        while (rs.next()) {
            Object[] fila = new Object[columnas];
            for (int i = 1; i <= columnas; i++) {
                fila[i - 1] = rs.getObject(i);
            }
            model.addRow(fila);
        }
        return model;
    }

    protected void agregarComponentes(String accion) {
        contentPane.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if ("eliminar".equals(accion)) {
            gbc.gridx = 0;
            gbc.gridy = 0;
            contentPane.add(labelCampo1, gbc);

            gbc.gridx = 1;
            contentPane.add(campo1, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            contentPane.add(btnEnviar, gbc);

            gbc.gridy = 2;
            gbc.gridx = 0;
            contentPane.add(labelInstruccion, gbc);

        } else if ("mostrar".equals(accion)) {
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            contentPane.add(scrollPane, gbc);
        } else {
            gbc.gridx = 0;
            gbc.gridy = 0;
            contentPane.add(labelCampo1, gbc);

            gbc.gridx = 1;
            contentPane.add(campo1, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            contentPane.add(labelCampo2, gbc);

            gbc.gridx = 1;
            contentPane.add(campo2, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            contentPane.add(labelCampo3, gbc);

            gbc.gridx = 1;
            contentPane.add(campo3, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            contentPane.add(labelCampo4, gbc);

            gbc.gridx = 1;
            contentPane.add(campo4, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            contentPane.add(labelCampo5, gbc);

            gbc.gridx = 1;
            contentPane.add(campo5, gbc);

            gbc.gridx = 1;
            gbc.gridy = 5;
            contentPane.add(btnEnviar, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            contentPane.add(labelInstruccion, gbc);
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
        } else if (e.getSource() == mostrarItem) {
            agregarComponentes("mostrar");
        }
    }
}