create database jv06_demo_springMVC;
use jv06_demo_springMVC;

create table Customer
(
    id      int primary key auto_increment not null,
    name    varchar(50)                    not null,
    address varchar(255)                   not null,
    email   varchar(255)                   not null
);

delimiter //
create procedure proc_show_list_customer()
begin
    select * from customer;
end //

delimiter //
create procedure proc_create_customer(in cName varchar(50), cAddress varchar(255), cEmail varchar(255))
begin
    insert into customer(name, address, email) values (cName, cAddress, cEmail);
end //

delimiter //
create procedure proc_update_customer(in cName varchar(50), cAddress varchar(255), cEmail varchar(255), cId int)
begin
    update customer set name = cName, address = cAddress, email = cEmail where id = cId;
end //

delimiter //
create procedure proc_delete_customer(in cId int)
begin
    delete from customer where id = cId;
end //

delimiter //
create procedure proc_find_by_id(in cId int)
begin
    select *from customer where id = cId;
end //

delimiter //
create procedure proc_find_by_name(in cName varchar(50))
begin
    select * from customer where name like concat('%',cName,'%');
end //



