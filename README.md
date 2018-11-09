# Project Management System

The application which helps in managing expenses for system support.

## Configuration

### Database

File [src/main/resources/hibernate.properties](src/main/resources/hibernate.properties) contains configuration for connection to database.
```
jdbc.url=jdbc:postgresql://localhost:5432/postgres
jdbc.user=postgres
jdbc.pass=pass
```

### File import

The app allows to import files with extension `.xslx`.
File [src/main/resources/import.properties](src/main/resources/import.properties) contains configuration about column names from imported file.
The column names will be in first line of file. The order of the columns does not matter.

### Logging

File [src/main/resources/log4j.properties](src/main/resources/log4j.properties) contains configuration for logging.

## Build the app

Go to the app folder and execute command:

```
mvn clean package
```
If compile and test are successful, the `.war` file will appear in [target](target) directory.


## Start Up

The running PostgreSQL is needed to start the app.

To initialize the database with sample data, execute the command(updated with correct data):

```
psql -h localhost -p 5432 -U postgres < init.sql
```

Deploy the generated `.war` file to tomcat and run it.

### Start PostgreSQL in Docker

To run database in docker execute:
```
docker run --name pms-postgres -e POSTGRES_PASSWORD=pass -p 5432:5432 -d postgres
```

## Start Up with Docker-Compose

For starting the app with docker-compose just execute (The database configuration is unnecessary):

```
docker-compose up
```

The running app will be available in [localhost:8080](http://localhost:8080)

## Author

* **Artur Siembab** - [GitHub](https://github.com/ajtuss), [linkedIn](https://www.linkedin.com/in/artur-siembab/)

