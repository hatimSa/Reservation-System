# Restaurant Reservation System

A Spring Boot REST API for managing restaurant reservations, tables, guests, and users.

## 🏗️ Project Structure

```
Reservation-Backend/
├── src/
│   ├── main/
│   │   ├── java/com/WS_Project/
│   │   │   ├── RestaurantReservationApplication.java
│   │   │   └── modules/
│   │   │       ├── user/
│   │   │       │   ├── controller/
│   │   │       │   │   └── UserController.java
│   │   │       │   ├── service/
│   │   │       │   │   └── UserService.java
│   │   │       │   ├── repository/
│   │   │       │   │   └── UserRepository.java
│   │   │       │   ├── entity/
│   │   │       │   │   └── User.java
│   │   │       │   ├── dto/
│   │   │       │   │   ├── request/
│   │   │       │   │   │   └── UserRequestDTO.java
│   │   │       │   │   └── response/
│   │   │       │   │       └── UserResponseDTO.java
│   │   │       │   └── mapper/
│   │   │       │       └── UserMapper.java
│   │   │       ├── restaurant/
│   │   │       │   ├── controller/
│   │   │       │   │   └── RestaurantController.java
│   │   │       │   ├── service/
│   │   │       │   │   └── RestaurantService.java
│   │   │       │   ├── repository/
│   │   │       │   │   └── RestaurantRepository.java
│   │   │       │   ├── entity/
│   │   │       │   │   └── Restaurant.java
│   │   │       │   ├── dto/
│   │   │       │   │   ├── request/
│   │   │       │   │   │   └── RestaurantRequestDTO.java
│   │   │       │   │   └── response/
│   │   │       │   │       └── RestaurantResponseDTO.java
│   │   │       │   └── mapper/
│   │   │       │       └── RestaurantMapper.java
│   │   │       ├── restaurantTable/
│   │   │       │   ├── controller/
│   │   │       │   │   └── RestaurantTableController.java
│   │   │       │   ├── service/
│   │   │       │   │   └── RestaurantTableService.java
│   │   │       │   ├── repository/
│   │   │       │   │   └── RestaurantTableRepository.java
│   │   │       │   ├── entity/
│   │   │       │   │   └── RestaurantTable.java
│   │   │       │   ├── dto/
│   │   │       │   │   ├── request/
│   │   │       │   │   │   └── RestaurantTableRequestDTO.java
│   │   │       │   │   └── response/
│   │   │       │   │       └── RestaurantTableResponseDTO.java
│   │   │       │   └── mapper/
│   │   │       │       └── RestaurantTableMapper.java
│   │   │       ├── guest/
│   │   │       │   ├── controller/
│   │   │       │   │   └── GuestController.java
│   │   │       │   ├── service/
│   │   │       │   │   └── GuestService.java
│   │   │       │   ├── repository/
│   │   │       │   │   └── GuestRepository.java
│   │   │       │   ├── entity/
│   │   │       │   │   └── Guest.java
│   │   │       │   ├── dto/
│   │   │       │   │   ├── request/
│   │   │       │   │   │   └── GuestRequestDTO.java
│   │   │       │   │   └── response/
│   │   │       │   │       └── GuestResponseDTO.java
│   │   │       │   └── mapper/
│   │   │       │       └── GuestMapper.java
│   │   │       └── reservation/
│   │   │           ├── controller/
│   │   │           │   └── ReservationController.java
│   │   │           ├── service/
│   │   │           │   └── ReservationService.java
│   │   │           ├── repository/
│   │   │           │   └── ReservationRepository.java
│   │   │           ├── entity/
│   │   │           │   └── Reservation.java
│   │   │           ├── dto/
│   │   │           │   ├── request/
│   │   │           │   │   └── ReservationRequestDTO.java
│   │   │           │   └── response/
│   │   │           │       └── ReservationResponseDTO.java
│   │   │           └── mapper/
│   │   │               └── ReservationMapper.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/WS_Project/
├── pom.xml
└── README.md
```

## 🚀 Technologies Used

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL**
- **Lombok**
- **Maven**

## 📋 Features

### Core Modules

1. **User Management**
   - User registration and authentication
   - User profile management
   - Restaurant owner management

2. **Restaurant Management**
   - Restaurant registration
   - Restaurant information management
   - Owner-restaurant relationship

3. **Table Management**
   - Table creation and configuration
   - Table status tracking (FREE/RESERVED)
   - Table location management (INDOORS/OUTDOORS)

4. **Guest Management**
   - Guest registration
   - Guest information management

5. **Reservation Management**
   - Reservation creation and management
   - Reservation status tracking (ACTIVE/EXPIRED/CANCELLED)
   - Table and guest association

## 🗄️ Database Schema

## 🏃‍♂️ Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Reservation-Backend
   ```

2. **Set up PostgreSQL database**
   ```sql
   CREATE DATABASE restaurant_reservation;
   ```

3. **Update database configuration**
   Edit `src/main/resources/application.properties` with your database credentials.

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**
   The application will be available at `http://localhost:8080`

## 🧪 Testing

Run tests using Maven:
```bash
mvn test
```

## 🏗️ Architecture

The project follows a **modular architecture** with clear separation of concerns:

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Handles data access
- **Entity Layer**: Represents database entities
- **DTO Layer**: Data transfer objects for API communication
- **Mapper Layer**: Converts between entities and DTOs

## 🔒 Security

- Spring Security integration
- Password encryption
- User authentication and authorization

## 📈 Future Enhancements

- [ ] Email notifications for reservations
- [ ] Payment integration
- [ ] Advanced search and filtering
- [ ] Reservation analytics
- [ ] Website for clients side reservations
- [ ] Mobile app support
- [ ] Real-time table availability updates

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 📞 Support

For support and questions, please contact the development team.
