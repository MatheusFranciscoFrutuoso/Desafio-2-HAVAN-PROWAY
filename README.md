# Desafio-2-HAVAN-PROWAY

Pastas do segundo desafio HAVAN-PROWAY.

Para a compilação e execução sera necessario ter o java instalado e funcional.

Sendo necessario fazer a conexão a um banco de dados com as seguintes informaçoes:

Url = "jdbc:mysql://localhost:3306/gerenciaroperacoes; user = "proway"; password = "proway";

Ou alterar os valores no arquivo metodos classe conexao() com as informações correspondetes.

E alem disso criar neste banco criar uma database conforme abaixo: 

CREATE database IF NOT EXISTS  gerenciarOperacoes;
use gerenciarOperacoes;
CREATE TABLE IF NOT EXISTS cadastroOperacoes (codigooperacao int not null auto_increment, nomecliente char(50), moedaorigem char(20), moedadestino char(20), dataoperacao TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP , valororiginal double(20, 3),  valorconvertido double(20, 3), taxacobrada double(20, 3), primary key(codigooperacao));
CREATE TABLE IF NOT EXISTS cadastroMoedas (codigomoeda int not null auto_increment, nomemoeda char(20), valormoeda double(20 ,3), primary key(codigomoeda));
insert into cadastroMoedas values (1, 'DOLAR', 5.35);
insert into cadastroMoedas values (2, 'EURO', 6.52);
insert into cadastroMoedas values (3, 'LIBRA ESTERLINA', 7.59);
insert into cadastroMoedas values (4, 'REMIMBI', 0.83);
