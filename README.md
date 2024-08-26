# Spring Boot BigQuery CRUD API

This project demonstrates a Spring Boot application that integrates with Google BigQuery to perform CRUD operations on a Person entity.

## Features

- REST API for managing Person entities
- Integration with Google BigQuery for data storage
- CRUD operations (Create, Read, Update, Delete)

## Prerequisites

- Java 8 or higher
- Maven
- Google Cloud Platform account with BigQuery enabled
- Google Cloud credentials file

## Configuration

The application uses the following properties, which should be set in `application.properties`:

- `google.cloud.project-id`: Your Google Cloud project ID
- `google.cloud.dataset-id`: Your BigQuery dataset ID
- `google.cloud.credentials.location`: Path to your Google Cloud credentials JSON file

## Project Structure

- `DemoApplication.java`: Main Spring Boot application class
- `BigQueryClient.java`: Client for executing BigQuery queries
- `BigQueryConfig.java`: Configuration for BigQuery connection
- `Person.java`: Model class for Person entity
- `PersonController.java`: REST controller for Person API
- `PersonService.java`: Service layer for Person operations

## API Endpoints

- POST `/api/persons`: Create a new person
- GET `/api/persons`: Retrieve all persons
- PUT `/api/persons/{id}`: Update a person
- DELETE `/api/persons/{id}`: Delete a person

## Running the Application

1. Set up your Google Cloud credentials and update `application.properties`
2. Build the project: `./gradlew build`
3. Run the application: `./gradlew bootRun`

The application will start on port 8081 by default.

## Technologies Used

- Spring Boot
- Google Cloud BigQuery
- Lombok
- SLF4J for logging

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.