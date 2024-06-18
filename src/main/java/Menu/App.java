package Menu;

import BBDD.SQL;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            SQL admin = new SQL();
            MenuPrincipal menuPrincipal = new MenuPrincipal(admin);
            menuPrincipal.setVisible(true);
        });
    }
}
