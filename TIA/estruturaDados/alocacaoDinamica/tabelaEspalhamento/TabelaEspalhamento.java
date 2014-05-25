package alocacaoDinamica.tabelaEspalhamento;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

public class TabelaEspalhamento <K, V> {
	
	No<K, V> no;
	private ListaEncadeada<No> lista[];
	
	public TabelaEspalhamento(){
		lista = new ListaEncadeada[26];
	}
	
	public void put(K key, V value){
		char letra = key.toString().charAt(0);
		int index = hash(letra);
		no = new No<K, V>(key, value);
		if(lista[index] == null)
			lista[index] = new ListaEncadeada<No>();
		lista[index].addFim(no);
	}
	
	
	public V remove (K key){
        char letra=key.toString().charAt(0);
        int index=hash(letra);
        no = new No(key, null);
        if (lista[index]==null){
            System.out.println("Não Há Lista para Este Contato");
            return null;
        }
        return (V) lista[index].remove(no).value;     
    }

	private int hash(char letra) {
		int v = Character.toLowerCase(letra) - 97;
		return v;
	}
			
	public V get(K key){
		char letra=key.toString().charAt(0);
        int index=hash(letra);
        no = new No(key, null);
        if (lista[index]==null){
            System.out.println("Não Há Lista para Este Contato");
            return null;
        }
        return (V) lista[index].get(no).value;  
		
	}
}
