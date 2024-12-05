package br.com.senacsp.projetopoo.crudapi.frames;

import br.com.senacsp.projetopoo.crudapi.tablemodel.ProdutoTableModel;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FrameApplication extends javax.swing.JFrame {

    public FrameApplication() {
        initComponents();
        tblProduto.setModel(new ProdutoTableModel(new ArrayList<>()));
        adicionarPlaceHolderStyle();
        setLocationRelativeTo(null);

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
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuProduto = new javax.swing.JMenu();
        mnuMarca = new javax.swing.JMenu();
        mnuFornecedor = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ProjetoPoo");
        setResizable(false);

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
        txtIdProduto.setBounds(20, 20, 64, 30);

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
        panelProduto.add(jScrollPane1);
        jScrollPane1.setBounds(20, 310, 500, 270);
        panelProduto.add(tblProduto);
        tblProduto.setBounds(21, 250, 388, 0);

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

        mnuProduto.setText("Produto");
        jMenuBar1.add(mnuProduto);

        mnuMarca.setText("Marca");
        jMenuBar1.add(mnuMarca);

        mnuFornecedor.setText("Fornecedor");
        jMenuBar1.add(mnuFornecedor);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );

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
    private javax.swing.JComboBox<String> cmbFornecedorProduto;
    private javax.swing.JComboBox<String> cmbMarcaProduto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mnuFornecedor;
    private javax.swing.JMenu mnuMarca;
    private javax.swing.JMenu mnuProduto;
    private javax.swing.JPanel panelProduto;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtDescricaoProduto;
    private javax.swing.JTextField txtIdProduto;
    private javax.swing.JTextField txtLargura;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtProfundidade;
    private javax.swing.JTextField txtQntdeEstoqueProduto;
    // End of variables declaration//GEN-END:variables
}
