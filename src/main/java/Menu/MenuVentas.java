package Menu;

import BBDD.Consulta;
import BBDD.SQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuVentas extends MenuBase{
    protected SQL admin;
    public MenuVentas(SQL admin) {
        this.admin = admin;
        setTitle("Ventas");
        menu.remove(eliminarItem);
        menu.remove(modificarItem);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == agregarItem){
            estadoActual = "agregar";
            agregarComponentes("agregar");
        }
        if(e.getSource() == mostrarItem){
            agregarComponentes("mostrar");
            try{
            mostrarVentas(new Consulta(admin));
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        if(e.getSource() == btnEnviar){
            Consulta consulta = new Consulta(admin);
            String estado = getEstadoActual();
            if(estado.equals("agregar")){
                try{
                    consulta.realizarVenta(Integer.parseInt(campo1.getText()),
                    String.valueOf(campo2.getText()),
                    Date.valueOf(campo3.getText()), Integer.parseInt(campo4.getText()));
                    JOptionPane.showMessageDialog(null, "Se agrego con exito");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "Error al agregar con exito");
                    ex.printStackTrace();
                 }
                limpiarCampos();
            }
        }
    }

    @Override
    public void agregarComponentes(String accion){
        contentPane.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if(accion.equals("mostrar")){
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
        }else{
            gbc.gridx = 0;
            gbc.gridy = 0;
            labelCampo1.setText("id del Libro");
            contentPane.add(labelCampo1, gbc);

            gbc.gridx = 1;
            contentPane.add(campo1, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            labelCampo2.setText("rut del cliente");
            contentPane.add(labelCampo2, gbc);

            gbc.gridx = 1;
            contentPane.add(campo2, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            labelCampo3.setText("Fecha de Venta");
            contentPane.add(labelCampo3, gbc);

            gbc.gridx = 1;
            contentPane.add(campo3, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            labelCampo4.setText("Cantidad");
            contentPane.add(labelCampo4, gbc);

            gbc.gridx = 1;
            contentPane.add(campo4, gbc);

            gbc.gridy = 4;
            contentPane.add(btnEnviar, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            labelInstruccion.setText("Instrucciones: ingrese los datos solicitados para registrar la venta");
            contentPane.add(labelInstruccion, gbc);

        }
        contentPane.revalidate();
        contentPane.repaint();
    }

    protected void mostrarVentas(Consulta consulta) throws SQLException {
        ResultSet rs = consulta.mostrarVentas();
        DefaultTableModel model = obtenerDatosRS(rs);
        tabla.setModel(model);
        rs.close();
    }
}
