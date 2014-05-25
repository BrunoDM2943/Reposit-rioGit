package alocacaoDinamica.tabelaEspalhamento;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

public class Teste {

	public static void main(String[] args) {
		TabelaEspalhamento<String, Integer> parametros = new TabelaEspalhamento<String, Integer>();
		parametros.put("A", 1);
		parametros.put("B", 2);
		parametros.put("C", 3);
		int rem = parametros.remove("B");
		System.out.println("Removido: " + rem);
		parametros.put("B", 19);
		System.out.println(parametros.get("B"));
	}

}
