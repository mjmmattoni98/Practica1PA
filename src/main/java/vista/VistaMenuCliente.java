package vista;

import controlador.Controlador;
import modelo.InterrogaModelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaMenuCliente extends VistaMadre{
    private Controlador controlador;
    private InterrogaModelo modelo;

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

        private void ejecuta(){
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
                            if(ItemEvent.SELECTED == e.getStateChange())
                                System.out.println("Agregar cliente particular");
                            break;
                        case "empresa":
                            if(ItemEvent.SELECTED == e.getStateChange())
                                System.out.println("Agregar cliente empresa");
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
            JTextField jtfCp = new JTextField(5);
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
                    String nif = jtfNif.getText();
                    System.out.println("NIF: " + nif);
                }
            });

//            JScrollPane scroll = new JScrollPane(this);

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
                    String nif = jtfNif.getText();
                    System.out.println("Cliente " + nif + " borrado.");
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
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbBorrarCliente = new JButton("Borrar cliente");
            jbBorrarCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nif = jtfNif.getText();
                    System.out.println("Cliente " + nif + " borrado.");
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbBorrarCliente);
        }
    }

    private class MostrarClientes extends JPanel {
        public MostrarClientes() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbBorrarCliente = new JButton("Borrar cliente");
            jbBorrarCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nif = jtfNif.getText();
                    System.out.println("Cliente " + nif + " borrado.");
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbBorrarCliente);
        }
    }

    private class MostrarDatosCliente extends JPanel {
        public MostrarDatosCliente() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbBorrarCliente = new JButton("Borrar cliente");
            jbBorrarCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nif = jtfNif.getText();
                    System.out.println("Cliente " + nif + " borrado.");
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbBorrarCliente);
        }
    }

    private class MostrarClientesEntreFechas extends JPanel {
        public MostrarClientesEntreFechas() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbBorrarCliente = new JButton("Borrar cliente");
            jbBorrarCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nif = jtfNif.getText();
                    System.out.println("Cliente " + nif + " borrado.");
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbBorrarCliente);
        }
    }


}
