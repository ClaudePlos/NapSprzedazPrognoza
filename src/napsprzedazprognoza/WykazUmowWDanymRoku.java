/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napsprzedazprognoza;

import java.awt.Cursor;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import napsprzedazprognoza.excel.CSVExporter;
import napsprzedazprognoza.excel.ExcelExporter;
import napsprzedazprognoza.models.NapSprzedazPrognozaVO;
import napsprzedazprognoza.renderer.NumberRenderer;
import napsprzedazprognoza.services.NapServices;

/**
 *
 * @author k.skowronski
 */
public class WykazUmowWDanymRoku extends javax.swing.JFrame {

    
    final JPanel panel = new JPanel();
    
    private NapServices napServ = NapServices.getInstance();
    
    
    List<NapSprzedazPrognozaVO> sprzPrognoza = new ArrayList<NapSprzedazPrognozaVO>();
    
    /**
     * Creates new form WykazUmowWDanymRoku
     */
    public WykazUmowWDanymRoku() {
        initComponents();
        
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboDywizja = new javax.swing.JComboBox();
        jTextRok = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboRodzaj = new javax.swing.JComboBox();
        jLabelTotal = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelTotalFacility = new javax.swing.JLabel();
        jLabelTotalCatering = new javax.swing.JLabel();
        jLabelTotal2 = new javax.swing.JLabel();
        jLabelIlUmow = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "SK", "OB", "Miasto", "Opis", "DataZakończenia", "Kontrakt", "KwotaMiesięczna"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Wykaz umówi w danym roku:");

        jComboDywizja.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ALL", "C", "Z" }));

        jTextRok.setText("2016");

        jButton1.setText("Odśwież");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboRodzaj.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktualne", "Kończące się" }));

        jButton2.setText("excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabelTotalFacility.setText("0");

        jLabelTotalCatering.setText("0");

        jLabelTotal2.setText("0");

        jLabelIlUmow.setText("0");

        jLabel2.setText("Razem Facility: ");

        jLabel3.setText("Razem Catering:");

        jLabel4.setText("Razem:");

        jLabel5.setText("Il. Umów:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTotalFacility, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(jLabelTotalCatering)
                    .addComponent(jLabelTotal2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelIlUmow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalFacility)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalCatering)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotal2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIlUmow)
                    .addComponent(jLabel5))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextRok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboDywizja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboRodzaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(153, 153, 153)
                                .addComponent(jLabelTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboDywizja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextRok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jComboRodzaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );
        
        this.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
        
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        
        sprzPrognoza = napServ.listaSprzedazPrognozaFiltr( jTextRok.getText(), jComboDywizja.getSelectedItem().toString(), jComboRodzaj.getSelectedItem().toString() );
        
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();

        
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable1.getModel());
        jTable1.setRowSorter(sorter);
        
        tm.getDataVector().removeAllElements();
        tm.fireTableDataChanged();
        
        for(NapSprzedazPrognozaVO sp : sprzPrognoza) 
        {
  
            Object[] dok = {sp.getId(), sp.getSk(), sp.getObPelnyKod(), sp.getMiasto(), sp.getOpis(), dt1.format( sp.getDataZakonczenia() ) 
                    , sp.getKontrakt(), sp.getKwotaMiesieczna() };
            //dokLst[i] = dok;
            tm.addRow(dok);
            
        }
        
        //// Podsumowanie:
        BigDecimal total = BigDecimal.ZERO;
            for (int i = 0; i < jTable1.getRowCount(); i++){
                if ( jTable1.getValueAt(i, 7) !=null )
                {
                   BigDecimal amount = new BigDecimal( jTable1.getValueAt(i, 7).toString() );
                   total = total.add(amount); 
                }                  
            }
       BigDecimal totalFacility = BigDecimal.ZERO;
            for (int i = 0; i < jTable1.getRowCount(); i++){
                if ( jTable1.getValueAt(i, 7) != null && jTable1.getValueAt(i, 1).toString().substring(0, 1).equals("Z") )
                {
                   BigDecimal amount = new BigDecimal( jTable1.getValueAt(i, 7).toString() );
                   totalFacility = totalFacility.add(amount); 
                }                  
            } 
            
            
          BigDecimal totalCatering= BigDecimal.ZERO;
            for (int i = 0; i < jTable1.getRowCount(); i++){
                if ( jTable1.getValueAt(i, 7) != null && jTable1.getValueAt(i, 1).toString().substring(0, 1).equals("C") )
                {
                   BigDecimal amount = new BigDecimal( jTable1.getValueAt(i, 7).toString() );
                   totalCatering = totalCatering.add(amount); 
                }                  
            }   
           
         jLabelTotal2.setText( "" + df2.format(total) );
         
         jLabelTotalFacility.setText( "" + df2.format(totalFacility) );
         
         jLabelTotalCatering.setText( "" + df2.format(totalCatering) );
         
         jLabelIlUmow.setText( "" + jTable1.getRowCount() );
         
         this.setCursor( Cursor.getDefaultCursor() );
         
         TableColumnModel tcm = jTable1.getColumnModel();
         tcm.getColumn(7).setCellRenderer( new NumberRenderer( df2 ) );
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        TableModel model = jTable1.getModel();
        
        ExcelExporter csvExp = new ExcelExporter();
        String ret = csvExp.ExportTable(model, this);
        
        if ( ret.equals("OK") )
        {
            JOptionPane.showMessageDialog(panel, "Zapisane", "Info", JOptionPane.INFORMATION_MESSAGE);
        } 
        
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
            java.util.logging.Logger.getLogger(WykazUmowWDanymRoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WykazUmowWDanymRoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WykazUmowWDanymRoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WykazUmowWDanymRoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WykazUmowWDanymRoku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboDywizja;
    private javax.swing.JComboBox jComboRodzaj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelIlUmow;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabelTotal2;
    private javax.swing.JLabel jLabelTotalCatering;
    private javax.swing.JLabel jLabelTotalFacility;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextRok;
    // End of variables declaration//GEN-END:variables
}