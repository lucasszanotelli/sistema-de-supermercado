/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import dao.HibernateUtil;
import view.DialogCadPessoa;
import view.DialogCadFornecedor;
import view.DialogCadProduto;
import view.DialogCadVenda;
import view.DialogPesq;
import view.FramePrincipal;

/**
 *
 * @author 2024122760121
 */
public class ControlerInterface {
    private static ControlerInterface instance;
    FramePrincipal framePrincipal = null;
    DialogCadProduto dlgCadProduto = null;
    DialogCadFornecedor dlgCadFornecedor = null;
    DialogCadPessoa dlgCadCliente = null;
    DialogCadVenda dlgCadVenda = null;
    DialogPesq dlgPescCliente = null;
    DialogCadProduto dlcCadProduto = null;
    
    GerenciadorDominio gerDominio;

    
    private ControlerInterface() throws ClassNotFoundException, SQLException{
        gerDominio = new GerenciadorDominio();
    }

    public static synchronized ControlerInterface getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new ControlerInterface();
        }
        return instance;
    }
       
        // ABRIR JDIALOG
    private JDialog abrirJanela(Frame parent, JDialog dlg, Class classe) {
        if (dlg == null){     
            try {
                dlg = (JDialog) classe.getConstructor(Frame.class, boolean.class).newInstance(parent,true);                                
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                JOptionPane.showMessageDialog(parent, "Erro ao abrir a janela " + classe.getName() + ". " + ex.getMessage() );
            } 
        }               
        dlg.setVisible(true); 
        return dlg;    
    }
    
    public void abrirFramePrincipal(){
        if(framePrincipal == null){
            framePrincipal = new FramePrincipal(this);
        }
        framePrincipal.setVisible(true);
    }
    public void abrirDialogCadProduto(){
        dlgCadProduto = (DialogCadProduto) abrirJanela(framePrincipal, dlgCadProduto, DialogCadProduto.class);
    }
    public void abrirDialogCadFornecedores(){
        dlgCadFornecedor = (DialogCadFornecedor) abrirJanela(framePrincipal, dlgCadFornecedor, DialogCadFornecedor.class);
    }
    public void abrirDialogCadCliente(){
        dlgCadCliente = (DialogCadPessoa) abrirJanela(framePrincipal, dlgCadCliente, DialogCadPessoa.class);
    }
    public void abrirDialogPesqCliente(){
        dlgPescCliente = (DialogPesq) abrirJanela(framePrincipal, dlgPescCliente, DialogPesq.class);
    }
    public void abrirDialogCadVenda(){
        dlgCadVenda = (DialogCadVenda) abrirJanela(framePrincipal, dlgCadVenda, DialogCadVenda.class);
    }
    
    public void carregarCombo(JComboBox combo){
        try {
            List lista = getGerDominio().listarCidade();
        } catch (ClassNotFoundException ex) {
            System.getLogger(ControlerInterface.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SQLException ex) {
            System.getLogger(ControlerInterface.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
       
    public GerenciadorDominio getGerDominio() {
        return gerDominio;
    }
            
    public static void main(String args[]) throws ClassNotFoundException, SQLException {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
        HibernateUtil.getSessionFactory();
        ControlerInterface.getInstance().abrirFramePrincipal();
    }
}
