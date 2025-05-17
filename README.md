# Menu Planner Application

A Spring Boot application for meal planning with GraphQL API.

## Features

- GraphQL API for meal planning
- PostgreSQL database with Liquibase migrations
- Docker containerization with Jib
- Code quality analysis with Quodana
- CI/CD with GitHub Actions

## Development

### Prerequisites

- Java 21
- Maven
- Docker and Docker Compose (for local development)

### Running Locally

1. Clone the repository
2. Start the database with Docker Compose:
   ```
   docker-compose up -d
   ```
3. Run the application:
   ```
   mvn spring-boot:run
   ```

### Building

Build the application with Maven:

```
mvn clean package
```

## Containerization with Jib

This project uses [Jib](https://github.com/GoogleContainerTools/jib) for building Docker images without requiring a Docker daemon.

### Building a Docker Image Locally

```
mvn jib:dockerBuild
```

### Pushing to a Container Registry

```
mvn jib:build
```

The default configuration pushes to GitHub Container Registry (ghcr.io).

## Code Quality with Quodana

This project uses [Quodana](https://www.jetbrains.com/qodana/) for code quality analysis.

### Running Quodana Locally

```
docker run --rm -it -p 8080:8080 \
  -v $(pwd):/data/project \
  -v $(pwd)/qodana-reports:/data/results \
  jetbrains/qodana-jvm --show-report
```

Then open http://localhost:8080 in your browser.

## CI/CD with GitHub Actions

This project includes GitHub Actions workflows for:

1. **Building and Publishing Docker Images** (.github/workflows/build-and-publish.yml)
   - Builds the application
   - Creates a Docker image with Jib
   - Pushes the image to GitHub Container Registry (on main branch)

2. **Code Quality Analysis** (.github/workflows/code-quality.yml)
   - Runs Quodana analysis
   - Uploads results to GitHub

### Required Secrets

- `QODANA_TOKEN` - For Quodana Cloud integration (optional)

### Repository Settings for GitHub Container Registry

To allow GitHub Actions to push to GitHub Container Registry, you need to configure the following repository settings:

1. Go to your repository's **Settings** > **Actions** > **General**
2. Under **Workflow permissions**, select **Read and write permissions**
3. Check **Allow GitHub Actions to create and manage pull requests**
4. Click **Save**

This ensures that the GitHub Actions workflow has the necessary permissions to push Docker images to GitHub Container Registry.

## License

[MIT](LICENSE)
