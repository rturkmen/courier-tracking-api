# Courier-Tracking-Application

This app provides you that tracking couriers where they are . 

## How can you run the project ?
Firstly, You need to install Docker on your computer.After that you have set JAVA_HOME environment variables as java 11
.Then you can run following commands below in project root directory

* mvn clean install
* docker-compose build
* docker-compose up

## H2 database

* path: http://localhost:8091/h2
* url: jdbc:h2:mem:migros
* username: migros
* there is no password on h2 database .You can enter db without password


## How can you use this api ?
You can access the swagger from this link -> http://localhost:8091/swagger-ui.html

* After that you can create a courier from '/courier/create' endpoint
* You can access courier's details from '/courier/details' endpoint
* You can update courier's locations from '/courier/update' endoint


