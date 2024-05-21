# SGP - Config Server

Este é o módulo Config Server do Sistema de Gerenciamento de Pedido Integrados (SGP). O Config Server é responsável por centralizar as configurações dos demais módulos, permitindo que todos eles obtenham suas configurações de forma centralizada. Isso facilita a manutenção e a atualização das configurações, além de proporcionar uma maior segurança e consistência.

## Funcionalidades

- Centralização das configurações de todos os módulos
- Atualização dinâmica das configurações
- Segurança nas configurações

## Tecnologias Utilizadas

- **Spring Cloud Config Server**: Para centralização das configurações dos outros módulos.
- **Spring Boot**: Framework principal para criação da aplicação.

## Requisitos

- Java 21
- Maven

## Como Executar

### Passos para Executar Localmente

1.	Certifique-se de que você tenha o Java 21 e o Maven instalados em sua máquina.
2.  Clone o repositório do projeto:
```bash
git clone https://github.com/rafaelteixeirarnnt/sgp.git
cd sgp
````

3.	Execute o projeto em sua IDE preferida:

    IntelliJ IDEA:
   1.	Abra o IntelliJ IDEA.
   2.	Selecione “Open” e escolha o diretório do projeto.
   3.	Aguarde a IDE importar o projeto e baixar as dependências.
   4.	Navegue até a classe principal (SgpClientesApplication.java).
   5.	Clique com o botão direito na classe principal e selecione “Run ‘SgpClientesApplication’”.
   
    Eclipse:
   1.	Abra o Eclipse.
   2.	Selecione “File” > “Import”.
   3.	Escolha “Existing Maven Projects” e clique em “Next”.
   4.	Navegue até o diretório do projeto e clique em “Finish”.
   5.	Aguarde o Eclipse importar o projeto e baixar as dependências.
   6.	Navegue até a classe principal (SgpClientesApplication.java).
   7.	Clique com o botão direito na classe principal e selecione “Run As” > “Java Application”.
   4.	Acesse a aplicação em seu navegador:

   Config-server: http://localhost:8888