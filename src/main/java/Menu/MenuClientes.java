package Menu;

import javax.swing.*;

public class MenuClientes extends MenuBase {

    public MenuClientes() {
        setTitle("Gestión de Clientes");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuClientes frame = new MenuClientes();
            frame.setVisible(true);
        });
    }
}
