# Courier-Tracking-Application

This app provides you that tracking couriers where they are . 

## How you can run the project ?
Firstly, You need to install Docker on your computer.After that you can run following commands below

* mvn clean install
* docker-compose build
* docker-compose up

## H2 database

* url: jdbc:h2:mem:migros
* username: migros
* there is no password on h2 database .You can enter db without password


## How to use this api ?
You can access the swagger from this link -> http://localhost:8091/swagger-ui.html

* After that you can create a courier from '/courier/create' endpoint
* You can access courier's details from '/courier/details' endpoint
* You can update courier's locations from '/courier/update' endoint


