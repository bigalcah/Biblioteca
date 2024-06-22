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

public class MenuLibros extends MenuBase {
    protected SQL admin;

    public MenuLibros(SQL admin) {
        this.admin = admin;
        setTitle("Gestión de Libros");
    }

    @Override
    protected void agregarComponentes(String accion){
        contentPane.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        switch (accion) {
            case "eliminar":
                gbc.gridx = 0;
                gbc.gridy = 0;
                this.labelCampo1.setText("Id libro a eliminar");
                contentPane.add(labelCampo1, gbc);

                gbc.gridx = 1;
                contentPane.add(campo1, gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                contentPane.add(btnEnviar, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 3;
                labelInstruccion.setText("Instrucciones: Ingrese el id del libro que desea eliminar");
                contentPane.add(labelInstruccion, gbc);
                break;

            case "mostrar":
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
                break;

            case "modificar":
                gbc.gridx = 0;
                gbc.gridy = 0;
                labelCampo1.setText("id");
                contentPane.add(labelCampo1, gbc);

                gbc.gridx = 1;
                contentPane.add(campo1, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                labelCampo2.setText("Stock");
                contentPane.add(labelCampo2, gbc);

                gbc.gridx = 1;
                contentPane.add(campo2, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                labelCampo3.setText("Precio");
                contentPane.add(labelCampo3, gbc);

                gbc.gridx = 1;
                contentPane.add(campo3, gbc);

                gbc.gridx = 1;
                gbc.gridy = 3;
                contentPane.add(btnEnviar, gbc);

                gbc.gridx = 0;
                gbc.gridy = 4;
                gbc.gridwidth = 3;
                contentPane.add(labelInstruccion, gbc);
                break;

            default:
                gbc.gridx = 0;
                gbc.gridy = 0;
                labelCampo1.setText("Titulo");
                contentPane.add(labelCampo1, gbc);

                gbc.gridx = 1;
                contentPane.add(campo1, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                labelCampo2.setText("Id Autor");
                contentPane.add(labelCampo2, gbc);

                gbc.gridx = 1;
                contentPane.add(campo2, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                labelCampo3.setText("Fecha de publicacion");
                contentPane.add(labelCampo3, gbc);

                gbc.gridx = 1;
                contentPane.add(campo3, gbc);

                gbc.gridx = 0;
                gbc.gridy = 3;
                labelCampo4.setText("precio");
                contentPane.add(labelCampo4, gbc);

                gbc.gridx = 1;
                contentPane.add(campo4, gbc);

                gbc.gridx = 0;
                gbc.gridy = 4;
                labelCampo5.setText("Stock");
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
                break;
        }
        contentPane.revalidate();
        contentPane.repaint();
    }

    protected void mostrarLibros(Consulta consulta){
        try{
            ResultSet rs = consulta.mostrarLibros();
            DefaultTableModel modelo = obtenerDatosRS(rs);
            tabla.setModel(modelo);
            rs.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == agregarItem) {
            this.estadoActual = "agregar";
            agregarComponentes("agregar");
            this.labelInstruccion.setText("Instrucciones: Rellene los campos para registrar");
        }

        if (e.getSource() == mostrarItem) {
            this.estadoActual = "mostrar";
            agregarComponentes("mostrar");
            mostrarLibros(new Consulta(admin));
        }
        if (e.getSource() == modificarItem) {
            this.estadoActual = "modificar";
            agregarComponentes("modificar");
            labelInstruccion.setText("Instrucciones: Rellene los campos e id para modificar");

        }
        if (e.getSource() == eliminarItem) {
            this.estadoActual = "eliminar";
            agregarComponentes("eliminar");

        }if (e.getSource() == btnEnviar) {
            Consulta consulta = new Consulta(admin);
            String estadoActual = getEstadoActual();
            if (estadoActual.equals("agregar")) {
                try{
                    consulta.registrarLibro(campo1.getText(), Integer.parseInt(campo2.getText()),
                            Date.valueOf(campo3.getText()), Float.parseFloat(campo4.getText()),
                            Integer.parseInt(campo5.getText()));
                    JOptionPane.showMessageDialog(this, "Libro agregado exitosamente");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }if (estadoActual.equals("eliminar")){
                try{
                    consulta.eliminarLibro(Integer.parseInt(this.campo1.getText()));
                    JOptionPane.showMessageDialog(this, "Libro eliminado exitosamente");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }if (estadoActual.equals("modificar")){
                try {
                    consulta.modificarLibro(Integer.parseInt(campo1.getText()), Integer.parseInt(campo2.getText()),
                            Float.parseFloat(campo3.getText()));
                    JOptionPane.showMessageDialog(this, "Libro modificado con exito");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
