package vetor;

import java.util.Comparator;

import produto.Produto;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T> {

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator comparadorMaximo;
	private Comparator comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this. arrayInterno = (T[]) new Object[tamanho];
		this.tamanho = tamanho;
		this.indice = -1;
	}

	private int procurarIndice(T o) {
		for (int i = 0; i <this.tamanho; i++) {
			if (this.arrayInterno[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		this.indice++;
		this.arrayInterno[this.indice] = o;
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		int pos = procurarIndice(o);

		if (pos != -1) {
			T x = this.arrayInterno[pos];
			this.arrayInterno[pos] = this.arrayInterno[this.indice]; //Para nao deixar "buracos", coloco o ultimo add na posicao do objeto removido no vetor.
			this.arrayInterno[this.indice] = null;
			this.indice--;
			return x;
		} else {
			return null;
		}

	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		int pos = procurarIndice(o);
		if(pos != -1) return this.arrayInterno[pos];
		else return null;
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		return this.indice == -1;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		return (this.indice + 1) == this.tamanho;
	}

}
