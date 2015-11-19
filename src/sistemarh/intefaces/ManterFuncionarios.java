/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.intefaces;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistemarh.entidades.Cargo;
import sistemarh.utils.ComboBoxDepartamento;
import sistemarh.entidades.Departamento;
import sistemarh.entidades.Diretor;
import sistemarh.entidades.Funcionario;
import sistemarh.utils.ComboItem;
import sistemarh.utils.TableFuncionarios;

/**
 *
 * @author Alisson
 */
public class ManterFuncionarios extends javax.swing.JFrame {

    /**
     * calledBy Jframe
     */
    private javax.swing.JFrame calledBy;

    /**
     * Creates new form manterFuncionarios
     */
    public ManterFuncionarios() {
        initComponents();
        cbBuscar.removeAllItems();

        List<ComboItem> combo = new ArrayList();
        combo.add(new ComboItem("CPF", 1));
        combo.add(new ComboItem("RG", 2));
        combo.add(new ComboItem("Nome", 3));
        combo.add(new ComboItem("Sobrenome", 4));
        combo.add(new ComboItem("Cargo", 5));

        for (ComboItem op : combo) {
            cbBuscar.addItem(op);
        }
        GerirDepartamentos1.setVisible(false);

    }

    public ManterFuncionarios(javax.swing.JFrame calledBy) {
        this.calledBy = calledBy;
        initComponents();
        GerirDepartamentos.setVisible(false);
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GerirDepartamentos = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        tfBuscar = new javax.swing.JTextField();
        cbBuscar = new javax.swing.JComboBox();
        btAdicionar = new javax.swing.JToggleButton();
        btVoltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        GerirDepartamentos1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        GerirDepartamentos.setText("Associar Departamento(s)");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema RH - Funcionarios");
        setResizable(false);

        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        tfBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBuscarActionPerformed(evt);
            }
        });

        cbBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuscarActionPerformed(evt);
            }
        });

        btAdicionar.setText("Adicionar");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        jTable1.setModel(new TableFuncionarios());
        jTable1.setDefaultEditor(Departamento.class, new sistemarh.utils.ComboBoxDepartamento());
        jTable1.setDefaultEditor(Cargo.class, new sistemarh.utils.ComboBoxCargo());
        jTable1.setDefaultEditor(String.class, new sistemarh.utils.ComboBoxNivel());

        jTable1.setAutoCreateRowSorter(false);
        jTable1.getTableHeader().setReorderingAllowed(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        GerirDepartamentos1.setText("Associar Departamento(s)");
        GerirDepartamentos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GerirDepartamentos1ActionPerformed(evt);
            }
        });

        jButton1.setText("Excluir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto Th", 0, 36)); // NOI18N
        jLabel1.setText("Funcionários");

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Sistema de RH");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel3.setText("Parâmetro de Busca:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nome","Sobrenome","Salario"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(GerirDepartamentos1)
                                .addGap(18, 18, 18)
                                .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscar)
                    .addComponent(jLabel3))
                .addGap(13, 13, 13)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btVoltar)
                    .addComponent(btAdicionar)
                    .addComponent(GerirDepartamentos1)
                    .addComponent(jButton1))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btBuscar.getAccessibleContext().setAccessibleName("bt_mF_buscar");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        ComboItem c = (ComboItem) cbBuscar.getSelectedItem();
        Funcionario f = new Funcionario();

        switch (c.getKey()) {
            case 1:
                f.setCpf(tfBuscar.getText());
                ((TableFuncionarios) jTable1.getModel()).refreshCpf(f);
                break;

            case 2:
                f.setRg(tfBuscar.getText());
                ((TableFuncionarios) jTable1.getModel()).refreshRg(f);
                break;

            case 3:
                f.setNome(tfBuscar.getText());
                ((TableFuncionarios) jTable1.getModel()).refreshNome(f);
                break;

            case 4:
                f.setSobrenome(tfBuscar.getText());
                ((TableFuncionarios) jTable1.getModel()).refreshSobrenome(f);
                break;

            case 5:
                Cargo cargo = new Cargo();
                cargo.setNome(tfBuscar.getText());
                f.setCargo(cargo);
                ((TableFuncionarios) jTable1.getModel()).refreshCargo(f);
                break;
        }
    }//GEN-LAST:event_btBuscarActionPerformed

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        AdicionarFuncionario tela = new AdicionarFuncionario();
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        MenuPrincipal tela = new MenuPrincipal();
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void tfBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfBuscarActionPerformed

    private void cbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBuscarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int cargoId = ((Cargo) ((TableFuncionarios) jTable1.getModel()).getValueAt(jTable1.getSelectedRow(), 6)).getId();
        if (cargoId == 1 || cargoId == 2) {
            GerirDepartamentos1.setVisible(true);
        } else {
            GerirDepartamentos1.setVisible(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void GerirDepartamentos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GerirDepartamentos1ActionPerformed

        int cargoId = ((Cargo) ((TableFuncionarios) jTable1.getModel()).getValueAt(jTable1.getSelectedRow(), 6)).getId();
        Funcionario funcionario = ((TableFuncionarios) jTable1.getModel()).getValueAt(jTable1.getSelectedRow());
        if (cargoId == 1) {
            AssociarDepartamentosDirigidos telaAss = new AssociarDepartamentosDirigidos((Diretor) funcionario);
            telaAss.setVisible(true);
        } else if (cargoId == 2) {
            AssociarDepartamentoGerenciado telaAss = new AssociarDepartamentoGerenciado(funcionario);
            telaAss.setVisible(true);
        }
    }//GEN-LAST:event_GerirDepartamentos1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int confirm = JOptionPane.showConfirmDialog(null, "Está certo que deseja deletar este(s) Funcionario(s)?");
        if (confirm == 0) {
            try {
                ((TableFuncionarios) jTable1.getModel()).delete(jTable1.getSelectedRows());
            } catch (Exception ex) {
                Logger.getLogger(ManterDepartamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       ((TableFuncionarios) jTable1.getModel()).ordenaTabela(jComboBox1.getSelectedIndex());
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(ManterFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManterFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManterFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManterFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManterFuncionarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GerirDepartamentos;
    private javax.swing.JButton GerirDepartamentos1;
    private javax.swing.JToggleButton btAdicionar;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btVoltar;
    private javax.swing.JComboBox cbBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tfBuscar;
    // End of variables declaration//GEN-END:variables
}
