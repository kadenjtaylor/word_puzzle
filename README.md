# Generic Word Puzzle Game

A dockerized Java project for playing a word guessing game in the terminal using Maven and JUnit.

## Project Structure
```
├── src/
│   ├── main/java/com/puzzles/word
│   │   ├── Game.java
│   │   ├── Round.java
│   │   ├── Utils.java
│   │   └── Turn.java
│   └── test/java/com/puzzles/word
│       ├── RoundTest.java
│       └── UtilsTest.java
├── pom.xml                         # Maven project configuration
├── Dockerfile                      # Docker configuration
├── build_docker.sh                 # Docker build script
├── run_tests.sh                    # Test execution script
└── README.md                       # This file
```

## Features

- **Java 17** with Maven build system
- **JUnit** for unit testing
- **AssertJ** for fluent assertions
- **Docker** support for containerized testing

### Docker Usage

1. **Build the Docker image**:

   ```bash
   ./build_docker.sh [image-name] [platform]
   ```

   Examples:

   ```bash
   ./build_docker.sh                           # Uses default: word-puzzle-game
   ./build_docker.sh my-java-app               # Custom image name
   ```

2. **Run tests in Docker**:

   ```bash
   ./build_docker.sh my-java-app
   docker run --rm my-java-app ./run_tests.sh
   ```

3. **Interactive Docker session**:

   ```bash
   docker run -it word-puzzle-game
   ```

   Inside the container, you can run:

   ```bash
   mvn test                         # Run all tests
   mvn test -Dtest=YourTestClass     # Run specific test class
   ./run_tests.sh                    # Alternative test runner
   mvn exec:java -Dexec.mainClass="your.package.YourMainClass"  # Run app
   ```