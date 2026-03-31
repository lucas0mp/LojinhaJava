package service;

import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class LojinhaService {
    private List<Produto> catalogo;
    private int contadorPedidos = 1;

    public LojinhaService() {
        this.catalogo = new ArrayList<>();
        inicializarCatalogo();
    }

    private void inicializarCatalogo() {
        catalogo.add(new Produto(1, "Notebook", 3500.00, 10));
        catalogo.add(new Produto(2, "Mouse", 150.00, 50));
        catalogo.add(new Produto(3, "Teclado", 250.00, 30));
    }

    public void listarProdutos() {
        System.out.println("--- Catálogo de Produtos ---");
        for (Produto p : catalogo) {
            System.out.println(p.getId() + " - " + p.getNome() + " | R$ " + p.getPreco() + " | Estoque: " + p.getEstoque());
        }
        System.out.println("----------------------------");
    }

    public Produto buscarProduto(int id) {
        for (Produto p : catalogo) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public void realizarCompra(Cliente cliente, int[] idsProdutos, int[] quantidades) {
        System.out.println("\nIniciando compra para o cliente: " + cliente.getNome());
        Pedido pedido = new Pedido(contadorPedidos++, cliente);

        for (int i = 0; i < idsProdutos.length; i++) {
            Produto produto = buscarProduto(idsProdutos[i]);
            if (produto != null && produto.getEstoque() >= quantidades[i]) {
                pedido.adicionarItem(new ItemPedido(produto, quantidades[i]));
                produto.reduzirEstoque(quantidades[i]);
            } else {
                System.out.println("[Erro] Produto ID " + idsProdutos[i] + " indisponível ou estoque insuficiente.");
                return;
            }
        }

        double total = pedido.calcularTotal();
        System.out.println("Pedido criado. Total a pagar: R$ " + total);

        // Invoca o Singleton para o pagamento
        PagamentoGateway gateway = PagamentoGateway.getInstance();
        boolean sucesso = gateway.processarPagamento(total);

        if (sucesso) {
            pedido.setStatus("Pago");
            System.out.println("Status do Pedido #" + pedido.getId() + ": " + pedido.getStatus());
        } else {
            pedido.setStatus("Falha no Pagamento");
            System.out.println("Status do Pedido #" + pedido.getId() + ": " + pedido.getStatus());
        }
    }
}