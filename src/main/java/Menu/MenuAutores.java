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

public class MenuAutores extends MenuBase {
    private SQL admin;

    public MenuAutores(SQL admin) {
        this.admin = admin;
        setTitle("Gestión de Autores");
        this.labelCampo1.setText("Nombre de Autor");
        this.labelCampo2.setText("pais");
        this.labelCampo3.setText("Fecha de nacimiento");

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.agregarItem){
            this.estadoActual = "agregar";
            agregarComponentes("agregar");
            this.labelInstruccion.setText("Instrucciones: Ingrese los campos solicitados");

        }if(e.getSource() == this.mostrarItem){
            this.estadoActual = "mostrar";
            agregarComponentes("mostrar");
            mostrarAutores(new Consulta(admin));

        }if(e.getSource() == this.modificarItem){
            this.estadoActual = "modificar";
            agregarComponentes("modificar");
            this.labelInstruccion.setText("Instrucciones: Ingrese el id del Autor que desea" +
                    "modificar, seguido de los demas campos");

        }if(e.getSource() == this.eliminarItem){
            this.estadoActual = "eliminar";
            agregarComponentes("eliminar");
            this.labelCampo1.setText("id");
            this.labelInstruccion.setText("Intrucciones: Ingrese el id del autor que desea" +
                    "eliminar");

        }if (e.getSource() == this.btnEnviar){
            Consulta consulta = new Consulta(admin);
            String estadoActual = getEstadoActual();

            if(estadoActual == "agregar"){
                try {
                    consulta.registrarAutor(this.campo1.getText(),
                            this.campo2.getText(), Date.valueOf(this.campo3.getText()));
                    JOptionPane.showMessageDialog(null, "Autor agregado correctamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }if(estadoActual == "eliminar"){
                try {
                    consulta.eliminarAutor(Integer.parseInt(this.campo1.getText()));
                    JOptionPane.showMessageDialog(null, "Autor eliminado correctamente");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }if (estadoActual == "modificar"){
                try {
                    consulta.modificarAutor(Integer.parseInt(campo1.getText()), campo2.getText(),
                            campo3.getText(), Date.valueOf(campo4.getText()));
                    JOptionPane.showMessageDialog(null, "Autor Modificado correctamente");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(this,ex.getMessage() , "Error", JOptionPane.ERROR_MESSAGE);
                }
                limpiarCampos();
            }
        }
    }

    private void mostrarAutores(Consulta consulta){
        try{
            ResultSet rs = consulta.mostrarAutores(admin.getConnection());
            DefaultTableModel model = obtenerDatosRS(rs);
            tabla.setModel(model);
            rs.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error al mostrar los datos", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
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
        }else if("modificar".equals(accion)){
            gbc.gridx = 0;
            gbc.gridy = 0;
            labelCampo1.setText("id");
            contentPane.add(labelCampo1, gbc);

            gbc.gridx = 1;
            contentPane.add(campo1, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            labelCampo2.setText("Nombre");
            contentPane.add(labelCampo2, gbc);

            gbc.gridx = 1;
            contentPane.add(campo2, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            labelCampo3.setText("País");
            contentPane.add(labelCampo3, gbc);

            gbc.gridx = 1;
            contentPane.add(campo3, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            labelCampo4.setText("Fecha de Nacimiento");
            contentPane.add(labelCampo4, gbc);

            gbc.gridx = 1;
            contentPane.add(campo4, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 3;
            contentPane.add(labelInstruccion, gbc);
        }else {
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

