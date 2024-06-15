package Menu;

import javax.swing.*;

public class MenuLibros extends MenuBase {

    public MenuLibros() {
        setTitle("Gestión de Libros");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuLibros frame = new MenuLibros();
            frame.setVisible(true);
        });
    }
}
