package produto;

public interface RepositorioProduto<P extends Produto> {
    
    public boolean existe(int codigo);

    public void inserir(P produto);

    public void atualizar(P produto);

    public void remover(int codigo);

    public P procurar(int codigo);
}
