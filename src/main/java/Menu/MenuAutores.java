package Menu;

import javax.swing.*;

public class MenuAutores extends MenuBase {

    public MenuAutores() {
        setTitle("Gestión de Autores");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuAutores frame = new MenuAutores();
            frame.setVisible(true);
        });
    }
}

