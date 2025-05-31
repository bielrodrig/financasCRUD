<h4 align="center"> 
	<img src="http://img.shields.io/static/v1?label=STATUS&message=DESENVOLVIMENTO%20EM%20ANDAMENTO&color=F7DF1E&style=for-the-badge"/>
</h4>

# 💵 Controle de Finanças Pessoais

## Descrição do Projeto

Este projeto tem como objetivo fornecer uma ferramenta prática e intuitiva para o controle de finanças pessoais, permitindo que o usuário registre, visualize e exclua transações financeiras de forma simples e organizada. Desenvolvido utilizando **Java Swing** para a interface gráfica e **MySQL** como banco de dados, a aplicação oferece uma maneira eficiente de gerenciar entradas e saídas financeiras.

---

## 🎯 Motivação Pessoal

> 💡 Esse projeto surgiu de uma necessidade real dentro da minha própria casa. Minha mãe sempre teve dificuldade para se organizar financeiramente. Ela até tentou aprender a usar Excel, mas sentiu que seria necessário fazer um curso para conseguir usar de forma eficiente, o que acabava sendo complicado para ela.  
>  
> Pensando nisso, resolvi desenvolver uma solução simples, intuitiva e direta, onde ela pudesse facilmente registrar seus gastos, visualizar suas despesas e controlar suas finanças sem complicação.  
>  
> Se minha mãe conseguiu usar, qualquer um consegue! 😄

---

## ⚙️ Funcionalidades

- ✅ Cadastro de Transações: Insira entradas e saídas com nome, valor, data e tipo (entrada/saída).
- 🔍 Listagem de Transações: Visualize todas as transações realizadas em uma tabela organizada.
- 🗑️ Remoção de Transações: Delete lançamentos de forma simples.
- 💾 Armazenamento em Banco de Dados: Todos os dados são salvos no MySQL, garantindo persistência e segurança.

---

## 🚧 Melhorias em Desenvolvimento

- 🧮 **Cálculo Total dos Gastos e Receitas:** Implementação de um painel que mostra automaticamente o total de entradas, saídas e saldo final.
- 📊 **Relatórios Financeiros:** Gerar relatórios periódicos (mensais, semanais, por tipo de gasto) em PDF ou na própria interface.
- 👁️ **Reconhecimento Facial:** Adicionar uma camada extra de segurança ao sistema através de reconhecimento facial para autenticação do usuário.
- 🤖 **Integração com IA:** Implementação de uma inteligência artificial que ajuda o usuário dando dicas financeiras, insights sobre os gastos e até sugestões de economia.

---

## 🔥 Impacto do Projeto

- 🏆 **Resolução de um Problema Real:** Sistema pensado e desenvolvido para pessoas que não estão familiarizadas com planilhas ou sistemas complexos.
- 🎯 **Solução Simples e Intuitiva:** Interface amigável, feita com Java Swing, sem burocracia.
- 📈 **Escalável:** Pode ser evoluído para gerar gráficos, relatórios e até controle de orçamento mensal.
- 💼 **Prática Profissional:** Aplicação de conceitos de desenvolvimento real, banco de dados, boas práticas e segurança.

---

## 🚀 Tecnologias Utilizadas

- **Java (Swing)** – Interface Gráfica
- **MySQL** – Banco de Dados
- **JDBC** – Conexão Java + MySQL
- **Maven** – Gerenciamento de dependências

---

## 🧠 Como Rodar o Projeto

### ✔️ Pré-requisitos

- Java JDK 8 ou superior  
- MySQL  
- IDE (IntelliJ, Eclipse, NetBeans)  
- MySQL Connector/J (via Maven ou manual)

### 📦 Banco de Dados

Execute os seguintes comandos no seu MySQL:

```sql
CREATE DATABASE financas;

CREATE TABLE transacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    data DATE NOT NULL
);
