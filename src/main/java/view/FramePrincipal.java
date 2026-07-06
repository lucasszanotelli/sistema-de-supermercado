/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controler.ControlerInterface;

public class FramePrincipal extends javax.swing.JFrame {
    private ControlerInterface controlInterface;
    
    public FramePrincipal(ControlerInterface newControlInterface) {
        controlInterface = newControlInterface;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        menuCadProduto = new javax.swing.JMenuItem();
        menuCadFornecedores = new javax.swing.JMenuItem();
        menuCadPessoa = new javax.swing.JMenuItem();
        menuRelatorio = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuListagem = new javax.swing.JMenu();
        listagemCliente = new javax.swing.JMenuItem();
        listagemProduto = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuCadastro.setText("Cadastro");

        menuCadProduto.setText("Produto");
        menuCadProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadProdutoActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadProduto);

        menuCadFornecedores.setText("Fornecedores");
        menuCadFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadFornecedoresActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadFornecedores);

        menuCadPessoa.setText("Cliente");
        menuCadPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadPessoaActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadPessoa);

        menuBar1.add(menuCadastro);

        menuRelatorio.setText("Venda");

        jMenuItem1.setText("Vender");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuRelatorio.add(jMenuItem1);

        menuBar1.add(menuRelatorio);

        menuListagem.setText("Listagem");

        listagemCliente.setLabel("Cliente");
        listagemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listagemClienteActionPerformed(evt);
            }
        });
        menuListagem.add(listagemCliente);

        listagemProduto.setText("Produto");
        listagemProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listagemProdutoActionPerformed(evt);
            }
        });
        menuListagem.add(listagemProduto);

        menuBar1.add(menuListagem);

        setJMenuBar(menuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuCadProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadProdutoActionPerformed
        controlInterface.abrirDialogCadProduto();
    }//GEN-LAST:event_menuCadProdutoActionPerformed

    private void menuCadFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadFornecedoresActionPerformed
        controlInterface.abrirDialogCadFornecedores();
    }//GEN-LAST:event_menuCadFornecedoresActionPerformed

    private void menuCadPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadPessoaActionPerformed
        controlInterface.abrirDialogCadCliente();
    }//GEN-LAST:event_menuCadPessoaActionPerformed

    private void listagemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listagemClienteActionPerformed
        controlInterface.abrirDialogPesqCliente();
    }//GEN-LAST:event_listagemClienteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        controlInterface.abrirDialogCadVenda();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void listagemProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listagemProdutoActionPerformed
        controlInterface.abrirDialogListProduto();
    }//GEN-LAST:event_listagemProdutoActionPerformed
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem listagemCliente;
    private javax.swing.JMenuItem listagemProduto;
    private javax.swing.JMenuBar menuBar1;
    private javax.swing.JMenuItem menuCadFornecedores;
    private javax.swing.JMenuItem menuCadPessoa;
    private javax.swing.JMenuItem menuCadProduto;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuListagem;
    private javax.swing.JMenu menuRelatorio;
    // End of variables declaration//GEN-END:variables
}
