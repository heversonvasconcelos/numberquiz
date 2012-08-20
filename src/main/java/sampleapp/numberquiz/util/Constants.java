package sampleapp.numberquiz.util;

/**
 * Classe de definição de constantes utilizadas em diversas partes da aplicação.
 * 
 * @author heverson.vasconcelos
 */
public class Constants {

	/*
	 * Constantes com o endereço das páginas (relativos a raiz)
	 */
	/**
	 * Armazena o endereço da página de login (página inicial).
	 */
	public static final String PAGE_INDEX = "/index.xhtml";
	/**
	 * Armazena o endereço da página onde o quiz sera apresentado ao usuario.
	 */
	public static final String PAGE_NUMBERQUIZ = "/numberquiz.xhtml";
	/**
	 * Armazena o endereço da página onde serão apresentadas ao usuário a sua
	 * pontuação final e as melhores pontuações entre os jogos já realizados.
	 */
	public static final String PAGE_STATS = "/stats.xhtml";
	/**
	 * Armazena o endereço da página que irá apresentar ao usuário uma mensagem
	 * alertando-o que não está devidamente autenticado e logado.
	 */
	public static final String PAGE_USER_NOT_LOGGED = "/usernotlogged.xhtml";

	/*
	 * Mensagens de erro
	 */
	public static final String MSG_INVALID_USER = "Usuário inválido";
	public static final String MSG_INVALID_PASSWORD = "Senha inválida";
	public static final String MSG_USER_ALREADY_EXISTS = "Usuário já foi cadastrado";

	/*
	 * Algumas constantes de configurações
	 */
	public static final String LOGGED_USER = "loggedUser";
	/**
	 * Armazena a quantidade máxima de itens que a lista com as melhores
	 * pontuações poderá ter.
	 */
	public static final int CONFIG_NUMBER_OF_SCORES = 5;
}
