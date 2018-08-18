#Dentro do Arquivo application.properties, existe as configurações do MYSQL:

#Sera preciso criar a database:

CREATE SCHEMA `sale` ;

#Logo apos criar um database existe as conexões:

spring.datasource.url=jdbc:mysql://localhost:3306/sale?useTimezone=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

Devera trocar o username e o password se for preciso de acordo com o seu banco.
