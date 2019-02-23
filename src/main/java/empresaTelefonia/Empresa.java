package empresaTelefonia;

public class Empresa extends Cliente {

    public Empresa (String nombre, String nif, Direccion direccion, String correoElectronico, /*Fecha fechaDeAlta, */Tarifa tarifa){
        super(nombre, nif, direccion, correoElectronico, /*fechaDeAlta, */tarifa);
    }

}
