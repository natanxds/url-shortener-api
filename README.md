# URL Shortener

This is a URL shortener service built with Java, Spring Boot, and MongoDB. It provides a simple API to shorten URLs and then redirect to the original URL when accessed.

## Technologies Used

- Java 17
- Spring Boot 3.3.3
- MongoDB 4.0
- Maven
- Docker

## API Endpoints

### POST /urls

This endpoint is used to create a shortened URL.

**Request Body:**

```json
{
    "url": "https://example.com"
}
```

**Response:**

```json
{
    "url": "http://localhost:8080/abc123"
}
```

### GET /{id}

This endpoint is used to redirect to the original URL using the shortened URL.

**Response:**

Redirects to the original URL.

## How It Works

1. The client sends a POST request to `/urls` with the original URL in the request body.
2. The server generates a unique ID for this URL and stores it in the MongoDB database.
3. The server responds with the shortened URL.
4. When the client sends a GET request to the shortened URL, the server looks up the original URL in the database using the unique ID and redirects the client to the original URL.

## Examples

**Create a shortened URL:**

```bash
curl -X POST -H "Content-Type: application/json" -d '{"url":"https://example.com"}' http://localhost:8080/urls
```

**Use the shortened URL:**

Open `http://localhost:8080/abc123` in a web browser.

## Setup and Run

1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn clean install` to build the project.
4. Run `docker-compose up` to start the MongoDB server.
5. Run `mvn spring-boot:run` to start the application.

The application will be running at `http://localhost:8080`.
