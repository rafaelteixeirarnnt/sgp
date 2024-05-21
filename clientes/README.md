# SGP - Módulo de Clientes

Este é o módulo de Clientes do Sistema de Gerenciamento de Pedido Integrados (SGP). Este módulo é responsável por armazenar e gerenciar todas as informações dos clientes da aplicação.

## Funcionalidades

- Cadastro de novos clientes
- Atualização de dados dos clientes existentes
- Consulta de informações detalhadas dos clientes
- Remoção de clientes
- Integração com sistemas externos para validação de dados

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

## Requisitos

- Docker
- Docker Compose

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

   Clientes: http://localhost:8081/swagger-ui/index.html