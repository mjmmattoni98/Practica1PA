package empresa.telefonia;


import excepciones.NIFException;
import excepciones.TarifaException;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClienteParticular extends  Cliente {
    private String apellidos;

    public ClienteParticular(String nombre, String nif, Direccion direccion, String correoElectronico, Tarifa tarifa, String apellidos) throws TarifaException, NIFException {
        super(nombre, nif, direccion, correoElectronico, tarifa);
        this.apellidos = apellidos;
    }

    public String getApellidos(){
        return apellidos;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-Nombre: " + super.getNombre() + "\n");
        sb.append("-Apellidos: " + apellidos + "\n");
        sb.append("-NIF: " + super.getNif() + "\n");
        sb.append("-Direción:\n" + super.getDireccion() + "\n");
        sb.append("-Correo electrónico: " + super.getCorreoElectronico() + "\n");
        sb.append("-Fecha de alta: " + super.getFecha() + "\n");
        sb.append("-Tarifa: " + super.getTarifa() + "\n");
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
