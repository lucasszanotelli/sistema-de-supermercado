/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Funcionario;
import model.ItemVenda;
import model.Produto;
import model.Venda;

/**
 *
 * @author lucas
 */
public class DialogCadVenda extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DialogCadVenda.class.getName());
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final VendaDAO vendaDAO = new VendaDAO();
    private final NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

    /**
     * Creates new form DialogCadVenda
     */
    public DialogCadVenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarVenda();
    }

    private void configurarVenda() {
        configurarTabelaResumo();
        txtSubtotal.setEditable(false);
        txtSubtotal.setText(moeda.format(0));
        carregarDadosBanco();
        jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jList1.addListSelectionListener(evt -> {
            if (!evt.getValueIsAdjusting()) {
                atualizarResumoProdutos();
            }
        });
        btnSalvar.addActionListener(evt -> salvarVenda());
    }

    private void carregarDadosBanco() {
        try {
            DefaultComboBoxModel<Cliente> clientes = new DefaultComboBoxModel<>();
            for (Cliente cliente : clienteDAO.listar()) {
                clientes.addElement(cliente);
            }
            jComboBox1.setModel(clientes);

            DefaultComboBoxModel<Funcionario> funcionarios = new DefaultComboBoxModel<>();
            for (Funcionario funcionario : funcionarioDAO.listar()) {
                funcionarios.addElement(funcionario);
            }
            jComboBox2.setModel(funcionarios);

            DefaultListModel<Produto> produtos = new DefaultListModel<>();
            for (Produto produto : produtoDAO.listar()) {
                produtos.addElement(produto);
            }
            jList1.setModel(produtos);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this,
                    "Nao foi possivel carregar os dados do banco.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void configurarTabelaResumo() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Produto", "Preco"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(modelo);
    }

    private void atualizarResumoProdutos() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);

        double subtotal = 0;
        List<Produto> produtosSelecionados = jList1.getSelectedValuesList();
        for (Produto produto : produtosSelecionados) {
            subtotal += produto.getPreco();
            modelo.addRow(new Object[]{produto.getNomeProduto(), moeda.format(produto.getPreco())});
        }

        txtSubtotal.setText(moeda.format(subtotal));
    }

    private void salvarVenda() {
        Cliente cliente = getItemSelecionado(jComboBox1, Cliente.class);
        Funcionario funcionario = getItemSelecionado(jComboBox2, Funcionario.class);
        List<Produto> produtos = jList1.getSelectedValuesList();

        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente.");
            return;
        }
        if (funcionario == null) {
            JOptionPane.showMessageDialog(this, "Selecione um funcionario.");
            return;
        }
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione ao menos um produto.");
            return;
        }

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setDataVenda(LocalDateTime.now());

        double total = 0;
        for (Produto produto : produtos) {
            ItemVenda item = new ItemVenda();
            item.setProduto(produto);
            item.setQuantidade(1);
            item.setValorUnitario(produto.getPreco());
            item.setValorTotal(produto.getPreco());
            venda.addItem(item);
            total += produto.getPreco();
        }
        venda.setValorTotal(total);

        try {
            vendaDAO.salvar(venda);
            JOptionPane.showMessageDialog(this, "Venda salva com sucesso.");
            limparVenda();
            carregarDadosBanco();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this,
                    "Nao foi possivel salvar a venda.\n" + getMensagemErro(ex),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private String getMensagemErro(Throwable ex) {
        Throwable causa = ex;
        while (causa.getCause() != null) {
            causa = causa.getCause();
        }
        String mensagem = causa.getMessage();
        return mensagem == null || mensagem.isBlank() ? ex.getClass().getSimpleName() : mensagem;
    }

    private <T> T getItemSelecionado(javax.swing.JComboBox<?> combo, Class<T> tipo) {
        Object item = combo.getSelectedItem();
        return tipo.isInstance(item) ? tipo.cast(item) : null;
    }

    private void limparVenda() {
        jList1.clearSelection();
        configurarTabelaResumo();
        txtSubtotal.setText(moeda.format(0));
        if (jComboBox1.getItemCount() > 0) {
            jComboBox1.setSelectedIndex(0);
        }
        if (jComboBox2.getItemCount() > 0) {
            jComboBox2.setSelectedIndex(0);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        labelSubtotal = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JFormattedTextField();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Venda"));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Cliente");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>());

        jLabel2.setText("Funcionário");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jList1.setModel(new javax.swing.DefaultListModel<>());
        jScrollPane1.setViewportView(jList1);

        labelSubtotal.setText("Subtotal:");

        txtSubtotal.setText("R$");
        txtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Preço"
            }
        ));
        jTable1.setToolTipText("");
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalvar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelSubtotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSubtotal)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Cliente> jComboBox1;
    private javax.swing.JComboBox<Funcionario> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<Produto> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelSubtotal;
    private javax.swing.JFormattedTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables
}
