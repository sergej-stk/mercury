<br />
<div align="center">
  <a href="https://github.com/sergej-stk/mercury">
    <img src="assets/logo.png" alt="Logo" width="200" height="200">
  </a>

<h3 align="center">Mercury API</h3>

  <p align="center">
    API for the Mercury platform, focusing on employee management and HR processes.
    <br />
    <a href="https://mercury.sergejsteinsiek.com/"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/sergej-stk/mercury/issues/new?template=bugreport.md">Report Bug</a>
    &middot;
    <a href="https://github.com/sergej-stk/mercury/issues/new?template=featurerequest.md">Request Feature</a>
  </p>
</div>

### Prerequisites

You will need the following software installed on your system to run this project:

* **Java Development Kit (JDK)**
    * This project requires **Java 21**.
    * You can download it from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/#java21) or an OpenJDK provider like [Adoptium](https://adoptium.net/temurin/releases/?version=21).
    * Ensure `JAVA_HOME` is set up correctly and `java --version` shows Java 21.

* **Apache Maven**
    * This project uses Maven for dependency management and building.
    * You can download Maven from the [Apache Maven Project](https://maven.apache.org/download.cgi).
    * Ensure Maven is installed and accessible from your command line (`mvn -v`).
    ```sh
    # Example: Check Maven version
    mvn -v
    ```

* **PostgreSQL Database**
    * This application uses PostgreSQL as its database.
    * Ensure you have a running PostgreSQL instance. You can download it from [postgresql.org](https://www.postgresql.org/download/).
    * You will also need to create a database and configure the connection details in `src/main/resources/application.properties` as shown in the Installation section.
    ```sh
    # Example for checking if PostgreSQL is running (Linux/macOS using psql)
    psql -U your_username -d your_database_name
    ```

### Installation

_Follow these steps to install and set up the Mercury API._

1.  **Clone the repository**
    ```sh
    git clone https://github.com/sergej-stk/mercury.git
    cd mercury
    ```

2.  **Build the project with Maven**
    This command will download dependencies, compile the code, and package the application.
    ```sh
    mvn clean install
    ```
    This will create a `.jar` file in the `target` directory (e.g., `target/mercury-0.0.1-SNAPSHOT.jar`).

3.  **(Optional) Configure your application**
    If your application requires specific configurations (e.g., database credentials, external API keys), you might need to update the `src/main/resources/application.properties` file.
    ```properties
    # Example: src/main/resources/application.properties
    # server.port=8080
    # spring.datasource.url=your_database_url
    ```
    
### Running the Application

Once the project is built, you can run the Spring Boot application using Maven:

```sh
mvn spring-boot:run
```

Open Swagger:
http://localhost:8080/swagger-ui/index.html#/

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!


### Top contributors:

<a href="https://github.com/sergej-stk/mercury/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=sergej-stk/mercury" alt="contrib.rocks image" />
</a>
