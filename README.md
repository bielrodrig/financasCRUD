# 💵 Controle de Finanças Pessoais

## Descrição do Projeto

Este projeto tem como objetivo fornecer uma ferramenta prática e intuitiva para o controle de finanças pessoais, permitindo que o usuário registre, visualize e exclua transações financeiras de forma simples e organizada. Desenvolvido utilizando **Java Swing** para a interface gráfica e **MySQL** como banco de dados, a aplicação oferece uma maneira eficiente de gerenciar entradas e saídas financeiras.

Ao invés de ser apenas um CRUD básico, este sistema traz benefícios reais de gestão de finanças para indivíduos e pequenas empresas, com foco em:

- **Organização de Dados Financeiros**: Permite a inserção e o acompanhamento de transações financeiras de forma clara e acessível.
- **Interface Gráfica Intuitiva**: A utilização do Java Swing proporciona uma interface de fácil navegação, garantindo que o usuário possa realizar suas operações com eficiência.
- **Persistência de Dados**: Ao armazenar as transações em um banco de dados MySQL, o projeto oferece a capacidade de manter o histórico financeiro de forma segura e acessível.
- **Segurança**: O projeto utiliza boas práticas para garantir que as transações sejam salvas corretamente e com segurança no banco de dados.
  
Este sistema não apenas facilita o controle de gastos e receitas, mas também pode ser expandido para futuras integrações, como relatórios financeiros, gráficos de desempenho e até mesmo alertas de limite de orçamento.

---

## ⚙️ Funcionalidades

- **Cadastro de Transações**: O usuário pode cadastrar transações, como entradas e saídas financeiras, com descrição, valor, tipo e data.
- **Listagem de Transações**: Através de uma tabela, o usuário pode visualizar todas as transações cadastradas, facilitando o acompanhamento.
- **Exclusão de Transações**: O sistema permite a exclusão de transações, com a confirmação de exclusão para evitar ações acidentais.
- **Armazenamento em Banco de Dados**: As transações são salvas em um banco de dados MySQL, garantindo persistência de dados.

---

## ⭐ Impacto do Projeto

### 1. **Resolução de Problemas Reais**
Este projeto vai além de um simples CRUD. Ele resolve um problema cotidiano de forma eficaz, permitindo aos usuários controlar e gerenciar suas finanças pessoais ou de pequenas empresas de maneira organizada e prática. É uma solução que pode ser facilmente escalada ou integrada com outras ferramentas de análise financeira.

### 2. **Boa Arquitetura e Organização**
A escolha do Java Swing para a interface gráfica oferece uma experiência de usuário fluida e intuitiva. Além disso, o uso do MySQL como banco de dados proporciona uma base sólida para qualquer aplicação que precise de persistência e segurança nos dados.

### 3. **Desenvolvimento Contínuo e Expansão**
O projeto pode ser ampliado com funcionalidades adicionais, como geração de relatórios financeiros, gráficos de desempenho financeiro, integração com APIs externas para dados financeiros, e muito mais. Isso demonstra a capacidade de pensar em soluções escaláveis, o que é altamente valorizado por recrutadores.

### 4. **Experiência com Ferramentas do Mercado**
Este projeto não apenas ensina conceitos básicos de programação, mas também coloca em prática ferramentas e tecnologias utilizadas amplamente no mercado de software, como banco de dados, interfaces gráficas de usuário e boas práticas de desenvolvimento.

---

## Tecnologias Utilizadas

- **Java** (Swing): Para o desenvolvimento da interface gráfica de usuário (GUI).
- **MySQL**: Para armazenamento e persistência de dados das transações financeiras.
- **JDBC**: Para realizar a conexão entre o Java e o banco de dados MySQL.
  
---

## Como Rodar o Projeto

### Pré-requisitos

Antes de rodar o projeto, certifique-se de ter os seguintes pré-requisitos instalados:

- **Java JDK 8 ou superior**: Necessário para compilar e executar a aplicação.
  - [Download do JDK](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
  
- **MySQL**: O banco de dados utilizado para armazenar as transações financeiras.
  - [Download do MySQL](https://dev.mysql.com/downloads/installer/)

- **IDE Java (recomendado IntelliJ IDEA, Eclipse, NetBeans)**: Para editar, compilar e rodar o código-fonte.
  - [Download do IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
  - [Download do Eclipse](https://www.eclipse.org/downloads/)

- **MySQL JDBC Driver**: O driver necessário para conectar o Java com o banco de dados MySQL. Este driver é geralmente incluído na dependência de seu projeto ou você pode adicioná-lo manualmente ao seu projeto.

### Passos para Rodar o Projeto

1. **Configuração do Banco de Dados**

   Antes de rodar a aplicação, você precisa configurar o banco de dados MySQL:

   - Crie uma instância do MySQL em seu sistema local ou em um servidor.
   - No MySQL, crie um banco de dados com o nome `financas`.
     ```sql
     CREATE DATABASE financas;
     ```
   - Crie a tabela de transações dentro do banco de dados:
     ```sql
     CREATE TABLE transacoes (
         id INT AUTO_INCREMENT PRIMARY KEY,
         nome VARCHAR(255) NOT NULL,
         valor DECIMAL(10, 2) NOT NULL,
         tipo VARCHAR(50) NOT NULL,
         data DATE NOT NULL
     );
     ```

2. **Configuração da Conexão JDBC**

   No código fonte do projeto, você precisa configurar a conexão com o banco de dados MySQL. A conexão deve ser feita através do driver JDBC. Aqui está um exemplo de URL de conexão:

   ```java
   String url = "jdbc:mysql://localhost:3306/financas-db";
   String user = "root";
   String password = "12";


