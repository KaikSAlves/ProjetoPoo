package br.com.senacsp.projetopoo.crudapi.frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import br.com.senacsp.projetopoo.crudapi.clients.ApiClient;
import br.com.senacsp.projetopoo.crudapi.model.BaseEntity;
import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import br.com.senacsp.projetopoo.crudapi.model.Marca;
import br.com.senacsp.projetopoo.crudapi.model.Produto;
import br.com.senacsp.projetopoo.crudapi.tablemodels.FornecedorTableModel;
import br.com.senacsp.projetopoo.crudapi.tablemodels.MarcaTableModel;
import br.com.senacsp.projetopoo.crudapi.tablemodels.ProdutoTableModel;


public class FrameApplication extends javax.swing.JFrame {

    private List<Produto> produtos = new ArrayList<>();
    private List<Marca> marcas = new ArrayList<>();
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private Produto produto;
    private Marca marca;
    private Fornecedor fornecedor;

    public FrameApplication() {
        initComponents();
        setSize(560, 700);
        setLocationRelativeTo(null);

        adicionarPlaceHolderStyle();

        try {
            produtos = (List<Produto>) ApiClient.get(Produto.class);
            marcas = (List<Marca>) ApiClient.get(Marca.class);
            fornecedores = (List<Fornecedor>) ApiClient.get(Fornecedor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Imagens", ImageIO.getReaderFileSuffixes());

        cmbMarcaProduto.setModel(new DefaultComboBoxModel<>(marcas.toArray(new Object[marcas.size()])));
        cmbFornecedorProduto.setModel(new DefaultComboBoxModel<>(fornecedores.toArray(new Object[marcas.size()])));

        tblProduto.setModel(new ProdutoTableModel(produtos));
        panelMarca.setVisible(false);
        panelFornecedor.setVisible(false);
        panelProduto.setVisible(true);

        tblProduto.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tblProdutoValueChanged(e);
            }
        });

