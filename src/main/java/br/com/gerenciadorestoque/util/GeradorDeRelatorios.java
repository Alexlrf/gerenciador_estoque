package br.com.gerenciadorestoque.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

public class GeradorDeRelatorios {

    private Connection conexao;

    public GeradorDeRelatorios(Connection conexao) {
        this.conexao = conexao;
    }

    public void geraPdf(String relatorio, Map<String, Object> parametros, OutputStream saida) {
        try {
            // compila jrxml em memoria
//            JasperReport jasper = JasperCompileManager.compileReport(jrxml);

//            JRSaver.saveObject(jasper, "pessoa.jasper");

            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(relatorio, parametros, this.conexao);

            // exporta para pdf
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
            exporter.exportReport();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erro ao gerar relatório" + e.getMessage());
        }
    }
}
