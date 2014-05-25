package alocacaoDinamica.tabelaEspalhamento;

public class No<K, V> {
	K key;
	V value;
	
	public No(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	@Override
	public boolean equals(Object e){
		No<K, V> no = (No) e;
		return (this.key.equals(no.key));
	}
}
