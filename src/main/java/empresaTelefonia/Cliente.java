package empresaTelefonia;

public abstract class Cliente {
    private String nombre;
    private String nif;
    private Direccion direccion;
    private String correoElectronico;
    //private Fecha fechaDeAlta;
    private Tarifa tarifa;

    public Cliente () {
        super();
    }

    public Cliente (String nombre, String nif, Direccion direccion, String correoElectronico, /*Fecha fechaDeAlta, */Tarifa tarifa){
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        //this.fechaDeAlta = fechaDeAlta;
        this.tarifa = tarifa;
    }



}
