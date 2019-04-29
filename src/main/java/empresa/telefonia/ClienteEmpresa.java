package empresa.telefonia;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClienteEmpresa extends Cliente {

    public ClienteEmpresa(String nombre, String nif, Direccion direccion, String correoElectronico, Tarifa tarifa){
        super(nombre, nif, direccion, correoElectronico, tarifa);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-Nombre: " + super.getNombre() + "\n");
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
