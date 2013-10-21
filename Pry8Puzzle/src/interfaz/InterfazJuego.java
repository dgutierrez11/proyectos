/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import problema.AlgoritmoRyP;
import problema.Nodo;
import problema.Tablero;

/**
 *
 * @author David
 */
public class InterfazJuego extends javax.swing.JFrame implements ActionListener{

     /**
     * La matriz con los botones para controlar y mostrar el estado del juego
     */
    private JButton[][] botonesJuego;
    private JButton[][] botonesSolucion;
     Nodo tableroInicial = new Nodo();
        AlgoritmoRyP prueba1 = new AlgoritmoRyP(tableroInicial);
        // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String POS_1_1 = "11";

    private static final String POS_2_1 = "21";

    private static final String POS_3_1 = "31";

    private static final String POS_1_2 = "12";

    private static final String POS_2_2 = "22";

    private static final String POS_3_2 = "32";

    private static final String POS_1_3 = "13";

    private static final String POS_2_3 = "23";

    private static final String POS_3_3 = "33";
    
    /**
     * Creates new form InterfazJuego
     */
    public InterfazJuego() {
        initComponents();
        panelJuego.setLayout( new GridLayout( 3, 3 ) );
        panelJuego.setBorder( new TitledBorder( "Estado inicial" ));
        panelSolucion.setLayout( new GridLayout( 3, 3 ) );
        panelSolucion.setBorder( new TitledBorder( "Estado Final" ));
        panelAnalisis.setBorder(new TitledBorder("Analisis de la Solucion"));
        
        botonesJuego=generarBotones(panelJuego);
        this.actualizarTablero(tableroInicial.getTablero().darTablero(),botonesJuego);
        
        botonesSolucion=generarBotones(panelSolucion);
        this.actualizarTablero(Tablero.darSolucion(tableroInicial.getTablero().tableroSolucion()), botonesSolucion);
   }
    
    public void tableroManual(Nodo tableroInicial){
        this.actualizarTablero(tableroInicial.getTablero().darTablero(), botonesJuego);
    }
      /**
     * Inicializa los botones del panel
     */
    private JButton[][] generarBotones(JPanel panel)
    {
        
        JButton[][] botones;
        Font fuente = new Font( "Arial", Font.BOLD, 15 );
        botones = new JButton[3][3];

        int i = 0;
        int j = 0;

        botones[ i ][ j ] = new JButton( );
        botones[ i ][ j ].setActionCommand(POS_1_1 );
        botones[ i ][ j ].addActionListener( this );
        botones[ i ][ j ].setFont( fuente );
        botones[ i ][ j ].setForeground( Color.BLACK );
        panel.add( botones[ i ][ j ] );
        j++;

        botones[ i ][ j ] = new JButton( );
        botones[ i ][ j ].setActionCommand( POS_1_2 );
        botones[ i ][ j ].addActionListener( this );
        botones[ i ][ j ].setFont( fuente );
        botones[ i ][ j ].setForeground( Color.BLACK );
        panel.add( botones[ i ][ j ] );
        j++;

        botones[ i ][ j ] = new JButton( );
        botones[ i ][ j ].setActionCommand( POS_1_3 );
        botones[ i ][ j ].addActionListener( this );
        botones[ i ][ j ].setFont( fuente );
        botones[ i ][ j ].setForeground( Color.BLACK );
        panel.add( botones[ i ][ j ] );

        j = 0;
        i++;

        botones[ i ][ j ] = new JButton( );
        botones[ i ][ j ].setActionCommand( POS_2_1 );
        botones[ i ][ j ].addActionListener( this );
        botones[ i ][ j ].setFont( fuente );
        botones[ i ][ j ].setForeground( Color.BLACK );
        panel.add( botones[ i ][ j ] );
        j++;

        botones[ i ][ j ] = new JButton( );
        botones[ i ][ j ].setActionCommand( POS_2_2 );
        botones[ i ][ j ].addActionListener( this );
        botones[ i ][ j ].setFont( fuente );
        botones[ i ][ j ].setForeground( Color.BLACK );
        panel.add( botones[ i ][ j ] );
        j++;

        botones[ i ][ j ] = new JButton( );
        botones[ i ][ j ].setActionCommand( POS_2_3 );
        botones[ i ][ j ].addActionListener( this );
        botones[ i ][ j ].setFont( fuente );
        botones[ i ][ j ].setForeground( Color.BLACK );
        panel.add( botones[ i ][ j ] );

        j = 0;
        i++;

        botones[ i ][ j ] = new JButton( );
        botones[ i ][ j ].setActionCommand( POS_3_1 );
        botones[ i ][ j ].addActionListener( this );
        botones[ i ][ j ].setFont( fuente );
        botones[ i ][ j ].setForeground( Color.BLACK );
        panel.add( botones[ i ][ j ] );
        j++;

        botones[ i ][ j ] = new JButton( );
        botones[ i ][ j ].setActionCommand( POS_3_2 );
        botones[ i ][ j ].addActionListener( this );
        botones[ i ][ j ].setFont( fuente );
        botones[ i ][ j ].setForeground( Color.BLACK );
        panel.add( botones[ i ][ j ] );
        j++;

        botones[ i ][ j ] = new JButton( );
        botones[ i ][ j ].setActionCommand( POS_3_3 );
        botones[ i ][ j ].addActionListener( this );
        botones[ i ][ j ].setFont( fuente );
        botones[ i ][ j ].setForeground( Color.BLACK );
        panel.add( botones[ i ][ j ] );
        
        return botones;
    }
  public void actualizarTablero( int[][] tablero, JButton[][] botonesJuego)
    {
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
                if( tablero[ i ][ j ] != Tablero.HUECO )
                {
                    botonesJuego[ i ][ j ].setText( "" + tablero[ i ][ j ] );
                    botonesJuego[ i ][ j ].setBackground( Color.LIGHT_GRAY );
                }
                else
                {
                    botonesJuego[ i ][ j ].setText( "" );
                    botonesJuego[ i ][ j ].setBackground( Color.WHITE );
                }
            }
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

