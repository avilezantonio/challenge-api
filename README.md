
# Challenge Api

Project created based on the assesment provided by agile engine


## Run Locally

Clone the project

```bash
  git clone git@github.com:avilezantonio/challenge-api.git
```

Go to the project directory

```bash
  cd challenge-api
```

Run the Postgres Container

```bash
  docker compose up -d challenge_db
```

Create the Jar file

```bash
  ./mvnw clean package -DskipTests
```

Build it

```bash
  docker compose build
```

Run the api
```bash
  docker compose up challenge_api
```


## Running Tests

To run tests, run the following command

```bash
  ./mvnw test
```

## Swagger ui

To view Open Api definition run the project and then open the follow url

```
http://localhost:8080/swagger-ui/index.html
```

