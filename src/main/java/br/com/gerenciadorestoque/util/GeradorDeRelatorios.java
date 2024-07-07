package br.com.gerenciadorestoque.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

public class GeradorDeRelatorios {

    private Connection conexao;

    public GeradorDeRelatorios(Connection conexao) {
        this.conexao = conexao;
    }

    public void geraPdf(String jrxml, Map<String, Object> parametros, OutputStream saida) {
        try {
            // compila jrxml em memoria
            JasperReport jasper = JasperCompileManager.compileReport(jrxml);

            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, this.conexao);

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
