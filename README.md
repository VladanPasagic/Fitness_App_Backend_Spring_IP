# Spring Boot Application (Backend)

## **Overview**
The **Spring Boot Application** serves as the backend of the online fitness platform. It handles RESTful services for fitness programs, user authentication, and payment systems.

### **Features**
1. **Fitness Program Management**:
   - Handle CRUD operations for fitness programs including attributes like name, description, category, and instructor information.
   - Programs can be filtered by category, price, and other attributes.
   - Pagination (e.g., classic or virtual scroll) is implemented for program listings.

2. **User Management**:
   - REST API endpoints for user registration, login (with two-factor authentication), and profile management.
   - Users can view their previous participations, purchase history, and change personal details.

3. **Payment and Participation**:
   - Supports basic payment methods like credit card and PayPal.
   - Users can register for fitness programs, either online or in person.

4. **Exercise Suggestions**:
   - The system integrates with an external API to provide daily workout suggestions for registered users.

### **Implementation Details**
- **Spring Boot** is used for backend development with RESTful services exposed to the frontend.
- User authentication is handled with JWT tokens, and two-factor authentication is required.
- Use **Spring Security** for securing the application, including user authentication and authorization.
- Utilize **Spring Data JPA** for interaction with the database.
