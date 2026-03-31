package service;

/**
 * Padrão Singleton aplicado ao Gateway de Pagamento.
 * * JUSTIFICATIVA: 
 * O acesso a um sistema externo de pagamento consome recursos de rede e requer
 * controle estrito de concorrência e configurações de segurança (ex: tokens, chaves de API).
 * Utilizar o padrão Singleton garante que toda a aplicação utilize uma única instância 
 * de conexão para processar os pagamentos, evitando sobrecarga de múltiplas instâncias 
 * desnecessárias, centralizando o controle de logs/falhas e prevenindo comportamentos 
 * inesperados na comunicação com a API externa.
 */
public class PagamentoGateway {

    private static PagamentoGateway instancia;

    private PagamentoGateway() {
        System.out.println("[Sistema] Inicializando conexão única com o Gateway de Pagamento externo...");
    }

    public static PagamentoGateway getInstance() {
        if (instancia == null) {
            instancia = new PagamentoGateway();
        }
        return instancia;
    }

    public boolean processarPagamento(double valor) {
        System.out.println("[Gateway] Processando transação no valor de R$ " + valor);
        // Simulação de tempo de resposta da rede
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        
        // Simula aprovação de pagamento (sempre retorna true na simulação)
        System.out.println("[Gateway] Pagamento aprovado com sucesso!");
        return true;
    }
}