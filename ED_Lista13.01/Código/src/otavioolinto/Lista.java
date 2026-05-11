package otavioolinto;

public class Lista<T> implements ILista<T>{
	
	// Ponteiro de referência da Lista 
	No<T> primeiro;
	
	// Variável para verificação da classe que a Lista está trabalhando
	private Class<T> type;
	
	public Lista(Class<T> tipo) {
		
		primeiro = null;
		
		this.type = tipo;
	}

	public Class<T> getType(){
		
		return this.type;
	}

	@Override
	// Método Responsável por limpar a Lista
	// Método destrutivo, uma vez realizado, perde-se todos os itens salvos na lista.
	public void clear() {
		
		primeiro = null;
	}

	@Override
	// Método de controle para verificar se a Lista está vazia
	public boolean isEmpty() {
		
		return (primeiro==null) ? true:false;
	}

	@Override
	// Método Responsável por mensurar a quantidade de itens na Lista
	public int size() {
		
		int tamanho = 0;
		
		if(!isEmpty()) {
			
			No<T> noAux = primeiro;
			
			while(noAux!=null) {
				
				tamanho++;
				noAux = noAux.proximo;
			}
		}
		
		return tamanho;
	}
	
	// Método Responsável por retornar um nó específico da Lista
	private No<T> getNo(int posicao) throws Exception{
		
		if(isEmpty()) {
			throw new Exception("Lista Vazia!\n");
		}
		
		int tamanho = size();
		
		if(posicao<0 || posicao>tamanho-1) {
			throw new Exception("Posição Inválida!\n");
		}
		
		No<T> noAux = primeiro;
		
		for(int cont=0; cont<posicao; cont++) {
			noAux = noAux.proximo;
		}
		
		return noAux;
	}

	@Override
	// Método Responsável por adicionar um item como o primeiro Lista
	public void addFirst(T valor) {
		
		No<T> elemento = new No<>();
		elemento.dado = valor;
		
		elemento.proximo = primeiro;
		primeiro = elemento;
	}

	@Override
	// Método Responsável por adicionar um item como o último da Lista
	public void addLast(T valor) throws Exception {
		
		if(isEmpty()){
			
			addFirst(valor);
			
		}else {
			
			int tamanho = size();
			
			No<T> elemento = new No<>();
			elemento.dado = valor;
			
			No<T> ultimo = getNo(tamanho-1);
			ultimo.proximo = elemento;
		}
		
	}

	@Override
	// Método Responsável por adicionar um item em uma posição da Lista
	// O índice da Lista começa com 0
	public void add(T valor, int posicao) throws Exception {
		
		int tamanho = size();
		
		if(posicao<0 || posicao>tamanho) {
			throw new Exception("Posição Inválida!\n");
		}
		
		if(posicao==0) {
			
			addFirst(valor);
			
		}else if(posicao==tamanho) {
			
			addLast(valor);
			
		}else {
			
			No<T> elemento = new No<>();
			elemento.dado = valor;
			
			No<T> anterior = getNo(posicao-1);
			elemento.proximo = anterior.proximo;
			anterior.proximo = elemento;
		}
	}

	@Override
	// Método Responsável por remover o primeiro item da Lista
	// Método destrutivo, uma vez usado, perde-se o valor
	public void removeFirst() throws Exception {
		
		if(isEmpty()) {
			throw new Exception("Lista Vazia!\n");
		}
		
		primeiro = primeiro.proximo;
	}

	@Override
	// Método Responsável por remover o último item da Lista
	// Método destrutivo, uma vez usado, perde-se o valor
	public void removeLast() throws Exception {
		
		if(isEmpty()) {
			throw new Exception("Lista Vazia!\n");
		}	
		
		int tamanho = size();
		
		if(tamanho==1) {
			
			removeFirst();
			
		}else {
			
			No<T> penultimo = getNo(tamanho-2);
			penultimo.proximo = null;
		}
		
	}

	@Override
	// Método Responsável por remover um item da Lista de uma posição específica
	// Método destrutivo, uma vez usado, perde-se o valor
	// O índice da Lista começa em 0
	public void remove(int posicao) throws Exception {
		
		if(isEmpty()) {
			throw new Exception("Lista Vazia!\n");
		}	
		
		int tamanho = size();
		
		if(posicao<0 || posicao>tamanho-1) {
			throw new Exception("Posição Inválida!\n");
		}
		
		if(posicao==0) {
			
			removeFirst();
			
		}else if(posicao==tamanho-1) {
			
			removeLast();
			
		}else {
			
			No<T> anterior = getNo(posicao-1);
			No<T> atual = anterior.proximo;
			
			anterior.proximo = atual.proximo;
		}
		
	}

	@Override
	// Método Responsável por retornar o dado de um Nó da Lista
	public T get(int posicao) throws Exception {
		
		No<T> noAux = getNo(posicao);
		
		return noAux.dado;
	}

}
