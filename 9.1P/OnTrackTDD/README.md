# OnTrackTDD

A task management service developed using Test-Driven Development (TDD) practices.

## Project Overview

OnTrackTDD is a Java-based task management system that allows users to create, read, update, and delete tasks. The project demonstrates TDD principles by writing tests before implementing the functionality.

## Features

- Add new tasks with title, description, and due date
- Retrieve all tasks or find specific tasks by ID
- Update existing tasks
- Delete tasks
- Filter tasks by status (TODO, IN_PROGRESS, COMPLETED, CANCELLED)

## Tech Stack

- Java 11
- Gradle for build automation
- JUnit 5 for unit testing

## Getting Started

### Prerequisites

- Java 11 or higher
- Gradle 7.x or higher

### Building the Project

To build the project, run:

```bash
./gradlew build
```

### Running Tests

To run all tests:

```bash
./gradlew test
```

## Project Structure

- `src/main/java/com/ontrack/TaskService.java` - Main service class for task management
- `src/test/java/com/ontrack/TaskServiceTest.java` - JUnit tests for the TaskService

## TDD Approach

This project follows the TDD (Test-Driven Development) methodology:

1. Write a test for the functionality you want to implement
2. Run the test and see it fail (Red phase)
3. Implement the functionality to make the test pass (Green phase)
4. Refactor your code while keeping the tests passing (Refactor phase)
5. Repeat for the next feature

## Contributing

1. Fork the project
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request