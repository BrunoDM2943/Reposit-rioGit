package com.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

public class HtmlGenerator {
	
	public String getHtmlFile(String nomeArquivo){
		StringBuilder html = new StringBuilder();
		File arquivo = new File(Diretorios.LAYOUT.toString() + "\\" + nomeArquivo + ".html");
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
