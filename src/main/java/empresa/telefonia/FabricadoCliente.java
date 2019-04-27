package empresa.telefonia;

import excepciones.TarifaException;

public class FabricadoCliente implements FabricaCliente {
    private String nif;
    private String nombre;
    private int cp;
    private String provincia;
    private String poblacion;
    private String correoElectronico;
    private double tarifa;
    private String apellidos;

    public FabricadoCliente(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa, String apellidos){
        this.nif = nif;
        this.nombre = nombre;
        this.cp = cp;
        this.provincia = provincia;
        this.poblacion = poblacion;
        this.correoElectronico = correoElectronico;
        this.tarifa = tarifa;
        this.apellidos = apellidos;
    }

    public Cliente getCliente(TipoCliente tipo) {
        Cliente cliente= Cliente.NULL_CLIENT;
        switch (tipo) {
            case PARTICULAR:
                cliente = new ClienteParticular(nombre,nif, new Direccion(cp,provincia,poblacion), correoElectronico, new TarifaBasica(tarifa), apellidos);
                break;

            case EMPRESA:
                cliente = new ClienteEmpresa(nombre,nif,new Direccion(cp,provincia,poblacion), correoElectronico, new TarifaBasica(tarifa));
                break;
        }
        return cliente;
    }


}
