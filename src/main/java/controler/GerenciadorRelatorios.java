package controler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class GerenciadorRelatorios {

    public void relComLista(List lista, String nomeRel) {
        try {
            InputStream rel = getClass().getResourceAsStream("/relatorios/" + nomeRel);
            if (rel == null) {
                JOptionPane.showMessageDialog(null, "Relatorio nao encontrado: " + nomeRel);
                return;
            }

            Map parametros = new HashMap();
            JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(lista);
            JasperPrint print;

            if (nomeRel.toLowerCase().endsWith(".jrxml")) {
                JasperReport jasperReport = JasperCompileManager.compileReport(rel);
                print = JasperFillManager.fillReport(jasperReport, parametros, dados);
            } else {
                print = JasperFillManager.fillReport(rel, parametros, dados);
            }

            if (print.getPages().size() > 0) {
                JasperViewer jrViewer = new JasperViewer(print, true);
                JDialog viewer = new JDialog(new javax.swing.JFrame(), "Visualizacao do Relatorio", true);
                viewer.setSize(800, 600);
                viewer.setLocationRelativeTo(null);
                viewer.getContentPane().add(jrViewer.getContentPane());
                viewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Relatorio vazio.");
            }
        } catch (JRException erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO ao abrir relatorio. " + mensagemErro(erro));
        }
    }

    private String mensagemErro(Throwable erro) {
        Throwable causa = erro;
        while (causa.getCause() != null) {
            causa = causa.getCause();
        }

        if (causa.getMessage() != null && !causa.getMessage().isBlank()) {
            return causa.getMessage();
        }

        return erro.getMessage();
    }
}
