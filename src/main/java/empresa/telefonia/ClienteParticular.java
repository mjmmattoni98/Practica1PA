package empresa.telefonia;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClienteParticular extends  Cliente {
    private String apellidos;

    public ClienteParticular(){
        super();
    }

    public ClienteParticular(Usuario usuario, Direccion direccion, Tarifa tarifa, String apellidos){
        super(usuario, direccion, tarifa);
        this.apellidos = apellidos;
    }

    public String getApellidos(){
        return apellidos;
    }

    //TODO rehacer el metodo toString.
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-Nombre: " + super.getNombre() + "\n");
        sb.append("-Apellidos: " + apellidos + "\n");
        sb.append("-NIF: " + super.getNif() + "\n");
        sb.append("-Direción:\n" + super.getDireccion() + "\n");
        sb.append("-Correo electrónico: " + super.getCorreoElectronico() + "\n");
        sb.append("-Fecha de alta: " + super.getFecha() + "\n");
        sb.append("-Tarifas aplicadas: " + super.getTarifa().description() + "\n");
        sb.append("-Listado de llamadas:\n");
        Map<Periodo, List<Llamada>> misLlamadas = super.getLlamadas();
        Iterator<Periodo> iterLlamadas = misLlamadas.keySet().iterator();
        while (iterLlamadas.hasNext())
            for (Llamada llamada : misLlamadas.get(iterLlamadas.next()))
                sb.append("\t-" + llamada);
        sb.append("-Listado de facturas:\n");
        Collection<Factura> colFacturas = super.getFacturas().values();
        for(Factura factura : colFacturas)
            sb.append(factura);
        return sb.toString();
    }
}
