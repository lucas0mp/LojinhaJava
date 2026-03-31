import model.Cliente;
import service.LojinhaService;

public class Main {
    public static void main(String[] args) {
        LojinhaService lojinha = new LojinhaService();

        // 1. Identificação de cliente (criado estaticamente)
        Cliente cliente1 = new Cliente(101, "Lucas");
        Cliente cliente2 = new Cliente(102, "Maria");

        // 2. Listagem de produtos
        lojinha.listarProdutos();

        // 3 e 4. Criação de pedido e Processamento de pagamento
        // Cliente 1 compra 1 Notebook e 2 Mouses
        int[] produtosCliente1 = {1, 2};
        int[] quantidadesCliente1 = {1, 2};
        lojinha.realizarCompra(cliente1, produtosCliente1, quantidadesCliente1);

        System.out.println("\n=========================================\n");

        // Cliente 2 compra 1 Teclado
        int[] produtosCliente2 = {3};
        int[] quantidadesCliente2 = {1};
        lojinha.realizarCompra(cliente2, produtosCliente2, quantidadesCliente2);
    }
}