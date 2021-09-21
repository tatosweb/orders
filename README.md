# products

*Coding task*

*How to Run:*
mvn spring-boot:run -Dspring.profiles.active=local

*Debug mode :*
mvn spring-boot:run -Dspring.profiles.active=local -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5006"

Example curl:

curl --location --request POST 'localhost:8080/api/v1/create-order' \
--header 'Content-Type: application/json' \
--data-raw '{
   "products":[
      {
         "article":"apples",
         "cost":60,
         "quantity":1,
         "activeOffer" : false
      },
      {
         "article":"oranges",
         "cost":25,
         "quantity":1,
         "activeOffer" : false
      }
   ]
}'