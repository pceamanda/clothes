#Aplicação feita com gradle, pelo intellij

#Dentro do Arquivo application.properties, existe as configurações do MYSQL:

#Necessário instalação de um servidor mysql local

#Sera preciso criar a database:

CREATE SCHEMA `sale` ;

#Logo apos criar um database existe as conexões:

spring.datasource.url=jdbc:mysql://localhost:3306/sale?useTimezone=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

#Devera trocar o username e o password se for preciso de acordo com sua instalação de servidor mysql local.

#Aplicação sobe na porta 8083

http://localhost:8083/sale/

#Inserir registros de venda, possível usar o endpoint de gerar comprador, produto e venda, disponíveis no swagger

http://localhost:8083/sale/swagger-ui.html

#Exemplo de chamada para inserir comprador

curl -X POST "http://localhost:8083/sale/customer" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"cpf\": \"00000000066\" \"name\": \"teste\"}"

#Exemplo de chamada para inserir produto

curl -X POST "http://localhost:8083/sale/product" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"name\": \"produto\", \"price\": 55.5}"

#Exemplo de chamada para inserir venda

Primeiramente listar comprador e produto para inserir na venda, tendo os ids correspondentes:

curl -X POST "http://localhost:8083/sale/order" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"coo\": 123, \"customerId\": 1, \"productOrderRequests\": [ { \"amount\": 7, \"productId\": 2 } ]}"
