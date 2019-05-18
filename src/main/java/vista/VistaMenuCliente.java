package vista;

import controlador.Controlador;
import controlador.ImplementacionControlador;
import empresa.telefonia.Direccion;
import empresa.telefonia.Periodo;
import empresa.telefonia.Usuario;
import modelo.CambioModelo;
import modelo.ImplementacionModelo;
import modelo.InterrogaModelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

public class VistaMenuCliente {
    private InterrogaModelo modelo = new ImplementacionModelo();
    private Controlador controlador = new ImplementacionControlador();

    public void ejecuta() {
        JFrame ventana = new JFrame("Vista cliente.");

        Container container = ventana.getContentPane();

//        container.setLayout(new GridLayout(17, 1));

//        container.add(new JButton("Boton de cliente"));
//        JLabel opcionLabel = new JLabel("Que acción desea realizar?");

        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.add("Crear cliente", new CrearCliente());
        pestanyas.add("Borrar Cliente", new BorrarCliente());
        pestanyas.add("Cambiar tarifa", new CambiarTarifa());
        pestanyas.add("Mostrar clientes", new MostrarClientes());
        pestanyas.add("Mostrar datos clientes", new MostrarDatosCliente());
        pestanyas.add("Mostrar clientes entre fechas", new MostrarClientesEntreFechas());

//        JScrollPane jScrollPane = new JScrollPane(pestanyas);
//        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        container.add(jScrollPane);
        container.add(pestanyas);
//        ventana.setSize(500, 500);
        ventana.pack();
        ventana.setVisible(true);

        ventana.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Hasta luego desde cliente!!!!");
//                System.exit(0);
            }
        });
    }

    private class CrearCliente extends JPanel {
        public CrearCliente() {
            this.ejecuta();
        }

        private void ejecuta() {
            final boolean[] particular = {false};
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            JCheckBox clienteParticular = new JCheckBox("Cliente particular");
            clienteParticular.setActionCommand("particular");
            ItemListener escuchador;
            clienteParticular.addItemListener(escuchador = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    JCheckBox boton = (JCheckBox) e.getItemSelectable();
                    String comando = boton.getActionCommand();
                    switch (comando) {
                        case "particular":
                            if (ItemEvent.SELECTED == e.getStateChange()) {
                                particular[0] = true;
                                System.out.println("Agregar cliente particular");
                            }
                            break;
                        case "empresa":
                            if (ItemEvent.SELECTED == e.getStateChange()) {
                                particular[0] = false;
                                System.out.println("Agregar cliente empresa");
                            }
                            break;
                    }
                }
            });
            JCheckBox clienteEmpresa = new JCheckBox("Cliente empresa");
            clienteEmpresa.setActionCommand("empresa");
            clienteEmpresa.addItemListener(escuchador);

            ButtonGroup grupoCliente = new ButtonGroup();
            grupoCliente.add(clienteEmpresa);
            grupoCliente.add(clienteParticular);

            //JLabel
            JLabel nifLabel = new JLabel("NIF: ");
