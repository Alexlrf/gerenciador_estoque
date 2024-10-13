package br.com.gerenciadorestoque.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import br.com.gerenciadorestoque.infra.GenericException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import static br.com.gerenciadorestoque.util.Constantes.*;

public class ParametrosRequestMultipart {

    private final Logger logger = Logger.getLogger(ParametrosRequestMultipart.class);

    private final HttpServletRequest request;

    public ParametrosRequestMultipart(HttpServletRequest request) {
        this.request = request;
    }

    public Entry<Map<String, String>, byte[]> obterValoresRequestMultipart() {
        Map<String, String> camposSimples = new HashMap<>();
        byte[] arquivo = null;
        if (this.confirmarRequestMultpartContent()) {
            List<FileItem> fileItens = this.obterListaItensRequest();

            if (this.confirmarListaFileItensValida(fileItens)) {
                for (FileItem item : fileItens) {
                    if (item.isFormField()) {
                        String nomeCampo = item.getFieldName();
                        String valorCampo = item.getString();
                        camposSimples.put(nomeCampo, valorCampo);
                        continue;
                    }
                    if (camposSimples.get("temArquivo").equalsIgnoreCase("SIM")) {
                        Map.Entry<File, File> retornoExtracaoArquivo = this.obterArquivoRequest(item);
                        arquivo = this.converterFileToByteArray(retornoExtracaoArquivo.getKey());
                        this.deletarArquivoTemporario(retornoExtracaoArquivo.getValue());
                    }
                }
            }
        }
        return new SimpleEntry<>(camposSimples, arquivo);
    }

    private boolean alteracaoComMudancaArquivo(Map<String, String> camposSimples, FileItem item) {
        return !camposSimples.get("acao").equalsIgnoreCase("alterar")
                || !item.getName().isBlank();
    }

    private Entry<File, File> obterArquivoRequest(FileItem item) {
        File fileTemp = null;
        File storeFile = null;
        try {
            String fileName = new File(item.getName()).getName();
            fileTemp = File.createTempFile(fileName, ".png");
            String tempPath = fileTemp.getAbsolutePath();
            storeFile = new File(tempPath);
            item.write(storeFile);
        }  catch (Exception e) {
            logger.error(FuncoesUtil.lancarLogErro(e));
            this.lancarErro(ERRO_PROCESSAMENTO_ARQUIVO);
        }
        return new SimpleEntry<>(storeFile, fileTemp);
    }

    private void deletarArquivoTemporario(File fileTemp) {
        try {
            if (fileTemp.exists()) {
               Files.delete(Path.of(fileTemp.getAbsolutePath()));
            }
        } catch(IOException e) {
            logger.error(FuncoesUtil.lancarLogErro(e));
            this.lancarErro(ERRO_PROCESSAMENTO_ARQUIVO);
        }
    }

    private byte[] converterFileToByteArray(File storeFile) {
        byte[] arq = new byte[(int) storeFile.length()];
        try (FileInputStream fis = new FileInputStream(storeFile)) {
            int teste = fis.read(arq);
            if (teste < 1) {
                logger.error(FuncoesUtil.lancarLogErro(new GenericException("Erro ao ler FileInputStream")));
                this.lancarErro(ERRO_PROCESSAMENTO_ARQUIVO);
            }
        } catch (IOException e) {
            logger.error(FuncoesUtil.lancarLogErro(e));
            this.lancarErro(ERRO_PROCESSAMENTO_ARQUIVO);
        }
        return arq;
    }

    private boolean confirmarListaFileItensValida(List<FileItem> fileItens) {
        return null != fileItens && !fileItens.isEmpty();
    }

    private List<FileItem> obterListaItensRequest() {
        List<FileItem> fileItens = null;
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            fileItens = upload.parseRequest(this.request);
        } catch (FileUploadException e) {
            logger.error(FuncoesUtil.lancarLogErro(e));
            this.lancarErro("Erro ao extrair o conteúdo da transação");
        } catch (Exception e) {
            logger.error(FuncoesUtil.lancarLogErro(e));
            this.lancarErro("Erro ao extrair as informações da transação");
        }
        return fileItens;
    }

    private boolean confirmarRequestMultpartContent() {
        if (!ServletFileUpload.isMultipartContent(this.request)) {
            logger.info("Request recebida não é do tipo Multipart");
            return false;
        }
        return true;
    }

    private void lancarErro(String mensagemErro) {
        RequestUtil.inputRetornoErro(this.request, mensagemErro);
    }

}