        tblMarca.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tblMarcaValueChanged(e);
            }
        });

        tblFornecedor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tblFornecedorValueChanged(e);
            }
        });

        imgLogoMarca.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    chooser.setFileFilter(imageFilter);
                    if (chooser.showOpenDialog(FrameApplication.this) == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        try {
                            BufferedImage bufImg = ImageIO.read(file);
                            Image imagem = bufImg.getScaledInstance(imgLogoMarca.getWidth(), imgLogoMarca.getHeight(),
                                    Image.SCALE_SMOOTH);
                            ImageIcon imgLabel = new ImageIcon(imagem);
                            imgLogoMarca.setIcon(imgLabel);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }
                }
            }
        });
    }

    private String[] toArrayOfName(List<? extends BaseEntity> list) {
        List<String> listaNomes = new ArrayList<>();
        String[] nomes;
        for (BaseEntity b : list) {
            listaNomes.add(b.getNome());
        }

        return listaNomes.toArray(new String[listaNomes.size()]);
    }

    public void limparTelaProduto() {
        txtIdProduto.setText("Id");
        txtNomeProduto.setText("Nome");
        txtDescricaoProduto.setText("Descrição");
        txtQntdeEstoqueProduto.setText("Qntde Estoque");
        txtAltura.setText("Y");
        txtLargura.setText("X");
        txtProfundidade.setText("Z");

        for (Component c : panelProduto.getComponents()) {
            if (c instanceof JTextField) {
                c = (JTextField) c;
                c.setForeground(Color.gray);
            }
        }

        imgMarcaProduto.setIcon(null);
    }

    public void limparTelaMarca() {
        txtIdMarca.setText("Id");
        txtNomeMarca.setText("Nome");
        txtDescricaoMarca.setText("Descrição");

        for (Component c : panelMarca.getComponents()) {
            if (c instanceof JTextField) {
                c = (JTextField) c;
                c.setForeground(Color.gray);
            }
        }

        imgLogoMarca.setIcon(null);
    }

    public void limparTelaFornecedor() {
        txtIdFornecedor.setText("Id");
        txtCnpjFornecedor.setText("Cnpj");
        txtDescricaoFornecedor.setText("Descrição");
        txtNomeFornecedor.setText("Nome");
        txtTelefoneFornecedor.setText("Telefone");

        for (Component c : panelFornecedor.getComponents()) {
            if (c instanceof JTextField) {
                c = (JTextField) c;
                c.setForeground(Color.gray);
            }
        }
    }

    public void adicionarPlaceHolderStyle() {
        for (Component panel : getContentPane().getComponents()) {
            if (panel instanceof JPanel) {
                for (Component input : ((JPanel) panel).getComponents()) {
                    if (input instanceof JTextField) {
                        JTextField txt = (JTextField) input;
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

                        txt.getDocument().addDocumentListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                txt.setForeground(Color.black);
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {

                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {

                            }
                        });
                    }
                }
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
        imgMarcaProduto = new javax.swing.JLabel();
        btnLimparProduto = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        panelFornecedor = new javax.swing.JPanel();
        txtTelefoneFornecedor = new javax.swing.JTextField();
        txtNomeFornecedor = new javax.swing.JTextField();
        txtDescricaoFornecedor = new javax.swing.JTextField();
        txtIdFornecedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFornecedor = new javax.swing.JTable();
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
        txtIdMarca.setBounds(20, 20, 64, 30);

        tblMarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblMarca.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        panelTblMarca.setViewportView(tblMarca);

        panelMarca.add(panelTblMarca);
        panelTblMarca.setBounds(20, 170, 510, 400);

        imgLogoMarca.setBackground(new java.awt.Color(204, 204, 204));
        imgLogoMarca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLogoMarca.setText("Adicionar Foto");
        imgLogoMarca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        tblProduto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblProduto);

        panelProduto.add(jScrollPane1);
        jScrollPane1.setBounds(20, 310, 500, 270);

        imgMarcaProduto.setBackground(new java.awt.Color(204, 204, 204));
        imgMarcaProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgMarcaProduto.setText("Adicionar Foto");
        imgMarcaProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgMarcaProduto.setOpaque(true);
        panelProduto.add(imgMarcaProduto);
        imgMarcaProduto.setBounds(400, 20, 130, 130);

        btnLimparProduto.setText("Limpar");
        btnLimparProduto.setToolTipText("");
        btnLimparProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparProdutoActionPerformed(evt);
            }
        });
        panelProduto.add(btnLimparProduto);
        btnLimparProduto.setBounds(280, 260, 100, 30);

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
        txtIdFornecedor.setBounds(90, 20, 64, 30);

        tblFornecedor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblFornecedor);

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

    private void btnLimparProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparProdutoActionPerformed
        limparTelaProduto();
    }//GEN-LAST:event_btnLimparProdutoActionPerformed

    private void btnLimparMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparMarcaActionPerformed
        limparTelaMarca();
    }//GEN-LAST:event_btnLimparMarcaActionPerformed

    private void mnuProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuProdutoMouseClicked
        panelMarca.setVisible(false);
        panelFornecedor.setVisible(false);
        panelProduto.setVisible(true);

        tblProduto.setModel(new ProdutoTableModel(produtos));
        cmbMarcaProduto.setModel(new DefaultComboBoxModel<>(marcas.toArray(new Object[marcas.size()])));
        cmbFornecedorProduto.setModel(new DefaultComboBoxModel<>(fornecedores.toArray(new Object[fornecedores.size()])));

    }//GEN-LAST:event_mnuProdutoMouseClicked

    private void mnuMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuMarcaMouseClicked
        panelProduto.setVisible(false);
        panelFornecedor.setVisible(false);
        panelMarca.setVisible(true);
        tblMarca.setModel(new MarcaTableModel(marcas));

    }//GEN-LAST:event_mnuMarcaMouseClicked

    private void txtTelefoneFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneFornecedorActionPerformed

    private void btnLimparFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparFornecedorActionPerformed
        limparTelaFornecedor();
    }//GEN-LAST:event_btnLimparFornecedorActionPerformed

    private void txtCnpjFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjFornecedorActionPerformed

    private void mnuFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuFornecedorMouseClicked
        panelProduto.setVisible(false);
        panelMarca.setVisible(false);
        panelFornecedor.setVisible(true);

        tblFornecedor.setModel(new FornecedorTableModel(fornecedores));

    }//GEN-LAST:event_mnuFornecedorMouseClicked

    private void txtNomeMarcaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeMarcaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeMarcaFocusGained

    private void tblProdutoValueChanged(ListSelectionEvent e) {
        int linha = tblProduto.getSelectedRow();

        if (linha >= 0) {
            produto = produtos.get(linha);
            txtIdProduto.setText(String.format("%d", produto.getId()));
            txtNomeProduto.setText(produto.getNome());
            txtDescricaoProduto.setText(produto.getDescricao());
            cmbFornecedorProduto.setSelectedItem(produto.getFornecedor());
            cmbMarcaProduto.setSelectedItem(produto.getMarca());
            txtQntdeEstoqueProduto.setText(String.format("%d", produto.getQuantidadeEstoque()));
            txtAltura.setText(String.format("%d", produto.getAltura()));
            txtLargura.setText(String.format("%d", produto.getLargura()));
            txtProfundidade.setText(String.format("%d", produto.getProfundidade()));
            imgMarcaProduto.setIcon(new ImageIcon(produto.getMarca().getLogo()));
        }
    }

    public void tblMarcaValueChanged(ListSelectionEvent e) {
        int linha = tblMarca.getSelectedRow();

        if (linha >= 0) {
            marca = marcas.get(linha);
            txtIdMarca.setText(String.format("%d", marca.getId()));
            txtNomeMarca.setText(marca.getNome());
            txtDescricaoMarca.setText(marca.getDescricao());
            imgLogoMarca.setIcon(new ImageIcon(marca.getLogo()));
        }
    }

    public void tblFornecedorValueChanged(ListSelectionEvent e) {
        int linha = tblFornecedor.getSelectedRow();

        if (linha >= 0) {
            fornecedor = fornecedores.get(linha);
            txtIdFornecedor.setText(String.format("%d", fornecedor.getId()));
            txtCnpjFornecedor.setText(fornecedor.getCnpj());
            txtDescricaoFornecedor.setText(fornecedor.getDescricao());
            txtTelefoneFornecedor.setText(fornecedor.getTelefone());

        }
    }

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
    private javax.swing.JButton btnLimparProduto;
    private javax.swing.JButton btnSalvarFornecedor;
    private javax.swing.JButton btnSalvarMarca;
    private javax.swing.JComboBox<Object> cmbFornecedorProduto;
    private javax.swing.JComboBox<Object> cmbMarcaProduto;
    private javax.swing.JLabel imgLogoMarca;
    private javax.swing.JLabel imgMarcaProduto;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTable tblFornecedor;
    private javax.swing.JTable tblMarca;
    private javax.swing.JTable tblProduto;
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
