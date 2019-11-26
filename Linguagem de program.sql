create database Notas
use Notas
create table tb_notas(
id int primary key identity(1,1),
nome varchar(60),
n1_1 decimal(2,1),
n2_1 decimal(2,1),
n1_2 decimal(2,1),
n2_2 decimal(2,1),
forma decimal(2,1),
nota_final decimal(2,1),
)

select * from tb_notas

SELECT @@VERSION 