# create databases
CREATE DATABASE IF NOT EXISTS `db_springboot_cloud`;
CREATE DATABASE IF NOT EXISTS `db_zipkin`;

# create root user and grant rights
CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';