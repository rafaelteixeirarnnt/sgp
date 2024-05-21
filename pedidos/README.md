# SGP - Módulo de Pedidos

Este é o módulo de Pedidos do Sistema de Gerenciamento de Pedido Integrados (SGP). Este módulo é responsável por centralizar os pedidos que os clientes desejam comprar, gerenciando todo o ciclo de vida de um pedido, desde a criação até a conclusão, incluindo o processamento de pagamento e o acompanhamento da entrega.

## Funcionalidades

- Criação de novos pedidos

[//]: # (- Atualização e cancelamento de pedidos)
- Consulta de detalhes de pedidos

[//]: # (- Processamento de pagamentos)
[//]: # (- Integração com serviços de entrega para rastreamento)
[//]: # (- Geração de relatórios e análises de pedidos)

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para criação da aplicação.
- **Spring Web**: Para desenvolvimento de aplicações web.
- **Lombok**: Para reduzir o código boilerplate, como getters, setters e construtores.
- **Spring Data JPA**: Para persistência de dados.
- **MapStruct**: Para mapeamento de objetos.
- **Liquibase**: Para versionamento de banco de dados.
- **PostgreSQL**: Como banco de dados relacional.
- **Docker**: Para containerização da aplicação.
- **Bean Validation**: Para validação de dados.
- **Spring Cloud Client**: Para integração com o Config Server.
- **Spring Integration**: Para integração entre sistemas.

## Requisitos

- Docker
- Docker Compose
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

   Pedidos: http://localhost:8083/swagger-ui/index.html