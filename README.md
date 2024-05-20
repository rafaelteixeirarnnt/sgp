# SGP - Sistema de Gerenciamento de Pedido Integrados

Este é o repositório do Sistema de Gerenciamento de Pedido Integrados (SGP), um projeto desenvolvido para gerenciar clientes, produtos e pedidos de forma integrada. O projeto é dividido em quatro módulos principais: Config Server, Clientes, Produtos e Pedidos.

## Módulos

### 1. Config Server

O módulo Config Server é responsável por centralizar as configurações dos demais módulos, permitindo que todos eles obtenham suas configurações de forma centralizada. Isso facilita a manutenção e a atualização das configurações, além de proporcionar uma maior segurança e consistência.

#### Funcionalidades:
- Centralização das configurações de todos os módulos
- Atualização dinâmica das configurações
- Segurança nas configurações

### 2. Clientes

O módulo de Clientes é responsável por armazenar e gerenciar todas as informações dos clientes da aplicação. Isso inclui dados pessoais, endereços, informações de contato e histórico de interações. Este módulo é fundamental para manter um registro detalhado dos clientes, o que é essencial para oferecer um atendimento personalizado e eficiente.

#### Funcionalidades:
- Cadastro de novos clientes
- Atualização de dados dos clientes existentes
- Consulta de informações detalhadas dos clientes
- Remoção de clientes
- Integração com sistemas externos para validação de dados

### 3. Produtos

O módulo de Produtos é responsável por manter as informações dos produtos e o controle de estoque. Este módulo garante que todas as informações sobre os produtos estejam atualizadas, incluindo descrições, preços, categorias e quantidades em estoque. É essencial para o gerenciamento eficaz do inventário e para garantir que os produtos estejam disponíveis para os clientes.

#### Funcionalidades:
- Cadastro de novos produtos
- Atualização de informações dos produtos existentes
- Consulta de detalhes dos produtos
- Controle de estoque e alertas de baixa quantidade
- Integração com fornecedores para atualização automática de estoque

### 4. Pedidos

O módulo de Pedidos é responsável por centralizar os pedidos que os clientes desejam comprar. Este módulo gerencia todo o ciclo de vida de um pedido, desde a criação até a conclusão, incluindo o processamento de pagamento e o acompanhamento da entrega. Ele também integra os módulos de Clientes e Produtos para assegurar que os pedidos sejam gerenciados de forma eficiente.

#### Funcionalidades:
- Criação de novos pedidos

[//]: # (- Atualização e cancelamento de pedidos)
- Consulta de status

[//]: # (- Processamento de pagamentos)
[//]: # (- Integração com serviços de entrega para rastreamento)
[//]: # (- Geração de relatórios e análises de pedidos)

## Tecnologia

O projeto SGP foi desenvolvido utilizando o ecossistema do Spring, incluindo os seguintes módulos e tecnologias:

### Config Server

- **Spring Cloud Config Server**: Para centralização das configurações dos outros módulos.

### Clientes

- **Spring Web**: Para desenvolvimento de aplicações web.
- **Lombok**: Para reduzir o código boilerplate, como getters, setters e construtores.
- **Spring Data JPA**: Para persistência de dados.
- **MapStruct**: Para mapeamento de objetos.
- **Liquibase**: Para versionamento de banco de dados.
- **PostgreSQL**: Como banco de dados relacional.
- **Docker**: Para containerização da aplicação.
- **Bean Validation**: Para validação de dados.
- **Spring Cloud Client**: Para integração com outros serviços.

### Produtos

- **Spring Web**: Para desenvolvimento de aplicações web.
- **Lombok**: Para reduzir o código boilerplate, como getters, setters e construtores.
- **Spring Data JPA**: Para persistência de dados.
- **MapStruct**: Para mapeamento de objetos.
- **Liquibase**: Para versionamento de banco de dados.
- **PostgreSQL**: Como banco de dados relacional.
- **Docker**: Para containerização da aplicação.
- **Bean Validation**: Para validação de dados.
- **Spring Cloud Client**: Para integração com outros serviços.
- **Spring Integration**: Para integração entre sistemas.

### Pedidos

- **Spring Web**: Para desenvolvimento de aplicações web.
- **Lombok**: Para reduzir o código boilerplate, como getters, setters e construtores.
- **Spring Data JPA**: Para persistência de dados.
- **MapStruct**: Para mapeamento de objetos.
- **Liquibase**: Para versionamento de banco de dados.
- **PostgreSQL**: Como banco de dados relacional.
- **Docker**: Para containerização da aplicação.
- **Bean Validation**: Para validação de dados.
- **Spring Cloud Client**: Para integração com outros serviços.
- **Spring Integration**: Para integração entre sistemas.
- **Spring Batch**: Para processamento de dados em lote.

## Como Executar

### Pré-requisitos

- Docker
- Docker Compose

### Passos para Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/sgp.git
   cd sgp
   ```

2.	Inicie os containers Docker:
   ```bash
    docker-compose up
   ```

3.	Acesse a aplicação em seu navegador:

- Clientes: http://localhost:8081
- Produtos: http://localhost:8082
- Pedidos: http://localhost:8083