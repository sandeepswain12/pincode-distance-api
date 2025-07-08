# 📍 Pincode Distance API

This is a Spring Boot REST API that calculates the **distance and duration** between two Indian PIN codes using the **Google Maps Distance Matrix API**.
It also caches the results in a local database to prevent redundant API calls.

---

## 🚀 Features

* ✅ Get distance and duration between two PIN codes
* ✅ Google Maps API integration
* ✅ Response caching using the database
* ✅ Clean code with proper layering (Controller → Service → Repository)
* ✅ Global Exception Handling
* ✅ Logging with SLF4J
* ✅ Modular design using interfaces
* ✅ RESTful and testable structure

---

## ⚙️ Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* H2 Database (can be swapped with MySQL)
* RestTemplate
* Google Maps Distance Matrix API
* SLF4J (Logging)
* Spring Caching

---

## 📂 Project Structure

```
com.pincodedistanceapi
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
│   └── impl
└── config
```

---

## 🔐 Prerequisites

1. Java 21
2. Maven
3. Google Maps API Key ([Get it here](https://console.cloud.google.com/google/maps-apis))

---

## ⚖️ Setup & Run

1. Clone the repo:

```bash
git clone https://github.com/your-username/pincode-distance-api.git
cd pincode-distance-api
```

2. Add the following in your `application.properties`:

```properties
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
google.maps.api.key=YOUR_GOOGLE_API_KEY
```

3. Run the application:

```bash
./mvnw spring-boot:run
```

By default, it runs on: `http://localhost:8080`

---

## 📬 API Endpoint

### `POST /api/distance`

#### 🔹 Request Body:

```json
{
  "from": "110001",
  "to": "560001"
}
```

#### 🔹 Success Response:

```json
{
  "from": "110001",
  "to": "560001",
  "distance": "2,146 km",
  "duration": "36 hours 20 mins",
  "routeDetails": "{...}"
}
```

#### 🔺 Error Response (if no route found):

```json
{
  "message": "Google API call failed: No route found between 754132 and 754323",
  "timestamp": "2025-07-08T14:26:28.6798003",
  "status": 400
}
```

---

## 🧪 Test With Valid PINs

These test inputs return valid results:

| From   | To     | Description            |
| ------ | ------ | ---------------------- |
| 110001 | 560001 | Delhi to Bangalore     |
| 751001 | 700001 | Bhubaneswar to Kolkata |

---

## 📝 Note

* The app uses caching — if a route is already stored in DB, it won’t hit Google API again.
* If Google Maps can’t find a route, a `400 Bad Request` with a custom error message is returned.
* Be careful to not exceed free tier limits of Google Maps API.

---

## 🔒 .gitignore Suggestion

Ensure you **do not push your API key** to GitHub. Add this to `.gitignore`:

```
application.properties
*.log
/target
```

---

## 👨‍💼 Author

**Sandeep Swain**
*Java Backend Developer | Spring Boot Enthusiast*

---

