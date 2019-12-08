# PidginN
### Multilingual Business Communication Application

#### Requirements
* Java (8 and above)
* Maven
* MySQL
* Google Translate Credentials

#### Setting Up the project:
1. Clone the repo.
2. Create the Databse and Tables in MySQL with this [script](https://github.com/vishnuys/PidginN/blob/master/DBScripts/Table/AllTables.sql).
3. Create all the Stored Procedures with the scripts from the [folder](https://github.com/vishnuys/PidginN/tree/master/DBScripts/SP)
4. Create `application.properties` file in the folder "pidgin-server/src/main/resources/" and fill it with below values.
```
spring.datasource.url=jdbc:mysql://<MySQL server address (default=localhost)>:<MySQL server port (default=3306)>/pidgin
spring.datasource.username=<MySQL Username>
spring.datasource.password=<MySQL Password>
google.api.translation.jsonpath=<Google API Key JSON file path>
```

#### Running from Spring Tool Suite:
1. Install [Spring Tool Suite](https://spring.io/tools3/sts/all).
2. Import "pidgin-server" as maven project.
3. For the first build, choose **Run as** -> **Maven Install**.
4. To start the server, choose **Run as** -> **Spring Application**.

#### Running from Command Line
1. Go to the `pidgin-server` folder
2. run `mvn spring-boot:run` to start the server

#### Using the Application:
1. Open the browser and go to `localhost:8080`. Other users can access the application using the machine's IP with the port `8080`.
2. Use the Sign Up page to register. 
3. Login to the application using your credentials.
4. Add connections with the existing users to start the chat.
5. Send message to the user to see the real time translation of the messages from your language to his and vice versa.
