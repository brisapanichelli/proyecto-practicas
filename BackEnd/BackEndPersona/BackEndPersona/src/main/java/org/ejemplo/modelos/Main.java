package ClasesLogin;

import org.ejemplo.modelos.Administrador;
import org.ejemplo.modelos.Vendedor;
import org.ejemplo.modelos.Cliente;

public class Main {
    public static void main(String[] args) {
        Administrador admin = new Administrador();
        admin.getId();

        Vendedor vend = new Vendedor();
        vend.getId();

        Cliente clie = new Cliente();
        clie.getId();
    }
}