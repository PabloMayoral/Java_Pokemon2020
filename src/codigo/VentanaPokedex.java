/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author xp
 */
public class VentanaPokedex extends javax.swing.JFrame {

    BufferedImage buffer1 = null;
    Image imagen1 = null;
    int contador = 0;

    Statement estado;
    ResultSet resultadoConsulta;
    Connection conexion;

    Clip sonidoFondo;
    Clip sonidoBoton;

    //estructura para guardar todo el contenido de la base de datos de golpe
    HashMap<String, Pokemon> listaPokemons = new HashMap();

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) imagenPokemon.getGraphics();
        g2.drawImage(buffer1, 0, 0, imagenPokemon.getWidth(), imagenPokemon.getHeight(), null);
    }

    /**
     * Creates new form VentanaPokedex
     */
    public VentanaPokedex() {
        initComponents();
        sonidoFondo();
        sonidoFondo.start();
        try {
            imagen1 = ImageIO.read(getClass().getResource("/imagenes/black-white.png"));

        } catch (IOException ex) {

        }

        buffer1 = (BufferedImage) imagenPokemon.createImage(imagenPokemon.getWidth(), imagenPokemon.getHeight());
        Graphics2D g2 = buffer1.createGraphics();
        dibujaElPokemosQueEstaEnLaPosicion(0);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test", "root", "");
            estado = conexion.createStatement();
            resultadoConsulta = estado.executeQuery("Select * from pokemon");
            //recorremos el array del resultado uno a uno para ir cargandolo en el hashmap

            while (resultadoConsulta.next()) {
                Pokemon p = new Pokemon();
                p.nombre = resultadoConsulta.getString("nombre");
                p.tipo2 = resultadoConsulta.getString("tipo2");
                p.tipo1 = resultadoConsulta.getString("tipo1");
                p.peso = resultadoConsulta.getString("peso");
                p.altura = resultadoConsulta.getString("altura");
                p.descripcion = resultadoConsulta.getString("descripcion");
                //añado el pokemon recien creado al hashmap
                listaPokemons.put(resultadoConsulta.getString("id"), p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("hay un error tontopoia");
        }

    }

    private void dibujaElPokemosQueEstaEnLaPosicion(int posicion) {
        int fila = posicion / 31;
        int columna = posicion % 31;
        Graphics2D g2 = (Graphics2D) buffer1.getGraphics();
        //pinta el fondo del jpanel negro
        g2.setColor(Color.black);
        g2.fillRect(0, 0, imagenPokemon.getWidth(), imagenPokemon.getHeight());
        g2.drawImage(imagen1, 0, 0, imagenPokemon.getWidth(), imagenPokemon.getHeight(),//posiciones X,Y del jpanel + ancho y alto
                columna * 96, fila * 96, columna * 96 + 96, fila * 96 + 96, null);//posion inicial y final X,Y dentro de la imagen del pokemonç
        repaint();
    }

    public void sonidoFondo() {
        try {//siempre que hace la lectura con algo que hay en el disco, se ejecuta un try
            //catch,esto hace que proteja lo que se encuentra en el disco.

            sonidoFondo = AudioSystem.getClip();
            sonidoFondo.open(AudioSystem.getAudioInputStream(getClass().getResource("/sonidos/467497__enviromaniac2__pokemon-route-201-cheesy-mix (1).wav")));
//En caso de no poner IO se transforma en una exception generico con errores gerenicos
        } catch (Exception e) {
            System.out.println("Hoal");
        }
    }

    public void sonidoBoton() {
        try {//siempre que hace la lectura con algo que hay en el disco, se ejecuta un try
            //catch,esto hace que proteja lo que se encuentra en el disco.

            sonidoBoton = AudioSystem.getClip();
            sonidoBoton.open(AudioSystem.getAudioInputStream(getClass().getResource("/sonidos/429625__camwaw__boton.wav")));
//En caso de no poner IO se transforma en una exception generico con errores gerenicos
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagenPokemon = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        nombrePokemon = new javax.swing.JLabel();
        peso = new javax.swing.JLabel();
        altura = new javax.swing.JLabel();
        tipo1 = new javax.swing.JLabel();
        tipo2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout imagenPokemonLayout = new javax.swing.GroupLayout(imagenPokemon);
        imagenPokemon.setLayout(imagenPokemonLayout);
        imagenPokemonLayout.setHorizontalGroup(
            imagenPokemonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        imagenPokemonLayout.setVerticalGroup(
            imagenPokemonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        getContentPane().add(imagenPokemon, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 210, 110));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botonizq.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 20, 20));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botondcha.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 20, 20));
        getContentPane().add(nombrePokemon, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 100, 20));

        peso.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(peso, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 90, 20));

        altura.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(altura, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, 90, 20));

        tipo1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(tipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 100, 40));

        tipo2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(tipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 100, 40));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(2);
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(3);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 220, 50));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pokedex.png"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 610, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dibujaElPokemosQueEstaEnLaPosicion(contador);
        Pokemon p = listaPokemons.get(String.valueOf(contador + 1));
        if (p != null) {
            nombrePokemon.setText(p.nombre);
            peso.setText(p.peso);
            altura.setText(p.altura);
            tipo1.setText(p.tipo1);
            tipo2.setText(p.tipo2);
            jTextArea1.setText(p.descripcion);

        } else {
            nombrePokemon.setText("NO HAY DATOS");
            peso.setText("NO HAY DATOS");
            altura.setText("NO HAY DATOS");
            tipo1.setText("NO HAY DATOS");
            tipo2.setText("NO HAY DATOS");
            jTextArea1.setText("NO HAY DATOS");
        }
        contador--;
        if (contador <= 0) {
            contador = 0;
        }
        sonidoBoton();
        sonidoBoton.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        dibujaElPokemosQueEstaEnLaPosicion(contador);
        Pokemon p = listaPokemons.get(String.valueOf(contador + 1));
        if (p != null) {
            nombrePokemon.setText(p.nombre);
            peso.setText(p.peso);
            altura.setText(p.altura);
            tipo1.setText(p.tipo1);
            tipo2.setText(p.tipo2);
            jTextArea1.setText(p.descripcion);
        } else {
            nombrePokemon.setText("NO HAY DATOS");
            peso.setText("NO HAY DATOS");
            altura.setText("NO HAY DATOS");
            tipo1.setText("NO HAY DATOS");
            tipo2.setText("NO HAY DATOS");
            jTextArea1.setText("NO HAY DATOS");
        }
        contador++;
        if (contador >= 649) {
            contador = 649;
        }
        sonidoBoton();
        sonidoBoton.start();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPokedex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel altura;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel imagenPokemon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel nombrePokemon;
    private javax.swing.JLabel peso;
    private javax.swing.JLabel tipo1;
    private javax.swing.JLabel tipo2;
    // End of variables declaration//GEN-END:variables
}
