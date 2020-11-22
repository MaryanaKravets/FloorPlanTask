#
FloorPlanTask

Use:

    Spring Boot, Data
    Hibernate, Jpa, Maven
    PostgreSQL
    Docker-compose

    -Build app

     mvn clean package

    -Build image and run docker-compose

     docker-compose build

     docker-compose up

Functionality:

   1)  POST http://localhost:8080/validateRoom for validate and save room (int- integer value): 
          { 
           "pointList":
             [ 
              { "x" : int,"y": int}, 
              { "x" : int,"y": int}, 
              { "x" : int,"y": int}, 
              { "x" : int,"y": int}, 
              { "x" : int,"y": int}, 
              { "x" : int,"y": int} 
            ] 
          }

   2) GET http://localhost:8080  for get all rooms from db

   3)  GET http://localhost:8080/{id}  for get room with id from db