        panelJuego = new javax.swing.JPanel();
        panelSolucion = new javax.swing.JPanel();
        panelAnalisis = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNodosAnalizados = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNodosGenerados = new javax.swing.JLabel();
        lblNodosPodados = new javax.swing.JLabel();
        btnSolucion = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuNuevo = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("8 Puzzle");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(600, 400));

        panelJuego.setMaximumSize(new java.awt.Dimension(500, 500));
        panelJuego.setPreferredSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        panelSolucion.setMaximumSize(new java.awt.Dimension(500, 500));
        panelSolucion.setPreferredSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelSolucionLayout = new javax.swing.GroupLayout(panelSolucion);
        panelSolucion.setLayout(panelSolucionLayout);
        panelSolucionLayout.setHorizontalGroup(
            panelSolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        panelSolucionLayout.setVerticalGroup(
            panelSolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jLabel1.setText("Nodos analizados:");

        lblNodosAnalizados.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblNodosAnalizados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Nodos Generados:");

        jLabel4.setText("Nodos Podados:");

        lblNodosGenerados.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblNodosGenerados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNodosPodados.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblNodosPodados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelAnalisisLayout = new javax.swing.GroupLayout(panelAnalisis);
        panelAnalisis.setLayout(panelAnalisisLayout);
        panelAnalisisLayout.setHorizontalGroup(
            panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnalisisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAnalisisLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNodosAnalizados, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                    .addGroup(panelAnalisisLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(15, 15, 15)
                        .addComponent(lblNodosPodados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelAnalisisLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(4, 4, 4)
                        .addComponent(lblNodosGenerados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAnalisisLayout.setVerticalGroup(
            panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnalisisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblNodosAnalizados, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblNodosGenerados, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lblNodosPodados, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        btnSolucion.setText("Mostrar Solucion");
        btnSolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolucionActionPerformed(evt);
            }
        });

        jMenu1.setText("Puzzle");

        jMenuNuevo.setText("Nuevo Juego");
        jMenuNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuNuevo);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Otro");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSolucion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(panelAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(panelSolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSolucion)))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolucionActionPerformed
        // TODO add your handling code here:
         try {
            Nodo solucion = prueba1.RyP_laMejor();
            System.out.println("\nSolucion: \n");
             System.out.println(solucion.toString());
             
             System.out.println("\nNodo analizados: \n");
             System.out.println(prueba1.getNumanalizados());
             lblNodosAnalizados.setText(String.valueOf(prueba1.getNumanalizados()));
             System.out.println("\nNodo generados: \n");
             System.out.println(prueba1.getNumgenerados());
             lblNodosGenerados.setText(String.valueOf(prueba1.getNumgenerados()));
             System.out.println("\nNodo podados: \n");
             System.out.println(prueba1.getNumpodados());
             lblNodosPodados.setText(String.valueOf(prueba1.getNumpodados()));
             
            this.actualizarTablero(solucion.getTablero().darTablero(), botonesJuego);
        } catch (InterruptedException ex) {
            Logger.getLogger(InterfazJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSolucionActionPerformed

    private void jMenuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNuevoActionPerformed
        // TODO add your handling code here:
        
   
        for( int i = 0; i < 3; i++ )
        {
            for( int j = 0; j < 3; j++ )
            {
               botonesJuego[ i ][ j ].setText( "" );
               botonesJuego[ i ][ j ].setBackground( Color.LIGHT_GRAY );
               botonesSolucion[ i ][ j ].setText( "" );
               botonesSolucion[ i ][ j ].setBackground( Color.LIGHT_GRAY );
              
            }
        }
        botonesSolucion[ 2 ][ 2 ].setBackground( Color.WHITE );
        botonesJuego[ 2 ][ 2 ].setBackground( Color.WHITE );
        
        InterfazNuevo nuevo=new InterfazNuevo(this);
        nuevo.setVisible(true);
    
    }//GEN-LAST:event_jMenuNuevoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
      /*  try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSolucion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuNuevo;
    private javax.swing.JLabel lblNodosAnalizados;
    private javax.swing.JLabel lblNodosGenerados;
    private javax.swing.JLabel lblNodosPodados;
    private javax.swing.JPanel panelAnalisis;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelSolucion;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
