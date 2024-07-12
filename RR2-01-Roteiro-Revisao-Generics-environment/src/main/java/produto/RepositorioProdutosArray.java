package produto;

public abstract class RepositorioProdutosArray<P extends Produto> implements RepositorioProduto<P> {
    
    /**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private P[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutosArray(int size) {
		this.produtos = (P[]) new Produto[size];
	}


	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {

		for (int i = 0; i < this.produtos.length; i++) {
			if (this.produtos[i].getCodigo() == codigo) return i;
		}

		return -1;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {

		if (procurarIndice(codigo) == -1) return false;
		return true; 
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(P produto) {

		this.index++;
		this.produtos[this.index] = produto;
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(P produto) {

		boolean achou = false;
		for (int i = 0; i < this.produtos.length; i++) {
			if (this.produtos[i].equals(produto)) {
				this.produtos[i] = produto;
				achou = true;
				break;
			}
		}

		if (!achou) new IllegalArgumentException("Produto não existente");
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {

		int pos = procurarIndice(codigo);
		if (pos == -1) new IllegalArgumentException("Produto não existente");
		
		this.produtos[pos] = null;
		for(int i = pos; i < this.produtos.length; i++) {
			this.produtos[pos] = this.produtos[pos+1];
			this.produtos[pos+1] = this.produtos[pos];
		}

		this.index--;
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public P procurar(int codigo) {

		int i = this.procurarIndice(codigo);
		if (i != -1) {
			return this.produtos[i];
		} else {
			return null;
		}
	}
}