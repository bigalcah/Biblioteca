package Menu;

import BBDD.Consulta;
import BBDD.SQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Consulta consulta = new Consulta(admin);

        if (e.getSource() == agregarItem) {
            agregarComponentes("agregar");
            this.estadoActual = "agregar";
            labelInstruccion.setText("Ingrese todos los valores solicitados");

        } else if (e.getSource() == eliminarItem) {
            agregarComponentes("eliminar");
            this.estadoActual = "eliminar";
            labelInstruccion.setText("Ingrese el Rut del cliente que desea eliminar");

        } else if (e.getSource() == modificarItem) {
            agregarComponentes("modificar");
            this.estadoActual = "modificar";
            labelInstruccion.setText("Ingrese el RUT del cliente que desea modificar, además de los siguientes parámetros");

        } else if (e.getSource() == mostrarItem) {
            agregarComponentes("mostrar");
            this.estadoActual = "mostrar";
            mostrarClientes(consulta);

        } else if (e.getSource() == this.btnEnviar) {
            String accion = this.getEstadoActual();
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
                JOptionPane.showMessageDialog(this, "Error al ejecutar la operación", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            System.out.println("boton presionado");
            limpiarCampos();
        }
    }

    protected void mostrarClientes(Consulta consulta) {
        try (Connection conn = new SQL().getConnection()) {
            ResultSet rs = consulta.mostrarClientes(conn);
            DefaultTableModel model = obtenerDatosRS(rs);
            tabla.setModel(model);
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al mostrar las clientes", "Error", JOptionPane.ERROR_MESSAGE);
        }
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


}
