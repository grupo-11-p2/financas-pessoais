# 💰 Sistema de Finanças Pessoais

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=for-the-badge)
![POO](https://img.shields.io/badge/Disciplina-Programação%20Orientada%20a%20Objetos%20(P2)-blue?style=for-the-badge)

> Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos (P2)** com o objetivo de auxiliar no controle e gerenciamento de finanças pessoais.

---

## 📋 Sobre o Projeto

Este repositório contém a implementação de um sistema de controle financeiro pessoal desenvolvido em **Java**, aplicando conceitos fundamentais de Orientação a Objetos (encapsulamento, herança, polimorfismo e abstração).

---

## 📐 Modelagem & Diagramas (UML)

A arquitetura e o diagrama de classes do sistema podem ser visualizados no Lucidchart ou diretamente na pasta de documentação do repositório:

- 🔗 **[Acessar Diagrama no Lucidchart](https://lucid.app/lucidchart/b85fd054-d149-44fb-85ce-db948ad39e3b/edit?page=HWEp-vi-RSFO&invitationId=inv_20ebadb3-428f-4281-8c48-c64228fbf2e5)**
- 📄 **[Diagrama de Classes (PDF)](./docs/UML.pdf)**

### Principais Classes do Diagrama
- **`GerenciarContas`**: gerencia a lista de contas (`List<Conta>`) e a criação de contas.
- **`Conta`**: representa cada conta (`nome`, `saldo`, `extrato`), com operações de saldo, nome e extrato.
- **`Extrato`**: armazena e filtra transações (`List<Transacao>`) por conta, mês, período (`LocalDate`) ou categoria (`List<Categoria>`).
- **`Movimentacao`**: gerencia cancelamento e edição de transações vinculadas a uma conta.
- **`Relatorio`**: gera extratos e saldos consolidados por mês, ano e categoria.
- **`GerenciarArquivos`**: lida com persistência e exportação de dados em JSON (`carregarContas`, `salvarContaNova`, `salvarContaDados`, `exportarDados`).

---

## 📁 Estrutura do Repositório

```text
financas-pessoais/
├── codigos base/    # Código-fonte principal em Java
├── docs/            # Documentação e diagramas UML do projeto
└── README.md        # Apresentação do projeto
