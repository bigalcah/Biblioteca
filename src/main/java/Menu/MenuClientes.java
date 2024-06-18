package Menu;

import BBDD.Consulta;
import BBDD.SQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MenuClientes extends MenuBase {
    public SQL admin;

    public MenuClientes(SQL admin) {
        this.admin = admin;
        setTitle("Gestión de Clientes");
        labelCampo1.setText("RUT");
        labelCampo2.setText("Nombre");
        labelCampo3.setText("Direccion");
        labelCampo4.setText("Telefono");
        labelCampo5.setText("Email");

        initUI();
    }

    private void initUI() {
        agregarItem.addActionListener(this);
        eliminarItem.addActionListener(this);
        modificarItem.addActionListener(this);
        mostrarItem.addActionListener(this);
        btnEnviar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Consulta consulta = new Consulta(admin);

        if (e.getSource() == agregarItem) {
            agregarComponentes("agregar");
            labelInstruccion.setText("Ingrese todos los valores solicitados");
        } else if (e.getSource() == eliminarItem) {
            agregarComponentes("eliminar");
            labelInstruccion.setText("Ingrese el Rut del cliente que desea eliminar");
        } else if (e.getSource() == modificarItem) {
            agregarComponentes("modificar");
            labelInstruccion.setText("Ingrese el RUT del cliente que desea modificar, además de los siguientes parámetros");
        } else if (e.getSource() == mostrarItem) {
            agregarComponentes("mostrar");
            mostrarClientes(consulta);
        } else if (e.getSource() == btnEnviar) {
            String accion = obtenerAccionActual();
            try {
                if ("agregar".equals(accion)) {
                    consulta.registrarCliente(
                            campo1.getText(),
                            campo2.getText(),
                            campo3.getText(),
                            campo4.getText(),
                            campo5.getText()
                    );
                    JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente");
                } else if ("eliminar".equals(accion)) {
                    consulta.eliminarCliente(campo1.getText());
                    JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente");
                } else if ("modificar".equals(accion)) {
                    consulta.ActualizarCliente(
                            campo1.getText(),
                            campo2.getText(),
                            campo3.getText(),
                            campo4.getText(),
                            campo5.getText()
                    );
                    JOptionPane.showMessageDialog(this, "Cliente modificado exitosamente");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al ejecutar la operación", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String obtenerAccionActual() {
        if (agregarItem.isArmed()) return "agregar";
        if (eliminarItem.isArmed()) return "eliminar";
        if (modificarItem.isArmed()) return "modificar";
        if (mostrarItem.isArmed()) return "mostrar";
        return "inicial";
    }

    private void mostrarClientes(Consulta consulta) {
        try (Connection conn = new SQL().getConnection()) {
            ResultSet rs = consulta.mostrarClientes(conn);
            DefaultTableModel model = obtenerDatosClientes(rs);
            tabla.setModel(model);
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private DefaultTableModel obtenerDatosClientes(ResultSet rs) throws SQLException {
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

    @Override
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
        } else if ("mostrar".equals(accion)) {
            // Configurar la tabla y el scroll pane
            tabla = new JTable();
            tabla.setFillsViewportHeight(true);
            scrollPane = new JScrollPane(tabla);
            scrollPane.setPreferredSize(new Dimension(400, 200));

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
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

            gbc.gridy = 6;
            gbc.gridx = 0;
            gbc.gridwidth = 3;
            contentPane.add(labelInstruccion, gbc);
        }

        contentPane.revalidate();
        contentPane.repaint();
    }

    private void limpiarCampos() {
        campo1.setText("");
        campo2.setText("");
        campo3.setText("");
        campo4.setText("");
        campo5.setText("");
    }
}
