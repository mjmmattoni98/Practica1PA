package controlador;

import empresa.telefonia.Direccion;
import empresa.telefonia.Usuario;
import modelo.CambioModelo;
import modelo.ImplementacionModelo;

public class ImplementacionControlador implements Controlador {
    private CambioModelo modelo;

    public ImplementacionControlador() {}

    @Override
    public void addClienteParticular(Usuario usuario, Direccion direccion, String apellidos) {
        modelo.addClienteParticular(usuario, direccion, apellidos);
    }
    @Override
    public void addClienteEmpresa(Usuario usuario, Direccion direccion) {
        modelo.addClienteEmpresa(usuario, direccion);
    }

    @Override
    public void delCuenta(String nif) {
        modelo.delCuenta(nif);
    }

    @Override
    public void addLlamada(String nif, int numero, double duracion) {
        modelo.addLlamada(nif,numero,duracion);
    }

    @Override
    public void modTarifaDomingosGratis(String nif){
        modelo.modTarifaDomingosGratis(nif);
    }

    @Override
    public void modTarifaTardesReducidas(String nif){
        modelo.modTarifaTardesReducidas(nif);
    }

    @Override
    public void emitirFactura(String nif){ modelo.emitirFactura(nif);
    }

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

}