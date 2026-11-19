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

- 🔗 **[Acessar Diagrama no Lucidchart]([https://lucidchart.com/b85fd054-d149-44fb-85ce-db948ad39e3b/edit?viewport_loc=-5150%2C96%2C2689%2C2833%2CCHWEp-vi-RSFO&invitationId=inv_20ebadb3-428f-4281-8c48-c64228fbf2e5](https://lucid.app/lucidchart/b85fd054-d149-44fb-85ce-db948ad39e3b/edit?page=HWEp-vi-RSFO&invitationId=inv_20ebadb3-428f-4281-8c48-c64228fbf2e5#))**[cite: 1]
- 📄 **[Diagrama de Classes (PDF)](./docs/UML.pdf)**[cite: 1, 2]

### Principais Classes do Diagrama
- **`GerenciarContas`**: gerencia a lista de contas (`List<Conta>`) e a criação de contas.
- **`Conta`**: representa cada conta (`nome`, `saldo`, `extrato`), com operações de saldo, nome e extrato[cite: 2].
- **`Extrato`**: armazena e filtra transações (`List<Transacao>`) por conta, mês, período (`LocalDate`) ou categoria (`List<Categoria>`)[cite: 2].
- **`Movimentacao`**: gerencia cancelamento e edição de transações vinculadas a uma conta[cite: 2].
- **`Relatorio`**: gera extratos e saldos consolidados por mês, ano e categoria[cite: 2].
- **`GerenciarArquivos`**: lida com persistência e exportação de dados em JSON (`carregarContas`, `salvarContaNova`, `salvarContaDados`, `exportarDados`)[cite: 2].

---

## 📁 Estrutura do Repositório

```text
financas-pessoais/
├── codigos base/    # Código-fonte principal em Java
├── docs/            # Documentação e diagramas UML do projeto
└── README.md        # Apresentação do projeto
```[cite: 1]

---

## 🚀 Como Executar

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/grupo-11-p2/financas-pessoais.git](https://github.com/grupo-11-p2/financas-pessoais.git)
   ```[cite: 1]
2. **Acesse a pasta do projeto:**
   ```bash
   cd financas-pessoais
   ```[cite: 1]
3. **Abra na sua IDE preferida** (VS Code, IntelliJ, Eclipse) e execute a classe principal dentro de `codigos base`[cite: 1].

---

## 👥 Integrantes (Grupo 11)

| Integrante | GitHub |
| :--- | :--- |
| **Caio Eduardo** | [@caiorios2604-source](https://github.com/caiorios2604-source) |
| **Ryan Torres** | [@Ryan-torres](https://github.com/Ryan-torres) |
| **Beckham** | [@sadavid382-alt](https://github.com/sadavid382-alt) |[cite: 1]
