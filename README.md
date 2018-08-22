#Dentro do Arquivo application.properties, existe as configurações do MYSQL:

#Necessário instalação de um servidor mysql local

#Sera preciso criar a database:

CREATE SCHEMA `sale` ;

#Logo apos criar um database existe as conexões:

spring.datasource.url=jdbc:mysql://localhost:3306/sale?useTimezone=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

#Devera trocar o username e o password se for preciso de acordo com sua instalação de servidor mysql local.

#Testes de performance feitos no jMeter retornaram os registros em menos de 7s
#Já o Postman enfileira as requests, demorando mais por não ser multi thread.
