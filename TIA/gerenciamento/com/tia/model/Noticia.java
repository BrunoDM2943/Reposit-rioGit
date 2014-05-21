package com.tia.model;

import com.framework.Diretorios;
import com.framework.SistemaArquivos;

/**
 * Entidade responsável por noticias
 * @author bruno.martins
 * @since 21/05/2014
 * @version 21/05/2014
 */
public class Noticia {
	private int idNoticia;
	private String titulo;
	private String texto;
	
	/**
	 * Construtor da classe Noticia
	 */
	public Noticia(){}
	
	/**
	 * Retorna o id de uma noticia
	 * @return IdNoticia
	 */
	public int getIdNoticia() {
		return idNoticia;
	}
	/**
	 * Gera uma chave primária para uma noticia
	 */
	public void setIdNoticia(){
		this.idNoticia = SistemaArquivos.geraChavePrimaria(Diretorios.NOTICIAS.getAutoIncremento());
	}
	/**
	 * Atribui uma chave primária para uma notícia
	 * @param idNoticia novo id da notícia
	 */
	public void setIdNoticia(int idNoticia) {
		this.idNoticia = idNoticia;
	}
	/**
	 * Retorna o título de uma notícia
	 * @return Titulo da notícia
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * Atribui um título a uma notícia
	 * @param titulo Novo título da notícia
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * Retorna o texto de uma notícia
	 * @return Texto da notícia
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * Atribui um texto para uma notícia
	 * @param texto Novo texto da notícia
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	/**
	 * Verifica se uma nova notícia é igual a atual
	 * @param e Notícia nova
	 * @return True, se o título das notícias forem iguais; False, senão
	 */
	@Override
	public boolean equals(Object e){
		Noticia nova = (Noticia) e;
		return nova.getTitulo().equalsIgnoreCase(this.titulo);			
	}
	
	/**
	 * Retorna uma representação em texto do objeto
	 * @author bruno.martins
	 * @since 21/05/2014
	 */
	@Override
	public String toString(){
		return this.titulo;
	}
}