//            nifLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel nombreLabel = new JLabel("Nombre: ");
//            nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel apellidosLabel = new JLabel("Apellidos: ");
//            apellidosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel cpLabel = new JLabel("CP: ");
//            cpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel provinciaLabel = new JLabel("Provincia: ");
//            provinciaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel poblacionLabel = new JLabel("Población: ");
//            poblacionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel emailLabel = new JLabel("Correo electrónico: ");
//            emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            //JTextField
            JTextField jtfNif = new JTextField(20);
            jtfNif.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField jtfNombre = new JTextField(20);
            jtfNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField jtfApellidos = new JTextField(20);
            jtfApellidos.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField jtfCp = new JTextField(20);
            jtfCp.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField jtfProvincia = new JTextField(20);
            jtfProvincia.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField jtfPoblacion = new JTextField(20);
            jtfPoblacion.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField jtfEmail = new JTextField(20);
            jtfEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton jbCrearCuenta = new JButton("Crear cuenta");
            jbCrearCuenta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Strings
                    String nombre = jtfNombre.getText();
                    String apellidos = jtfApellidos.getText();
                    String nif = jtfNif.getText();
                    int cp = Integer.parseInt(jtfCp.getText());
                    String provincia = jtfProvincia.getText();
                    String poblacion = jtfPoblacion.getText();
                    String email = jtfEmail.getText();
                    Usuario usuario = new Usuario(nombre, nif, email);
                    Direccion direccion = new Direccion(cp, provincia, poblacion);
                    if(particular[0])
                        controlador.addClienteParticular(usuario, direccion, apellidos);
                    else
                        controlador.addClienteEmpresa(usuario, direccion);
                    System.out.println("Cliente "+nombre+" "+apellidos+" "+"añadido con exito.");
                }
            });
            JButton jbClientesEjemplo = new JButton("Clientes de ejemplo");
            jbClientesEjemplo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Usuario usuario = new Usuario("pepe", "08312348z", "adoio@gmail.com");
                    Direccion direccion = new Direccion(12345, "Toledo", "Torrijos");
                    controlador.addClienteParticular(usuario, direccion, "iojo");
                    usuario = new Usuario("pepito", "02772348G", "adoil@gmil.com");
                    direccion = new Direccion(87975, "Albacete", "Albacete");
                    controlador.addClienteEmpresa(usuario, direccion);
                    usuario = new Usuario("pepe", "89712348Z", "adiii@gmil.com");
                    direccion = new Direccion(56445, "Valencia","Orxata");
                    controlador.addClienteParticular(usuario, direccion, "pojo");
                }
            });

            JScrollPane scroll = new JScrollPane(this);

            this.add(clienteParticular);
            this.add(clienteEmpresa);
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(nombreLabel);
            this.add(jtfNombre);
            this.add(apellidosLabel);
            this.add(jtfApellidos);
            this.add(cpLabel);
            this.add(jtfCp);
            this.add(provinciaLabel);
            this.add(jtfProvincia);
            this.add(poblacionLabel);
            this.add(jtfPoblacion);
            this.add(emailLabel);
            this.add(jtfEmail);
            this.add(jbCrearCuenta);
            this.add(jbClientesEjemplo);
        }
    }

    private class BorrarCliente extends JPanel {
        public BorrarCliente() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbBorrarCliente = new JButton("Borrar cliente");
            jbBorrarCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controlador.delCuenta(jtfNif.getText());
                    System.out.println("Cliente con nif "+ jtfNif.getText()+ " ha sido borrado con exito.");
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbBorrarCliente);
        }
    }

    private class CambiarTarifa extends JPanel {
        public CambiarTarifa() {
            this.ejecuta();
        }

        private void ejecuta(){
            int[] tarifa = {0};
            JCheckBox tardesReducidas = new JCheckBox("TardesReducidas");
            tardesReducidas.setActionCommand("TardesReducidas");
            ItemListener escuchador;
            tardesReducidas.addItemListener(escuchador = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    JCheckBox boton = (JCheckBox) e.getItemSelectable();
                    String comando = boton.getActionCommand();
                    switch (comando) {
                        case "DomingosGratis":
                            if (ItemEvent.SELECTED == e.getStateChange()) {
                                tarifa[0] = 1;
                                System.out.println("Cambiar tarifa a Domingos Gratis.");
                            }
                            break;
                        case "TardesReducidas":
                            if (ItemEvent.SELECTED == e.getStateChange()) {
                                tarifa[0] = 2;
                                System.out.println("Cambiar tarifa a Tardes Reducidas");
                            }
                            break;
                    }
                }
            });
            JCheckBox tarifaDomingoGratis = new JCheckBox("DomingosGratis");
            tarifaDomingoGratis.setActionCommand("DomingosGratis");
            tarifaDomingoGratis.addItemListener(escuchador);
            ButtonGroup grupoCliente = new ButtonGroup();
            grupoCliente.add(tarifaDomingoGratis);
            grupoCliente.add(tardesReducidas);
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbCambiarTarifa = new JButton("Cambiar Tarifa");
            jbCambiarTarifa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ( tarifa[0]==1){
                        controlador.modTarifaDomingosGratis(jtfNif.getText());
                        System.out.println("Tarifa Domingos gratis aplicada.");
                    }
                    else{
                        controlador.modTarifaTardesReducidas(jtfNif.getText());
                        System.out.println("Tarifa Tardes Reducidas aplicada.");
                    }
                }
            });
            this.add(tarifaDomingoGratis);
            this.add(tardesReducidas);
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbCambiarTarifa);
        }
    }

    private class MostrarClientes extends JPanel {
        public MostrarClientes() {
            this.ejecuta();
        }

        private void ejecuta(){
            JButton jbMostrarClientes = new JButton("Mostrar clientes");
            jbMostrarClientes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel clientes= new JLabel(modelo.mostrarClientes());
                    JFrame mostrarClientes = new JFrame("Mostrar clientes");
                    mostrarClientes.add(clientes);
                    mostrarClientes.pack();
                    mostrarClientes.setVisible(true);
                    System.out.println("Mostrando clientes.");
                    modelo.mostrarClientes();
                }
            });
            this.add(jbMostrarClientes);
        }
    }

    private class MostrarDatosCliente extends JPanel {
        public MostrarDatosCliente() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbMostrarDatosCliente = new JButton("Mostrar datos Cliente");
            jbMostrarDatosCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel clientes= new JLabel(modelo.mostrarDatosCliente(jtfNif.getText()));
                    JFrame mostrarClientes = new JFrame("Mostrar datos cliente");
                    mostrarClientes.add(clientes);
                    mostrarClientes.pack();
                    mostrarClientes.setVisible(true);
                    System.out.println("Mostrar datos cliente con nif "+jtfNif.getText());
                    modelo.mostrarClientes();
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbMostrarDatosCliente);
        }
    }

    private class MostrarClientesEntreFechas extends JPanel {
        public MostrarClientesEntreFechas() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfFecha1 = new JTextField(20);
            JLabel Fecha1Label = new JLabel("Fecha Inicio (yyyy-mm-ddThh:mm:ss): ");
            JTextField jtfFecha2 = new JTextField(20);
            JLabel Fecha2Label = new JLabel("Fecha Final(yyyy-mm-ddThh:mm:ss): ");
            JButton jbMostrarClientes = new JButton("Mostrar Clientes");
            jbMostrarClientes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel llamadas= new JLabel(modelo.mostrarClientesEntreFechas( LocalDateTime.parse(jtfFecha1.getText()),LocalDateTime.parse(jtfFecha2.getText())));
                    JFrame mostrarLlamadas= new JFrame("Mostrar clientes entre fechas");
                    mostrarLlamadas.add(llamadas);
                    mostrarLlamadas.pack();
                    mostrarLlamadas.setVisible(true);
                    System.out.println("Mostrando clientes entre "+jtfFecha1.getText()+" y "+jtfFecha2.getText());
                }
            });
            this.add(Fecha1Label);
            this.add(jtfFecha1);
            this.add(Fecha2Label);
            this.add(jtfFecha2);
            this.add(jbMostrarClientes);
        }
    }


}
