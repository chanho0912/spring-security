# docker run --name mysql-security -e MYSQL_ROOT_PASSWORD=${...} -d -p 3306:3306 mysql:8.0.23
# docker exec -it mysql-security bash
# mysql -u root -p
# enter the password ${...}

create user 'chanho'@'%' identified by 'chanho';
grant all privileges on *.* to 'chanho'@'%' WITH GRANT OPTION;
create database security;
use security;