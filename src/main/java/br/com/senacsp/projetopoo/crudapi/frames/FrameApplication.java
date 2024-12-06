package br.com.senacsp.projetopoo.crudapi.frames;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class FrameApplication extends javax.swing.JFrame {

    public FrameApplication() {
        initComponents();
        setSize(560, 700);
        adicionarPlaceHolderStyle();
        panelMarca.setVisible(false);
        panelFornecedor.setVisible(false);
        panelProduto.setVisible(true);
    }

    public void limpar() {
        txtIdProduto.setText("Id");
        txtIdProduto.setForeground(Color.gray);
        txtNomeProduto.setText("Nome");
        txtNomeProduto.setForeground(Color.gray);
        txtDescricaoProduto.setText("Descrição");
        txtDescricaoProduto.setForeground(Color.gray);
        txtQntdeEstoqueProduto.setText("Qntde Estoque");
        txtQntdeEstoqueProduto.setForeground(Color.gray);
        txtAltura.setText("Y");
        txtAltura.setForeground(Color.gray);
        txtLargura.setText("X");
        txtLargura.setForeground(Color.gray);
        txtProfundidade.setText("Z");
        txtProfundidade.setForeground(Color.gray);
    }
    

    public void adicionarPlaceHolderStyle() {
        for (Component c : panelProduto.getComponents()) {
            if (c instanceof JTextField) {
                JTextField txt = (JTextField) c;
                String texto = txt.getText();
                txt.setForeground(Color.gray);
                txt.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (txt.getText().equals(texto)) {
                            txt.setText("");
                            txt.setForeground(Color.black);
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        if (txt.getText().equals("")) {
                            txt.setText(texto);
                            txt.setForeground(Color.gray);
                        }
                    }
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMarca = new javax.swing.JPanel();
        txtNomeMarca = new javax.swing.JTextField();
        txtIdMarca = new javax.swing.JTextField();
        panelTblMarca = new javax.swing.JScrollPane();
        tblMarca = new javax.swing.JTable();
        imgLogoMarca = new javax.swing.JLabel();
        btnLimparMarca = new javax.swing.JButton();
        btnSalvarMarca = new javax.swing.JButton();
        btnExcluirMarca = new javax.swing.JButton();
        txtDescricaoMarca = new javax.swing.JTextField();
        panelProduto = new javax.swing.JPanel();
        txtQntdeEstoqueProduto = new javax.swing.JTextField();
        txtProfundidade = new javax.swing.JTextField();
        txtNomeProduto = new javax.swing.JTextField();
        cmbFornecedorProduto = new javax.swing.JComboBox<>();
        cmbMarcaProduto = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDescricaoProduto = new javax.swing.JTextField();
        txtIdProduto = new javax.swing.JTextField();
        txtLargura = new javax.swing.JTextField();
        txtAltura = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        panelFornecedor = new javax.swing.JPanel();
        txtTelefoneFornecedor = new javax.swing.JTextField();
        txtNomeFornecedor = new javax.swing.JTextField();
        txtDescricaoFornecedor = new javax.swing.JTextField();
        txtIdFornecedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduto1 = new javax.swing.JTable();
        btnLimparFornecedor = new javax.swing.JButton();
        btnSalvarFornecedor = new javax.swing.JButton();
        btnExcluirFornecedor = new javax.swing.JButton();
        txtCnpjFornecedor = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuProduto = new javax.swing.JMenu();
        mnuMarca = new javax.swing.JMenu();
        mnuFornecedor = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ProjetoPoo");
        setResizable(false);
        getContentPane().setLayout(null);

        panelMarca.setLayout(null);

        txtNomeMarca.setText("Nome");
        txtNomeMarca.setToolTipText("");
        txtNomeMarca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNomeMarcaFocusGained(evt);
            }
        });
        panelMarca.add(txtNomeMarca);
        txtNomeMarca.setBounds(100, 20, 280, 30);

        txtIdMarca.setText("Id");
        txtIdMarca.setEnabled(false);
        panelMarca.add(txtIdMarca);
        txtIdMarca.setBounds(20, 20, 68, 30);

        tblMarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        panelTblMarca.setViewportView(tblMarca);

        panelMarca.add(panelTblMarca);
        panelTblMarca.setBounds(20, 170, 510, 400);

        imgLogoMarca.setBackground(new java.awt.Color(204, 204, 204));
        imgLogoMarca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLogoMarca.setText("Foto");
        imgLogoMarca.setOpaque(true);
        panelMarca.add(imgLogoMarca);
        imgLogoMarca.setBounds(400, 20, 130, 130);

        btnLimparMarca.setText("Limpar");
        btnLimparMarca.setToolTipText("");
        btnLimparMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparMarcaActionPerformed(evt);
            }
        });
        panelMarca.add(btnLimparMarca);
        btnLimparMarca.setBounds(280, 110, 100, 30);

        btnSalvarMarca.setText("Salvar");
        panelMarca.add(btnSalvarMarca);
        btnSalvarMarca.setBounds(20, 110, 100, 30);

        btnExcluirMarca.setText("Excluir");
        panelMarca.add(btnExcluirMarca);
        btnExcluirMarca.setBounds(150, 110, 100, 30);

        txtDescricaoMarca.setText("Descrição");
        panelMarca.add(txtDescricaoMarca);
        txtDescricaoMarca.setBounds(20, 60, 360, 30);

        getContentPane().add(panelMarca);
        panelMarca.setBounds(0, 0, 550, 588);

        panelProduto.setLayout(null);

        txtQntdeEstoqueProduto.setText("Qntde Estoque");
        txtQntdeEstoqueProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQntdeEstoqueProdutoActionPerformed(evt);
            }
        });
        panelProduto.add(txtQntdeEstoqueProduto);
        txtQntdeEstoqueProduto.setBounds(20, 180, 360, 30);

        txtProfundidade.setText("Z");
        txtProfundidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProfundidadeActionPerformed(evt);
            }
        });
        panelProduto.add(txtProfundidade);
        txtProfundidade.setBounds(280, 220, 100, 30);

        txtNomeProduto.setText("Nome");
        panelProduto.add(txtNomeProduto);
        txtNomeProduto.setBounds(100, 20, 280, 30);
        panelProduto.add(cmbFornecedorProduto);
        cmbFornecedorProduto.setBounds(90, 100, 290, 30);
        panelProduto.add(cmbMarcaProduto);
        cmbMarcaProduto.setBounds(90, 140, 290, 30);

        jLabel1.setText("Marca:");
        panelProduto.add(jLabel1);
        jLabel1.setBounds(20, 140, 40, 30);

        jLabel2.setText("Fornecedor:");
        panelProduto.add(jLabel2);
        jLabel2.setBounds(20, 100, 70, 30);

        txtDescricaoProduto.setText("Descrição");
        panelProduto.add(txtDescricaoProduto);
        txtDescricaoProduto.setBounds(20, 60, 360, 30);

        txtIdProduto.setText("Id");
        txtIdProduto.setEnabled(false);
        panelProduto.add(txtIdProduto);
        txtIdProduto.setBounds(20, 20, 68, 30);

        txtLargura.setText("X");
        txtLargura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLarguraActionPerformed(evt);
            }
        });
        panelProduto.add(txtLargura);
        txtLargura.setBounds(20, 220, 100, 30);

        txtAltura.setText("Y");
        txtAltura.setToolTipText("");
        panelProduto.add(txtAltura);
        txtAltura.setBounds(150, 220, 100, 30);

        jScrollPane1.setViewportView(tblProduto);

        panelProduto.add(jScrollPane1);
        jScrollPane1.setBounds(20, 310, 500, 270);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Foto");
        jLabel3.setOpaque(true);
        panelProduto.add(jLabel3);
        jLabel3.setBounds(400, 20, 130, 130);

        jButton1.setText("Limpar");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelProduto.add(jButton1);
        jButton1.setBounds(280, 260, 100, 30);

        jButton2.setText("Salvar");
        panelProduto.add(jButton2);
        jButton2.setBounds(20, 260, 100, 30);

        jButton3.setText("Excluir");
        panelProduto.add(jButton3);
        jButton3.setBounds(150, 260, 100, 30);

        getContentPane().add(panelProduto);
        panelProduto.setBounds(0, 0, 550, 588);

        panelFornecedor.setLayout(null);

        txtTelefoneFornecedor.setText("Telefone");
        txtTelefoneFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneFornecedorActionPerformed(evt);
            }
        });
        panelFornecedor.add(txtTelefoneFornecedor);
        txtTelefoneFornecedor.setBounds(90, 140, 360, 30);

        txtNomeFornecedor.setText("Nome");
        panelFornecedor.add(txtNomeFornecedor);
        txtNomeFornecedor.setBounds(170, 20, 280, 30);

        txtDescricaoFornecedor.setText("Descrição");
        panelFornecedor.add(txtDescricaoFornecedor);
        txtDescricaoFornecedor.setBounds(90, 60, 360, 30);

        txtIdFornecedor.setText("Id");
        txtIdFornecedor.setEnabled(false);
        panelFornecedor.add(txtIdFornecedor);
        txtIdFornecedor.setBounds(90, 20, 68, 30);

        jScrollPane2.setViewportView(tblProduto1);

        panelFornecedor.add(jScrollPane2);
        jScrollPane2.setBounds(20, 230, 510, 340);

        btnLimparFornecedor.setText("Limpar");
        btnLimparFornecedor.setToolTipText("");
        btnLimparFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparFornecedorActionPerformed(evt);
            }
        });
        panelFornecedor.add(btnLimparFornecedor);
        btnLimparFornecedor.setBounds(350, 180, 100, 30);

        btnSalvarFornecedor.setText("Salvar");
        panelFornecedor.add(btnSalvarFornecedor);
        btnSalvarFornecedor.setBounds(90, 180, 100, 30);

        btnExcluirFornecedor.setText("Excluir");
        panelFornecedor.add(btnExcluirFornecedor);
        btnExcluirFornecedor.setBounds(220, 180, 100, 30);

        txtCnpjFornecedor.setText("CNPJ");
        txtCnpjFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCnpjFornecedorActionPerformed(evt);
            }
        });
        panelFornecedor.add(txtCnpjFornecedor);
        txtCnpjFornecedor.setBounds(90, 100, 360, 30);

        getContentPane().add(panelFornecedor);
        panelFornecedor.setBounds(0, 0, 550, 588);

        mnuProduto.setText("Produto");
        mnuProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuProdutoMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuProduto);

        mnuMarca.setText("Marca");
        mnuMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuMarcaMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuMarca);

        mnuFornecedor.setText("Fornecedor");
        mnuFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuFornecedorMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuFornecedor);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQntdeEstoqueProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQntdeEstoqueProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQntdeEstoqueProdutoActionPerformed

    private void txtProfundidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProfundidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfundidadeActionPerformed

    private void txtLarguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLarguraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLarguraActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLimparMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparMarcaActionPerformed

    private void mnuProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuProdutoMouseClicked
        panelMarca.setVisible(false);
        panelFornecedor.setVisible(false);
        panelProduto.setVisible(true);
    }//GEN-LAST:event_mnuProdutoMouseClicked

    private void mnuMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuMarcaMouseClicked
        panelProduto.setVisible(false);
        panelFornecedor.setVisible(false);
        panelMarca.setVisible(true);
    }//GEN-LAST:event_mnuMarcaMouseClicked

    private void txtTelefoneFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneFornecedorActionPerformed

    private void btnLimparFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparFornecedorActionPerformed

    private void txtCnpjFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjFornecedorActionPerformed

    private void mnuFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuFornecedorMouseClicked
        panelProduto.setVisible(false);
        panelMarca.setVisible(false);
        panelFornecedor.setVisible(true);
    }//GEN-LAST:event_mnuFornecedorMouseClicked

    private void txtNomeMarcaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeMarcaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeMarcaFocusGained

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameApplication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluirFornecedor;
    private javax.swing.JButton btnExcluirMarca;
    private javax.swing.JButton btnLimparFornecedor;
    private javax.swing.JButton btnLimparMarca;
    private javax.swing.JButton btnSalvarFornecedor;
    private javax.swing.JButton btnSalvarMarca;
    private javax.swing.JComboBox<String> cmbFornecedorProduto;
    private javax.swing.JComboBox<String> cmbMarcaProduto;
    private javax.swing.JLabel imgLogoMarca;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu mnuFornecedor;
    private javax.swing.JMenu mnuMarca;
    private javax.swing.JMenu mnuProduto;
    private javax.swing.JPanel panelFornecedor;
    private javax.swing.JPanel panelMarca;
    private javax.swing.JPanel panelProduto;
    private javax.swing.JScrollPane panelTblMarca;
    private javax.swing.JTable tblMarca;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTable tblProduto1;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtCnpjFornecedor;
    private javax.swing.JTextField txtDescricaoFornecedor;
    private javax.swing.JTextField txtDescricaoMarca;
    private javax.swing.JTextField txtDescricaoProduto;
    private javax.swing.JTextField txtIdFornecedor;
    private javax.swing.JTextField txtIdMarca;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtLargura;
    private javax.swing.JTextField txtNomeFornecedor;
    private javax.swing.JTextField txtNomeMarca;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtProfundidade;
    private javax.swing.JTextField txtQntdeEstoqueProduto;
    private javax.swing.JTextField txtTelefoneFornecedor;
    // End of variables declaration//GEN-END:variables
}
