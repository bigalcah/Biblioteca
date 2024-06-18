package Menu;

import BBDD.SQL;

import static org.junit.jupiter.api.Assertions.*;

class MenuClientesTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void obtenerAccionActual() {
        MenuClientes menuClientes = new MenuClientes(new SQL());
        menuClientes.setVisible(true);

    }
}