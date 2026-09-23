# 🎟️ Movie Ticket Booking System 🎟️

Welcome to the **Movie Ticket Booking System**! This repository contains the back-end API for the booking system, with comprehensive documentation available via Swagger UI. Feel free to contribute to the front-end design.

## Technologies Used 🛠️

* **Spring Boot**: For building the back-end application.
* **REST API**: To expose endpoints for various functionalities.
* **PostgreSQL**: As the relational database management system.
* **Maven**: For project management and dependency management.
* **Spring Security**: Securing all the endpoints with basic authentication.
* **Docker**: For containerizing and running the application.

## How to Set Up 🚀

There are two ways to run the application: **locally using Maven** or **using Docker**.

### Option 1: Run Locally 💻

#### 1. Fork the Repository 🍴

Click the **Fork** button at the top right corner of this repository page to create your own copy of the repository.

#### 2. Clone the Forked Repository 📂

Open your terminal or Git Bash and clone the repository:

```bash
git clone https://github.com/<your-username>/movie-ticket-booking-system.git
```

#### 3. Import the Maven Project 📦

Open your preferred IDE (such as IntelliJ IDEA or Eclipse).

Import the cloned repository as a Maven project and allow the IDE to download all the required Maven dependencies.

#### 4. Configure the Database 🛠️

Open the `application.properties` file located in:

```text
src/main/resources/application.properties
```

Configure the PostgreSQL database connection properties according to your local setup.

#### 5. Run the Project ▶️

Start the application by running the main Spring Boot class.

Ensure that the application starts successfully without any errors.

---

### Option 2: Run Using Docker 🐳

Make sure **Docker** is installed and running on your system.

#### 1. Pull the Docker Image 📥

Pull the pre-built application image from Docker Hub:

```bash
docker pull hardikdev98/bookmyshow-movie-ticket-booking-system:latest
```

#### 2. Run the Docker Container 🚀

```bash
docker run -d \
  --name movie-ticket-booking-system \
  -p 8080:8080 \
  hardikdev98/bookmyshow-movie-ticket-booking-system:latest
```

The application will now be available at:

```text
http://localhost:8080
```

> **Note:** The application requires a PostgreSQL database. Make sure PostgreSQL is available and the application's database configuration points to the correct database host and credentials.

---

## Access API Documentation 📖

Once the application is running, open Swagger UI in your browser:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger UI provides interactive documentation for all available REST API endpoints.

## Authentication Details 🔑

The endpoints are secured using **Spring Security** with basic authentication.

Use the following credentials:

* **Username:** `admin`
* **Password:** `admin`

> ⚠️ For production environments, use secure credentials instead of the default development credentials.

## License 📄

[MIT License](LICENSE)

## Contribution 🤝

We welcome contributions to improve and enhance the system. Whether it's fixing bugs, improving existing functionality, or adding new features, your efforts are appreciated! 🎉

---

Thank you for your interest in the **Movie Ticket Booking System**.

**Happy coding! 🚀**
