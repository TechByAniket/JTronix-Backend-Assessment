# Employee Management REST API

A RESTful Employee Management API built using **Java 21 and Spring Boot** for the JTRONIX Backend Internship Assessment.

The API supports retrieving employees, creating new employees, updating employee status, and filtering employees by department.

## Features

- Retrieve all employees and an employee by ID.
- Create employees with automatic ID generation.
- Default new employee status to `Active`.
- Update employee status to `Active` or `Inactive`.
- Filter employees by department.
- Validate required fields and reject invalid status values.
- Return appropriate HTTP status codes and readable error messages.
- Store employee data in memory using a thread-safe collection.

## Tech Stack

- Java 21
- Spring Boot
- Maven
- Spring Web
- Jakarta Bean Validation

## Getting Started

### Prerequisites

- Java 21 or compatible configured JDK
- Maven, or the Maven Wrapper included in the project
- Postman (for API testing)

### Run Locally

1. Clone the repository:

   ```bash
   git clone <YOUR_GITHUB_REPOSITORY_URL>
   ```

2. Navigate to the project directory:

   ```bash
   cd employee-api
   ```

3. Run the application using the Maven Wrapper:

   **Windows:**
   ```bash
   .\mvnw.cmd spring-boot:run
   ```

   **macOS/Linux:**
   ```bash
   ./mvnw spring-boot:run
   ```

   Alternatively, if Maven is installed:

   ```bash
   mvn spring-boot:run
   ```

4. The API will be available at:

   `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/employees` | Retrieve all employees |
| GET | `/employees/{id}` | Retrieve an employee by ID |
| POST | `/employees` | Create a new employee |
| PATCH | `/employees/{id}/status` | Update employee status |
| GET | `/employees?department=Engineering` | Filter employees by department |

## Example Requests and Responses

### 1. Get All Employees

**Request**

```http
GET /employees
```

**Response — `200 OK`**

```json
[
  {
    "id": 1,
    "name": "Rahul Sharma",
    "department": "Engineering",
    "status": "Active"
  },
  {
    "id": 2,
    "name": "Priya Patel",
    "department": "HR",
    "status": "Active"
  },
  {
    "id": 3,
    "name": "Amit Verma",
    "department": "Engineering",
    "status": "Inactive"
  }
]
```

### 2. Get Employee by ID

**Request**

```http
GET /employees/1
```

**Response — `200 OK`**

```json
{
  "id": 1,
  "name": "Rahul Sharma",
  "department": "Engineering",
  "status": "Active"
}
```

If the employee does not exist, the API returns `404 Not Found`.

### 3. Create Employee

**Request**

```http
POST /employees
Content-Type: application/json
```

```json
{
  "name": "Neha Singh",
  "department": "Engineering"
}
```

**Response — `201 Created`**

```json
{
  "id": 4,
  "name": "Neha Singh",
  "department": "Engineering",
  "status": "Active"
}
```

The ID is generated automatically, and the status defaults to `Active`.

Both `name` and `department` are required. Empty or whitespace-only values are rejected with `400 Bad Request`.

### 4. Update Employee Status

**Request**

```http
PATCH /employees/1/status
Content-Type: application/json
```

```json
{
  "status": "Inactive"
}
```

**Response — `200 OK`**

```json
{
  "id": 1,
  "name": "Rahul Sharma",
  "department": "Engineering",
  "status": "Inactive"
}
```

The API accepts `Active` and `Inactive` case-insensitively and normalizes the stored value. For example, `"  inactive  "` becomes `"Inactive"`.

Any other status returns `400 Bad Request`. A nonexistent employee ID returns `404 Not Found`.

### 5. Filter Employees by Department

**Request**

```http
GET /employees?department=Engineering
```

**Response — `200 OK`**

Returns only employees belonging to the Engineering department. Department matching is case-insensitive.

If no employees match, the API returns an empty array:

```json
[]
```

## HTTP Status Codes

| Status Code | Meaning |
|---|---|
| `200 OK` | Request completed successfully |
| `201 Created` | Employee created successfully |
| `400 Bad Request` | Invalid input or validation failure |
| `404 Not Found` | Employee does not exist |

## Data Storage

Employee records are stored in an in-memory Java collection. The initial dataset contains three sample employees.

**Note:** Data is not persisted between application restarts. A database is not required for this assessment.

## API Testing

The endpoints can be tested using Postman.

Recommended test cases:

- Retrieve all employees.
- Retrieve an existing and a nonexistent employee.
- Create an employee with valid data.
- Attempt creation with missing or blank fields.
- Update status using `Active`, `Inactive`, and different capitalization.
- Attempt to update status with an invalid value.
- Filter employees by department, including different capitalization.
- Filter by a department with no matching employees.


**Aniket Khemnar**

