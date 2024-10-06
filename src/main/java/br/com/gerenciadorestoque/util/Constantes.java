package br.com.gerenciadorestoque.util;

public class Constantes {

    private Constantes(){}

    public static final String MENSAGEM_ERRO_LOGGER_EXCEPTION = "Exception: [ %s ] Mensagem: %s";
    public static final String MENSAGEM_ERRO_TRANSACAO_DB = "Erro ao efetuar operação na base de dados";
    public static final String MENSAGEM_ERRO_CAMPO_VAZIO = "Campo: %s deve ser preenchido";
    public static final String MENSAGEM_ERRO_DESCONHECIDO = "HOUVE UM ERRO INESPERADO";
    public static final String COR_MENSAGEM_ERRO = "danger";
    public static final String COR_MENSAGEM_SUCESSO = "success";
    public static final String KEY_MSG_RETORNO = "ret";
    public static final String KEY_COR_MENSAGEM_RETORNO = "cor_msg_retorno";

    // upload de arquivos
    public static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
    public static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    public static final String ERRO_PROCESSAMENTO_ARQUIVO = "Houve um erro ao processar o arquivo de imagem";

}
