# API Clients CRUD

##### Esta api foi desenvolvida como parte do desafio seletivo da empresa SNet empresa de nome e impato na cidade de João Pessoa e Região com grandes clientes em todo o Brasil.

### Passo a Passo para execução:

- Em seu banco de dados mysql execute o comando: create database snet_challenge_crud_clients;
- Base de dados:Configure os dados de acesso do seu mysql no arquivo application.properties nas seções spring.datasource e spring.flyway.
- Migrations: Configure os dados de acesso do seu mysql no arquivo pom.xml na seção: build > plugins > flyway-maven-plugin > configuration.
- Procure pela classe Startup e inicie a aplicação através dela as migrations serão rodadas automaticamente graças à configuração do plugin configurado no passo anterior.
