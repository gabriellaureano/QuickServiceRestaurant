# ⚡ Quick Service Restaurant API

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot&logoColor=6DB33F)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Flyway](https://img.shields.io/badge/Flyway-CC0202?style=for-the-badge&logo=redhat&logoColor=white)](https://flywaydb.org/)

Uma API robusta, rápida e totalmente integrada para gerenciamento de atendimentos, controle de mesas, cardápio digital, realização de pedidos e integração em tempo real com o fluxo de preparação da cozinha.

---

## 📌 Status do Desenvolvimento

- [x] **Fluxo Core Completo:** Integração ponta a ponta desde a criação do produto ao fechamento de caixa.
- [ ] **Segurança com Spring Security:** Controle de acessos refinado para perfis `ADMIN` e `USER` *(Próximo passo!)* 🚀

---

## 🏃‍♂️ Fluxo de Uso da API (Guia Passo a Passo)

Abaixo, você confere a sequência exata para simular o funcionamento de um restaurante. Siga os passos utilizando o endereço base:  
`📍 http://localhost:8080`

---

### 1️⃣ Criar Produto (Cardápio)
> Antes de abrir o restaurante, precisamos cadastrar itens no nosso cardápio. Esta rota salva os produtos no banco de dados.

*   **Rota:** `POST /restaurant/produto/criar`
*   **Header:** `Content-Type: application/json`
*   **Request Body (JSON):**
    ```json
    {
      "nome": "Feijoada Completa",
      "descricao": "Tradicional feijoada acompanhada de arroz branco, couve refogada no alho, farofa artesanal e fatias de laranja.",
      "preco": 38.90,
      "categoria": "PRATO_PRINCIPAL"
    }
    ```

> 🔎 **Como funciona por trás:** O banco de dados gera um **ID exclusivo** (ex: `1`) para este prato. Nós usaremos este ID no passo 3.  
> *Dica: Se quiser listar o cardápio completo, faça um `GET /restaurant/produto`.*

---

### 2️⃣ Abrir Mesa (Iniciar Atendimento)
> O cliente chega, escolhe uma mesa e nós iniciamos o seu atendimento vinculando o nome dele ao número da mesa escolhida.

*   **Rota:** `POST /atendimento/abertura`
*   **Header:** `Content-Type: application/json`
*   **Request Body (JSON):**
    ```json
    {
      "mesaId": "1",
      "nome": "Gabriel"
    }
    ```

> 🔎 **Como funciona por trás:** O sistema ativa a mesa `1` na memória.  
> *Dica: Para conferir todas as mesas ativas neste momento, utilize `GET /atendimento`.*

---

### 3️⃣ Fazer Pedido
> Com a mesa ativa e o cardápio cadastrado, o cliente faz a escolha. Enviamos uma lista com os IDs de todos os itens desejados. Suporta perfeitamente produtos repetidos (como pedir várias bebidas/pratos iguais).

*   **Rota:** `POST /restaurant/pedido`
*   **Header:** `Content-Type: application/json`
*   **Request Body (JSON):**
    ```json
    {
      "mesaId": "1",
      "produtosIds": [1, 2, 2, 2, 1],
      "observacao": "Sem obs"
    }
    ```

> 🔎 **Como funciona por trás:** O sistema valida os itens na memória e gera um **Número de Pedido** único (ex: `12`). Guarde esse número, pois ele será usado para controle da cozinha!  
> *Dica: Para ver todos os pedidos em andamento no salão, faça um `GET /restaurant/pedido`.*

---

### 4️⃣ Finalizar Pedido (Na Cozinha)
> O chef prepara o prato. Assim que tudo estiver pronto para ser servido ao Gabriel, a cozinha notifica o sistema utilizando o número do pedido.

*   **Rota:** `PUT /restaurant/cozinha/finalizar`
*   **Header:** `Content-Type: application/json`
*   **Request Body (JSON):**
    ```json
    {
      "numeroDoPedido": "12",
      "mesaId": "1"
    }
    ```

> 🔎 **Como funciona por trás:** O pedido muda de status no banco para finalizado, garantindo que a entrega física aconteça sem erros e que a cobrança seja computada.

---

### 5️⃣ Finalizar Mesa (Fechar Conta)
> O cliente pede a conta. Fechamos o atendimento liberando a mesa para novas pessoas e somando todos os pedidos feitos no período.

*   **Rota:** `PUT /atendimento/mesa/1/finalizar`

> 🔎 **Como funciona por trás:** O sistema busca todos os pedidos daquela mesa, soma os valores dos produtos, desativa o status de ocupada da mesa `1` e gera o registro histórico consolidado.

---

### 6️⃣ Buscar Histórico de Fechamento
> Rota estratégica para auditoria e controle de faturamento. Aqui o gerente consegue ver tudo que já foi fechado e pago.

*   **Rota:** `GET /atendimento/historico-fechamento`

> 🔎 **Como funciona por trás:** Você verá uma lista com os fechamentos consolidados de todas as mesas, incluindo o atendimento do Gabriel na mesa `1` com o valor total calculado.

---

## 🛠️ Stack Tecnológica

*   **Java 17** - Linguagem core e segura.
*   **Spring Boot 3.x** - Web, Data JPA, Hibernate.
*   **Flyway Database Migrations** - Versionamento controlado de banco de dados.
*   **Banco de Dados** - Preparado para ambiente relacional (PostgreSQL/MySQL).

---

> 💡 **Dica de Desenvolvimento:** Você pode rodar e testar todos esses cenários na sua máquina de forma instantânea usando os arquivos `.http` já configurados na raiz deste repositório!