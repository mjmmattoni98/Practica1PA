package empresa.telefonia;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClienteEmpresa extends Cliente {

    public ClienteEmpresa(Usuario usuario, Direccion direccion, Tarifa tarifa){
        super(usuario, direccion, tarifa);
    }

    public String toStringHtml(){
        StringBuilder sb = new StringBuilder();
        sb.append("-Nombre: " + super.getNombre() + "<br>");
        sb.append("-NIF: " + super.getNif() + "<br>");
        sb.append("-Dirección:<br>" + super.getDireccion().toStringHtml() + "<br>");
        sb.append("-Correo electrónico: " + super.getCorreoElectronico() + "<br>");
        sb.append("-Fecha de alta: " + super.getFecha() + "<br>");
        sb.append("-Tarifas aplicadas: " + super.getTarifa().description() + "<br>");
        sb.append("-Listado de llamadas:<br>");
        Map<Periodo, List<Llamada>> misLlamadas = super.getLlamadas();
        Iterator<Periodo> iterLlamadas = misLlamadas.keySet().iterator();
        while (iterLlamadas.hasNext())
            for (Llamada llamada : misLlamadas.get(iterLlamadas.next()))
                sb.append("\t-" + llamada.toStringHTML());
        sb.append("-Listado de facturas: ");
        Collection<Factura> colFacturas = super.getFacturas().values();
        for(Factura factura : colFacturas)
            sb.append(factura.toStringHtml());
        return sb.toString();
    }
}
