/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import view.DialogCadCliente;
import view.DialogCadFornecedor;
import view.DialogCadProduto;
import view.FramePrincipal;

/**
 *
 * @author 2024122760121
 */
public class ControlerInterface {
    FramePrincipal framePrincipal = null;
    DialogCadProduto dlgCadProduto = null;
    DialogCadFornecedor dlgCadFornecedor = null;
    DialogCadCliente dlgCadCliente = null;
    
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
        abrirJanela(framePrincipal, dlgCadProduto, DialogCadProduto.class);
    }
    public void abrirDialogCadFornecedores(){
        abrirJanela(framePrincipal, dlgCadFornecedor, DialogCadFornecedor.class);           
    }
    public void abrirDialogCadCliente(){
        abrirJanela(framePrincipal, dlgCadCliente, DialogCadCliente.class);           
    }
    
    
    
    
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
        //</editor-fold>

        /* Create and display the form */
        ControlerInterface controlerInterface = new ControlerInterface();
        controlerInterface.abrirFramePrincipal();
    }
}
