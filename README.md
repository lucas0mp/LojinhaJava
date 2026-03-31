# Atividade Lojinha - Simulação de Sistema Monolítico

## Estrutura do Projeto
O projeto está organizado em pacotes lógicos para separar as responsabilidades, seguindo os princípios de Orientação a Objetos:
* `model/`: Contém as classes de entidade e estrutura de dados (`Cliente`, `Produto`, `Pedido`, `ItemPedido`).
* `service/`: Contém as regras de negócio e integrações (`LojinhaService`, `PagamentoGateway`).
* `Main.java`: Classe principal responsável por instanciar os dados estáticos e simular o fluxo de execução via console.

## Principais Decisões Arquiteturais
* **Arquitetura Monolítica:** Toda a lógica de cliente, produtos, fechamento de carrinho e comunicação externa está encapsulada em um único projeto executável. Não há divisão de microsserviços.
* **Simulação em Memória:** Para simplificar a simulação da API, o banco de dados foi substituído por instâncias estáticas (Listas na classe `LojinhaService`), permitindo testar o fluxo sem a necessidade de um SGBD real.

## Padrão Singleton
* **Onde foi aplicado:** Na classe `PagamentoGateway.java` (pacote `service`).
* **Como foi aplicado:** O construtor da classe foi definido como `private` para impedir que outras classes criem novas instâncias (usando `new`). Foi criado um atributo estático privado e um método público estático `getInstance()` que verifica se a instância já existe antes de criá-la ou retorná-la.
* **Justificativa:** A comunicação com o sistema externo de pagamento é centralizada. Isso garante economia de recursos, controle unificado das transações (evitando múltiplas chamadas concorrentes desnecessárias) e simula uma conexão de gateway única e segura para todo o ciclo de vida do backend da loja.
