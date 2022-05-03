### This is a project using Spring Boot and MySQL database. 

**Operational System:**

- Windows 10

**Technologies:**

- IntelliJ IDEA Communit Edition 2021.2.3
- MySQL 8.0.29
- Spring Boot 2.6.7 
- Java JDK 8.0.271 
- Maven 3.8.2
- Postman 8.11.1
- Docker Desktop 20.10.14

**How to run:**
- You need to have a MySQL 8 instance started and running

1. Clone the repository in a folder.
2. Open the project in your preferential IDE and just run the main application.

Alternative to run :

1. Open a terminal or command prompt at your cloned project folder.
2. Run the command: "mvn spring-boot:run" to have the API running at port 8081.

**Payload:**

1) There is in the folder "payload" an exported file named "quotation-manager.postman_collection.json" that you can use to import to your Postman tool, so you will have all the endpoints ready and configured to use and test the API

**Endpoints:**

Endpoints:
```
(POST)   http://localhost:8081/create
(GET)    http://localhost:8081/listallstockquote
(GET)    http://localhost:8081/findbystockid/petr8
(POST)   http://localhost:8080/stock
(DELETE) http://localhost:8081/stockcache
