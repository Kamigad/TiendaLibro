package gm.tienda_libros.presentacion;

import gm.tienda_libros.Servicio.LibroServicio;
import gm.tienda_libros.modelo.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField idTexto;
    private JTextField libroTexto;
    private JTextField autorTexto;
    private JTextField precioTexto;
    private JTextField existenciasTexto;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private DefaultTableModel tablamodeloLibros;

    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();
        agregarButton.addActionListener(e -> agregarLibro());
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarLibroSeleccionado();
            }
        });
        modificarButton.addActionListener(e -> modificarLibro());
        eliminarButton.addActionListener(e -> eliminarLibro());
    }

    private void iniciarForma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth()/ 2);
        int y = (tamanioPantalla.height - getHeight()/ 2);
        setLocation(x,y);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //creamos el elemento idTexto oculto
        idTexto = new JTextField("");
        idTexto.setVisible(false);

        this.tablamodeloLibros = new DefaultTableModel(0, 5);
        String[] cabeceros = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        tablamodeloLibros.setColumnIdentifiers(cabeceros);
        //Instanciar el objeto JTable
        this.tablaLibros = new JTable(tablamodeloLibros);
        listarLibros();
    }

    private void agregarLibro(){
        // Leer los valores del formulario
        if(libroTexto.getText().equals("")){
            mostrarMensaje("Proporciona el nombre del libro");
            libroTexto.requestFocusInWindow();
            return;
        }
        var nombreLibro = libroTexto.getText();
        var autor = autorTexto.getText();
        var precio = Double.parseDouble(precioTexto.getText());
        var existencias = Integer.parseInt(existenciasTexto.getText());
        // Crear el objeto libro
        var libro = new Libro();
        libro.setNombreLibro(nombreLibro);
        libro.setAutor(autor);
        libro.setPrecio(precio);
        libro.setExistencias(existencias);
        this.libroServicio.guardarLibro(libro);
        mostrarMensaje("Se agrego el libro...");
        limpiarFormulario();
        listarLibros();
    }

    private void modificarLibro(){
        //leer los valores del formulario
        if (this.idTexto.getText().equals("")){// verificamos que el id no sea nulo
            mostrarMensaje("Debe seleccionar el libro que desea modificar");
        }else {// verificamos que el nombre del libro no sea nulo
            if (libroTexto.getText().equals("")){
                mostrarMensaje("Proporcione el nombre del libro...");
                libroTexto.requestFocusInWindow();
                return;
            }
        }
        //Se toma la informacion del formulario
        var id = Integer.parseInt(idTexto.getText());
        var nombre = libroTexto.getText();
        var autor = autorTexto.getText();
        var precio = Double.parseDouble(precioTexto.getText());
        var existencia = Integer.parseInt(existenciasTexto.getText());
        //se crea el objeto Libro
        var libro = new Libro(id,nombre,autor,precio,existencia);
        //finalmente se actualiza el libro en la base de datos
        libroServicio.guardarLibro(libro);
        mostrarMensaje("Libro modificado...");
        limpiarFormulario();
        listarLibros();
    }

    private void eliminarLibro(){
        //Verificamos que hayan elegido un libro ya existente
        if(idTexto.getText().equals("")){
            mostrarMensaje("Debes elegir un libro primero...");
        } else {//verificamos que tenga el nombre del libro
            if(libroTexto.getText().equals("")){
                mostrarMensaje("Proporcione el nombre del libro");
                return;
            }
        }
        //Ahora tomamos la informacionn del formulario
        var id = Integer.parseInt(idTexto.getText());
        var nombre = libroTexto.getText();
        var autor = autorTexto.getText();
        var precio = Double.parseDouble(precioTexto.getText());
        var existencias = Integer.parseInt(existenciasTexto.getText());
        var libro = new Libro(id,nombre,autor,precio,existencias);
        libroServicio.eliminarLibro(libro);
        mostrarMensaje("Libro eliminado");
        limpiarFormulario();
        listarLibros();
    }

    private void cargarLibroSeleccionado(){
        // Los indices de las columnas inician en 0
        var renglon = tablaLibros.getSelectedRow();
        if(renglon != -1){ //Regresa -1 si no se selecciono ningn registro
            String idLibro = tablaLibros.getModel().getValueAt(renglon, 0).toString();
            idTexto.setText(idLibro);
            String nombreLibro = tablaLibros.getModel().getValueAt(renglon,1).toString();
            libroTexto.setText(nombreLibro);
            String autor = tablaLibros.getModel().getValueAt(renglon,2).toString();
            autorTexto.setText(autor);
            String precio = tablaLibros.getModel().getValueAt(renglon,3).toString();
            precioTexto.setText(precio);
            String existencias = tablaLibros.getModel().getValueAt(renglon,4).toString();
            existenciasTexto.setText(existencias);
        }
    }

    private void limpiarFormulario(){
        libroTexto.setText("");
        autorTexto.setText("");
        precioTexto.setText("");
        existenciasTexto.setText("");
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void listarLibros(){
        // Limpiar la tabla
        tablamodeloLibros.setRowCount(0);
        // Obtener los libros
        var libros = libroServicio.listarLibros();
        libros.forEach((libro) -> {
            Object[] renglonLibro = {
              libro.getIdLibro(),
              libro.getNombreLibro(),
              libro.getAutor(),
              libro.getPrecio(),
              libro.getExistencias()
            };
            this.tablamodeloLibros.addRow(renglonLibro);
        });
    }
}
