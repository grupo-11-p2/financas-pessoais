# 💰 Sistema de Finanças Pessoais

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=for-the-badge)
![POO](https://img.shields.io/badge/Disciplina-Programação%20Orientada%20a%20Objetos%20(P2)-blue?style=for-the-badge)

> Projeto da disciplina de **Programação Orientada a Objetos (P2)** (UFRPE/UABJ) para controle e gerenciamento de finanças pessoais.

---

## 📋 Sobre o Projeto

Sistema de controle financeiro pessoal desenvolvido em **Java**, aplicando os pilares da Orientação a Objetos: **encapsulamento, herança, polimorfismo e abstração**.

Nesta etapa, a aplicação roda em **modo texto (console)**. A versão final terá interface gráfica.

---

## ✨ Funcionalidades

- Criação, gerenciamento e remoção de múltiplas contas
- Registro de **receitas**, **despesas** e **transferências** entre contas
- **Edição e cancelamento** de transações, com validação de saldo
- Extrato por conta, com busca de transação por **ID**
- Filtros por **mês**, **período** e **categoria**
- Relatórios por período e por categoria
- Persistência e exportação de dados em **JSON**
- Validação de entradas do usuário

### Regras de negócio

- O saldo de uma conta **nunca pode ficar negativo**.
- Cancelar ou editar uma **receita** ou **transferência recebida** é bloqueado se deixar o saldo negativo.
- **Despesas** podem ser removidas a qualquer momento.
- Cada tipo de transação (`Receita`, `Despesa`, `Transferencia`) implementa seu próprio `cancelar()`, validando antes de alterar saldo e extrato.

---

## 📐 Modelagem & Diagramas (UML)

- 🔗 **[Acessar Diagrama no Lucidchart](https://lucid.app/lucidchart/b85fd054-d149-44fb-85ce-db948ad39e3b/edit?page=HWEp-vi-RSFO&invitationId=inv_20ebadb3-428f-4281-8c48-c64228fbf2e5)**
- 📄 **[Diagrama de Classes (PDF)](./docs/UML.pdf)**

### Principais Classes

| Classe | Responsabilidade |
|---|---|
| `Inicializador` | Ponto de entrada; cria as contas e inicia o sistema. |
| `Leitor` | Leitura e validação das entradas do console (ex.: `lerId`). |
| `GerenciarContas` | Gerencia a lista de contas (`List<Conta>`) e o cadastro de novas contas. |
| `Conta` | Representa uma conta (`nome`, `saldo`, `extrato`) e suas operações. |
| `Extrato` | Armazena as transações (`List<Transacao>`), filtra por mês, período ou categoria e busca por ID. |
| `Transacao` *(abstrata)* | Base das movimentações financeiras, com dados e comportamento comuns. |
| `Receita`, `Despesa`, `Transferencia` | Especializações de `Transacao`, cada uma com seu `cancelar()`. |
| `Categoria` | Classifica as transações. |
| `Movimentacao` | Conduz o registro, a edição e o cancelamento de transações de uma conta. |
| `Relatorio` | Gera extratos e saldos consolidados por período e por categoria. |
| `GerenciarArquivos` | Persistência e exportação em JSON (`carregarContas`, `salvarContaNova`, `salvarContaDados`, `exportarDados`). |

---

## 📁 Estrutura do Repositório

```text
financas-pessoais/
├── codigos base/    # Código-fonte principal em Java
├── docs/            # Documentação e diagramas UML
└── README.md        # Apresentação do projeto
```

---

## ▶️ Como Executar

**Pré-requisitos:** JDK [versão] instalado.

```bash
git clone https://github.com/[usuario]/financas-pessoais.git
cd financas-pessoais/"codigos base"
javac *.java
java Inicializador
```

> Ajuste o comando conforme a estrutura de pacotes do projeto.

---

## 🛣️ Roadmap

- [x] Contas, receitas, despesas e transferências
- [x] Edição e cancelamento de transações
- [x] Extrato, busca por ID e filtros
- [x] Relatórios por período e categoria
- [x] Persistência em JSON
- [x] Remoção de conta pelo menu, com cancelamento de transferências
- [ ] Interface gráfica / web
