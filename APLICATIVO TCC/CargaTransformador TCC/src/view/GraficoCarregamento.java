package view;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Cliente;
import model.bean.Demanda;
import model.bean.Equipamento;
import model.dao.ClienteDAO;
import model.dao.DemandaDAO;
import model.dao.EquipamentoDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
public class GraficoCarregamento extends javax.swing.JFrame {
    public GraficoCarregamento() {
        initComponents();
        EquipamentoDAO Dao = new EquipamentoDAO();
        DemandaDAO dao = new DemandaDAO();
        ClienteDAO cdao = new ClienteDAO();
        for(Equipamento e:Dao.read()){
            equipamento.addItem(e);
    }
        for(Demanda d:dao.read()){
            ano.addItem(d);
        }
        for(Cliente c:cdao.readBairro()){
            cbbairro.addItem(c);
        }
        for(Demanda d:dao.read()){
            anoBairro.addItem(d);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        equipamento = new javax.swing.JComboBox<>();
        ano = new javax.swing.JComboBox<>();
        anoBairro = new javax.swing.JComboBox<>();
        cbbairro = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gráficos");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/chart_bar.png"))); // NOI18N
        jButton1.setText("Gerar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        anoBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anoBairroActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/chart_bar.png"))); // NOI18N
        jButton2.setText("Gerar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Equipamento");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Ano");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Ano");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Bairro");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(anoBairro, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equipamento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jButton1)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(equipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Equipamento e = (Equipamento) equipamento.getSelectedItem();
        Demanda d = (Demanda) ano.getSelectedItem();
        int ano = d.getAno();
        d.setEquipamento(e.getId());
        DemandaDAO dao = new DemandaDAO();
        double jan = ((dao.SomademandaJaneiro(d))/e.getPotencia())*100;
        double fev = ((dao.SomademandaFevereiro(d))/e.getPotencia())*100;
        double mar = ((dao.SomademandaMarco(d))/e.getPotencia())*100;
        double abr = ((dao.SomademandaAbril(d))/e.getPotencia())*100;
        double mai = ((dao.SomademandaMaio(d))/e.getPotencia())*100;
        double jun = ((dao.SomademandaJunho(d))/e.getPotencia())*100;
        double jul = ((dao.SomademandaJulho(d))/e.getPotencia())*100;
        double ago = ((dao.SomademandaAgosto(d))/e.getPotencia())*100;
        double set = ((dao.SomademandaSetembro(d))/e.getPotencia())*100;
        double out = ((dao.SomademandaOutubro(d))/e.getPotencia())*100;
        double nov = ((dao.SomademandaNovembro(d))/e.getPotencia())*100;
        double dez = ((dao.SomademandaDezembro(d))/e.getPotencia())*100;
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            ds.addValue(jan, "janeiro","");
            ds.addValue(fev, "fevereiro", "");
            ds.addValue(mar, "março", "");
            ds.addValue(abr, "abril", "");
            ds.addValue(mai, "maio", "");
            ds.addValue(jun, "junho", "");
            ds.addValue(jul, "julho", "");
            ds.addValue(ago, "agosto", "");
            ds.addValue(set, "setembro", "");
            ds.addValue(out, "outubro", "");
            ds.addValue(nov, "novembro", "");
            ds.addValue(dez, "dezembro", "");
            JFreeChart grafico = ChartFactory.createBarChart3D("Carregamento do ano - "+e.getId(), "mês", 
                    "Carregamento (%)", ds, PlotOrientation.VERTICAL, true, true, false);
            ChartFrame f = new ChartFrame("Carregamento", grafico);
            f.pack();
            f.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Cliente cli = (Cliente) cbbairro.getSelectedItem();
        Demanda d = (Demanda) anoBairro.getSelectedItem();
        int anob = d.getAno();
        Cliente c = new Cliente();
        c.setBairro(cli.getBairro());
        DemandaDAO dao = new DemandaDAO();
        double jan = dao.SomademandaJaneiroBairro(d,c);
        double fev = dao.SomademandaFevereiroBairro(d,c);
        double mar = dao.SomademandaMarcoBairro(d,c);
        double abr = dao.SomademandaAbrilBairro(d,c);
        double mai = dao.SomademandaMaioBairro(d,c);
        double jun = dao.SomademandaJunhoBairro(d,c);
        double jul = dao.SomademandaJulhoBairro(d,c);
        double ago = dao.SomademandaAgostoBairro(d,c);
        double set = dao.SomademandaSetembroBairro(d,c);
        double out = dao.SomademandaOutubroBairro(d,c);
        double nov = dao.SomademandaNovembroBairro(d,c);
        double dez = dao.SomademandaDezembroBairro(d,c);
        DefaultCategoryDataset ds1 = new DefaultCategoryDataset();
            ds1.addValue(jan, "janeiro","");
            ds1.addValue(fev, "fevereiro", "");
            ds1.addValue(mar, "março", "");
            ds1.addValue(abr, "abril", "");
            ds1.addValue(mai, "maio", "");
            ds1.addValue(jun, "junho", "");
            ds1.addValue(jul, "julho", "");
            ds1.addValue(ago, "agosto", "");
            ds1.addValue(set, "setembro", "");
            ds1.addValue(out, "outubro", "");
            ds1.addValue(nov, "novembro", "");
            ds1.addValue(dez, "dezembro", "");
            JFreeChart grafico1 = ChartFactory.createBarChart3D("Consumo por ano - "+cli.getBairro(), 
                    "mês", "Consumo (kWh)", ds1, PlotOrientation.VERTICAL, true, true, false);
            ChartFrame f1 = new ChartFrame("Carregamento por Bairro", grafico1);
            f1.pack();
            f1.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
    private void anoBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anoBairroActionPerformed
    }//GEN-LAST:event_anoBairroActionPerformed
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
            java.util.logging.Logger.getLogger(GraficoCarregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraficoCarregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraficoCarregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraficoCarregamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraficoCarregamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> ano;
    private javax.swing.JComboBox<Object> anoBairro;
    private javax.swing.JComboBox<Object> cbbairro;
    private javax.swing.JComboBox<Object> equipamento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
