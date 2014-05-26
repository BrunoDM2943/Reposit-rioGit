package com.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

/**
 * Classe responsável por gerar htmls
 * @author bruno.martins
 * @since 21/05/2014
 * @version 21/05/2014
 */
public class HtmlGenerator {
	
	/**
	 * Retorna um html com layout de uma mensagem  
	 * @param nomeArquivo Nome do arquivo contendo o layout
	 * @return HTML
	 * @author bruno.martins
	 * @since 21/05/2014
	 */
	public String getHtmlFile(String nomeArquivo){
		StringBuilder html = new StringBuilder();
		File arquivo = new File(nomeArquivo + ".html");
		FileReader fileReader = null;
		BufferedReader reader = null;
		
		try{
			fileReader =  new FileReader(arquivo);
			reader = new BufferedReader(fileReader);
			while(reader.ready())
				html.append(reader.readLine()  + "\n");
			reader.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		return html.toString();
	}

	/**
	 * Transforma uma lista de dados em um conjunto de blocos no html
	 * @param listaGeral Lista contendo os dados
	 * @return Html contendo os blocos
	 */
	public String gerarHtml(ListaEncadeada<Object> listaGeral) {
		StringBuilder html = new StringBuilder();
		html.append("<html> \n");
		html.append("<body> \n");
		while(listaGeral.hasNext()){
			html.append("<font size=\"5\"><center>" + listaGeral.next().toString() + "</center></font>" + "\n");
			html.append("<font size=\"5\"><center>" + "-------------------"+ "</center></font>" + "\n");
		}
		
		html.append("</body> \n");
		html.append("</html> \n");
		System.out.println(html.toString());
		
		return html.toString();
		
	}
}
