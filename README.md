# API Clients CRUD

##### Esta api foi desenvolvida como parte do desafio seletivo da empresa SNet empresa de nome e impato na cidade de João Pessoa e Região com grandes clientes em todo o Brasil.

### Passo a Passo para execução:

- Em seu banco de dados mysql execute o comando: create database snet_challenge_crud_clients;
- Base de dados:Configure os dados de acesso do seu mysql no arquivo application.properties nas seções spring.datasource e spring.flyway.
- Migrations: Configure os dados de acesso do seu mysql no arquivo pom.xml na seção: build > plugins > flyway-maven-plugin > configuration.
- Procure pela classe Startup e inicie a aplicação através dela as migrations serão rodadas automaticamente graças à configuração do plugin configurado no passo anterior.
- Para utilizar o sistema utilize um dos dois usuários já existentes abaixo ou crie um novo atraves do endpoint de signup.
- Para realizar a autenticação utilize o endpoint de singin informando username ou email e o password
- Para executar o frontend utilize a sequencia de comandos: cd clients-crud-front, npm install, e por ultimo ng serve

Autenticação:
email: italo_2512@hotmail.com, username: italo-gouveia, password: admin123
email: atouguialeite@gmail.com, username: renato-leite, password: admin234


### ENDPOINTS:

Endpoints           | Metodo    | Request       | Response              | PathParam | RequestParam                  |
---------------------------------------------------------------------------------------------------------------------
/auth/signin        | POST      | Credentials1  |SigninResponse         |           |                               |
/auth/signup        | POST      | Credentials2  |String username        |           |                               |
/api/v1/client      | GET       |               |ClientResponse(List)   |           |page, limit, order(asc, desc)  |
/api/v1/client      | POST      |ClientRequest1 |ClientResponse(Objeto) |           |                               |
/api/v1/client      | PUT       |ClientRequest2 |ClientResponse(Objeto) |           |                               |
/api/v1/client/{id} | GET       |               |ClientResponse(Objeto) |           |                               |
/api/v1/client/{id} | DELETE    |               |                       | {id}      |                               |
---------------------------------------------------------------------------------------------------------------------

### MODELS REQUEST:
- Credentials1 = username: string, email: string, password: string
- Credentials2 = username: string, fullname: string, email: string, password: string
- ClientRequest1 = name: string, phone: string, address: string, houseNumber: string, city: string, uf:string, country: string, postalCode: string
- ClientRequest2 = id: number, name: string, phone: string, address: string, houseNumber: string, city: string, uf:string, country: string, postalCode: string

### MODELS RESPONSE:
- SigninResponse = token: string, username: string
- ClientReponse = id: string, name: string, phone: string, address: string, houseNumber: string, city: string, uf:string, country: string, postalCode: string
