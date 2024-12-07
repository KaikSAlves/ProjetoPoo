package com.senac.projetopoo.frames;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.senac.projetopoo.dao.EMFactory;
import com.senac.projetopoo.dao.FornecedorDao;
import com.senac.projetopoo.dao.MarcaDao;
import com.senac.projetopoo.dao.ProdutoDao;
import com.senac.projetopoo.model.BaseEntity;
import com.senac.projetopoo.model.Fornecedor;
import com.senac.projetopoo.model.Marca;
import com.senac.projetopoo.model.Produto;
import com.senac.projetopoo.tablemodels.FornecedorTableModel;
import com.senac.projetopoo.tablemodels.MarcaTableModel;
import com.senac.projetopoo.tablemodels.ProdutoTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameApplication extends javax.swing.JFrame {

    private List<Produto> produtos = new ArrayList<>();
    private List<Marca> marcas = new ArrayList<>();
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private Produto produto;
    private Marca marca;
    private Fornecedor fornecedor;
    private ProdutoDao produtoDao;
    private MarcaDao marcaDao;
    private FornecedorDao fornecedorDao;
    private JFileChooser chooser;
    private File file;

    public FrameApplication() {

        Locale.setDefault(Locale.US);
        initComponents();
        setSize(560, 700);
        setLocationRelativeTo(null);

        adicionarPlaceHolderStyle();

        produtoDao = new ProdutoDao(EMFactory.getEntityManager());
        marcaDao = new MarcaDao(EMFactory.getEntityManager());
        fornecedorDao = new FornecedorDao(EMFactory.getEntityManager());

        try {
            produtos = produtoDao.listar();
            marcas = marcaDao.listar();
            fornecedores = fornecedorDao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        chooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Imagens", ImageIO.getReaderFileSuffixes());

        cmbMarcaProduto.setModel(new DefaultComboBoxModel<>(marcas.toArray(new Object[marcas.size()])));
        cmbFornecedorProduto.setModel(new DefaultComboBoxModel<>(fornecedores.toArray(new Object[fornecedores.size()])));

        tblProduto.setModel(new ProdutoTableModel(produtos));
        panelMarca.setVisible(false);
        panelFornecedor.setVisible(false);
        panelProduto.setVisible(true);

        imgLogoMarca.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    chooser.setFileFilter(imageFilter);
                    if (chooser.showOpenDialog(FrameApplication.this) == JFileChooser.APPROVE_OPTION) {
                        file = chooser.getSelectedFile();
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
    }

    public void limparTelaProduto() throws Exception {
        txtIdProduto.setText("Id");
        txtNomeProduto.setText("Nome");
        txtDescricaoProduto.setText("Descrição");
        txtQntdeEstoqueProduto.setText("Qntde Estoque");
        txtAltura.setText("Y");
        txtLargura.setText("X");
        txtProfundidade.setText("Z");
        txtPrecoProduto.setText("Preço");

        for (Component c : panelProduto.getComponents()) {
            if (c instanceof JTextField) {
                c = (JTextField) c;
                c.setForeground(Color.gray);
            }
        }

        imgMarcaProduto.setIcon(null);
        produtos = produtoDao.listar();
        tblProduto.setModel(new ProdutoTableModel(produtos));
        produto = null;
    }

    public void limparTelaMarca() throws Exception {
        txtIdMarca.setText("Id");
        txtNomeMarca.setText("Nome");
        txtDescricaoMarca.setText("Descrição");

        for (Component c : panelMarca.getComponents()) {
            if (c instanceof JTextField) {
                c = (JTextField) c;
                c.setForeground(Color.gray);
            }
        }

        marca = null;
        imgLogoMarca.setIcon(null);
        marcas = marcaDao.listar();
        tblMarca.setModel(new MarcaTableModel(marcas));
    }

    public void limparTelaFornecedor() throws Exception {
        txtIdFornecedor.setText("Id");
        txtCnpjFornecedor.setText("CNPJ");
        txtDescricaoFornecedor.setText("Descrição");
        txtNomeFornecedor.setText("Nome");
        txtTelefoneFornecedor.setText("Telefone");

        for (Component c : panelFornecedor.getComponents()) {
            if (c instanceof JTextField) {
                c = (JTextField) c;
                c.setForeground(Color.gray);
            }
        }

        fornecedor = null;
        fornecedores = fornecedorDao.listar();
        tblFornecedor.setModel(new FornecedorTableModel(fornecedores));
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
        txtPrecoProduto = new javax.swing.JTextField();
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
        btnSalvarProduto = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtQntdeEstoqueProduto = new javax.swing.JTextField();
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
        txtNomeMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeMarcaActionPerformed(evt);
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
        btnSalvarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarMarcaActionPerformed(evt);
            }
        });
        panelMarca.add(btnSalvarMarca);
        btnSalvarMarca.setBounds(20, 110, 100, 30);

        btnExcluirMarca.setText("Excluir");
        btnExcluirMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirMarcaActionPerformed(evt);
            }
        });
        panelMarca.add(btnExcluirMarca);
        btnExcluirMarca.setBounds(150, 110, 100, 30);

        txtDescricaoMarca.setText("Descrição");
        panelMarca.add(txtDescricaoMarca);
        txtDescricaoMarca.setBounds(20, 60, 360, 30);

        getContentPane().add(panelMarca);
        panelMarca.setBounds(0, 0, 550, 588);

        panelProduto.setLayout(null);

        txtPrecoProduto.setText("Preço");
        txtPrecoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecoProdutoActionPerformed(evt);
            }
        });
        panelProduto.add(txtPrecoProduto);
        txtPrecoProduto.setBounds(210, 180, 170, 30);

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

        cmbMarcaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMarcaProdutoActionPerformed(evt);
            }
        });
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
        imgMarcaProduto.setText("Foto");
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

        btnSalvarProduto.setText("Salvar");
        btnSalvarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarProdutoActionPerformed(evt);
            }
        });
        panelProduto.add(btnSalvarProduto);
        btnSalvarProduto.setBounds(20, 260, 100, 30);

        jButton3.setText("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelProduto.add(jButton3);
        jButton3.setBounds(150, 260, 100, 30);

        txtQntdeEstoqueProduto.setText("Qntde Estoque");
        txtQntdeEstoqueProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQntdeEstoqueProdutoActionPerformed(evt);
            }
        });
        panelProduto.add(txtQntdeEstoqueProduto);
        txtQntdeEstoqueProduto.setBounds(20, 180, 170, 30);

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
        btnSalvarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarFornecedorActionPerformed(evt);
            }
        });
        panelFornecedor.add(btnSalvarFornecedor);
        btnSalvarFornecedor.setBounds(90, 180, 100, 30);

        btnExcluirFornecedor.setText("Excluir");
        btnExcluirFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirFornecedorActionPerformed(evt);
            }
        });
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

    private void txtPrecoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoProdutoActionPerformed

    private void txtProfundidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProfundidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfundidadeActionPerformed

    private void txtLarguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLarguraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLarguraActionPerformed

    private void btnLimparProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparProdutoActionPerformed
        try {
            limparTelaProduto();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnLimparProdutoActionPerformed

    private void btnLimparMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparMarcaActionPerformed
        try {
            limparTelaMarca();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        try {
            limparTelaFornecedor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    private void btnSalvarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarMarcaActionPerformed
        if (txtNomeMarca.getText().equals("Nome")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos para prosseguir");
        } else {
            String nome = txtNomeMarca.getText();
            String descricao = txtDescricaoMarca.getText();

            try {
                if (marca == null) {
                    marca = new Marca();
                }

                marca.setNome(nome);
                marca.setDescricao(descricao);

                if (file != null) {
                    byte[] imagemBytes = Files.readAllBytes(file.toPath());
                    marca.setLogo(imagemBytes);
                }

                if (marca.getId() == 0) {
                    marcaDao.inserir(marca);
                } else {
                    marcaDao.alterar(marca);
                }

                limparTelaMarca();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }//GEN-LAST:event_btnSalvarMarcaActionPerformed

    private void btnSalvarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarProdutoActionPerformed
        if (txtNomeProduto.getText().equals("Nome")) {
            JOptionPane.showConfirmDialog(null, "Preencha ao menos o nome do produto!");
        } else {
            String nome = txtNomeProduto.getText();
            String descricao = txtDescricaoProduto.getText();
            Fornecedor fornecedor = (Fornecedor) cmbFornecedorProduto.getSelectedItem();
            Marca marca = (Marca) cmbMarcaProduto.getSelectedItem();
            int qntdeEstoque = Integer.parseInt(txtQntdeEstoqueProduto.getText());
            int altura = Integer.parseInt(txtAltura.getText());
            int largura = Integer.parseInt(txtLargura.getText());
            int profundidade = Integer.parseInt(txtProfundidade.getText());
            double preco = Double.parseDouble(txtPrecoProduto.getText());

            if (produto == null) {
                produto = new Produto();
            }

            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setFornecedor(fornecedor);
            produto.setMarca(marca);
            produto.setQuantidadeEstoque(qntdeEstoque);
            produto.setAltura(altura);
            produto.setLargura(largura);
            produto.setProfundidade(profundidade);
            produto.setPreco(preco);

            try {
                if (produto.getId() == 0) {
                    produtoDao.alterar(produto);
                } else {
                    produtoDao.inserir(produto);
                }

                limparTelaProduto();
            } catch (Exception ex) {
                Logger.getLogger(FrameApplication.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnSalvarProdutoActionPerformed

    private void txtQntdeEstoqueProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQntdeEstoqueProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQntdeEstoqueProdutoActionPerformed

    private void btnExcluirMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirMarcaActionPerformed
        if (txtIdMarca.getText().equals("Id")) {
            JOptionPane.showMessageDialog(null, "Selecione uma marca para realizar essa operacao");
        } else {
            try {
                marcaDao.excluir(Integer.parseInt(txtIdMarca.getText()));
                limparTelaMarca();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível excluir essa marca!");
            }
        }
    }//GEN-LAST:event_btnExcluirMarcaActionPerformed

    private void btnSalvarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarFornecedorActionPerformed
        if (txtNomeFornecedor.getText().equals("Nome")) {
            JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
        } else {
            String nome = txtNomeFornecedor.getText();
            String descricao = txtDescricaoFornecedor.getText();
            String cnpj = txtCnpjFornecedor.getText();
            System.out.println(cnpj);
            String telefone = txtTelefoneFornecedor.getText();

            try {
                if (fornecedor == null) {
                    fornecedor = new Fornecedor();
                }

                fornecedor.setNome(nome);
                fornecedor.setDescricao(descricao);
                fornecedor.setCnpj(cnpj);
                fornecedor.setTelefone(telefone);

                if (fornecedor.getId() == 0) {
                    fornecedorDao.inserir(fornecedor);
                } else {
                    fornecedorDao.alterar(fornecedor);
                }

                limparTelaFornecedor();
            } catch (Exception ex) {
                Logger.getLogger(FrameApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSalvarFornecedorActionPerformed

    private void btnExcluirFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirFornecedorActionPerformed
        if (txtIdFornecedor.getText().equals("Id")) {
            JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
        } else {
            try {
                fornecedorDao.excluir(Integer.parseInt(txtIdFornecedor.getText()));
                limparTelaFornecedor();
            } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível excluir esse fornecedor!");
            }
        }
    }//GEN-LAST:event_btnExcluirFornecedorActionPerformed

    private void txtNomeMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeMarcaActionPerformed

    private void cmbMarcaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMarcaProdutoActionPerformed
        Marca marca = (Marca) cmbMarcaProduto.getSelectedItem();
        if (marca.getLogo() != null) {

            try {
                byte[] imagemBytes = marca.getLogo();
                ImageIcon icon = new ImageIcon(imagemBytes);
                Image img = icon.getImage().getScaledInstance(imgMarcaProduto.getWidth(), imgMarcaProduto.getHeight(),
                        Image.SCALE_SMOOTH);
                imgMarcaProduto.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                imgMarcaProduto.setIcon(null);
                JOptionPane.showMessageDialog(FrameApplication.this,
                        "Erro ao carregar a imagem.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            imgMarcaProduto.setIcon(null);
        }
    }//GEN-LAST:event_cmbMarcaProdutoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblProdutoValueChanged(ListSelectionEvent e) {
        int linha = tblProduto.getSelectedRow();

        if (linha >= 0) {
            produto = produtos.get(linha);
            txtIdProduto.setText(String.valueOf(produto.getId()));
            txtNomeProduto.setText(produto.getNome());
            txtQntdeEstoqueProduto.setText(String.valueOf(produto.getQuantidadeEstoque()));
            txtDescricaoProduto.setText(produto.getDescricao());
            cmbFornecedorProduto.setSelectedItem(produto.getFornecedor());
            cmbMarcaProduto.setSelectedItem(produto.getMarca());
            txtPrecoProduto.setText(String.valueOf(produto.getQuantidadeEstoque()));
            txtAltura.setText(String.valueOf(produto.getAltura()));
            txtLargura.setText(String.valueOf(produto.getLargura()));
            txtProfundidade.setText(String.valueOf(produto.getProfundidade()));
            txtPrecoProduto.setText(String.valueOf(produto.getPreco()));

            if (produto.getMarca().getLogo() != null) {
                try {
                    byte[] imagemBytes = produto.getMarca().getLogo();
                    ImageIcon icon = new ImageIcon(imagemBytes);
                    Image img = icon.getImage().getScaledInstance(imgMarcaProduto.getWidth(), imgMarcaProduto.getHeight(),
                            Image.SCALE_SMOOTH);
                    imgMarcaProduto.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    imgMarcaProduto.setIcon(null);
                    JOptionPane.showMessageDialog(FrameApplication.this,
                            "Erro ao carregar a imagem.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                imgMarcaProduto.setIcon(null);
            }
        }
    }

    public void tblMarcaValueChanged(ListSelectionEvent e) {
        int linha = tblMarca.getSelectedRow();

        if (linha >= 0) {
            marca = marcas.get(linha);
            txtIdMarca.setText(String.valueOf(marca.getId()));
            txtNomeMarca.setText(marca.getNome());
            txtDescricaoMarca.setText(marca.getDescricao());

            if (marca.getLogo() != null) {

                try {
                    byte[] imagemBytes = marca.getLogo();
                    ImageIcon icon = new ImageIcon(imagemBytes);
                    Image img = icon.getImage().getScaledInstance(imgLogoMarca.getWidth(), imgLogoMarca.getHeight(),
                            Image.SCALE_SMOOTH);
                    imgLogoMarca.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    imgLogoMarca.setIcon(null);
                    JOptionPane.showMessageDialog(FrameApplication.this,
                            "Erro ao carregar a imagem.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                imgLogoMarca.setIcon(null);
            }

        }
    }

    public void tblFornecedorValueChanged(ListSelectionEvent e) {
        int linha = tblFornecedor.getSelectedRow();

        if (linha >= 0) {
            fornecedor = fornecedores.get(linha);
            txtIdFornecedor.setText(String.valueOf(fornecedor.getId()));
            txtNomeFornecedor.setText(fornecedor.getNome());
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
    private javax.swing.JButton btnSalvarProduto;
    private javax.swing.JComboBox<Object> cmbFornecedorProduto;
    private javax.swing.JComboBox<Object> cmbMarcaProduto;
    private javax.swing.JLabel imgLogoMarca;
    private javax.swing.JLabel imgMarcaProduto;
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
    private javax.swing.JTextField txtPrecoProduto;
    private javax.swing.JTextField txtProfundidade;
    private javax.swing.JTextField txtQntdeEstoqueProduto;
    private javax.swing.JTextField txtTelefoneFornecedor;
    // End of variables declaration//GEN-END:variables
}
