# Game App Backend

Spring Boot API server for the Game Club Management Application.

## Quick Deploy on Render

1. **Create a new Web Service on Render**
2. **Connect this GitHub repository**
3. **Configure:**
   - **Environment**: `Java`
   - **Build Command**: `./mvnw clean package -DskipTests`
   - **Start Command**: `java -jar target/*.jar`
4. **Environment Variables:**
   - `SPRING_PROFILES_ACTIVE` = `production`
   - `SERVER_PORT` = `10000`

## Local Development

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

- `GET /api/members` - Get all members
- `POST /api/members/register` - Register new member
- `POST /api/members/login` - Member login
- `GET /api/games` - Get all games
- `POST /api/games` - Create new game
- `GET /api/collections` - Get all collections
- `POST /api/collections` - Create new collection
- `GET /api/transactions` - Get all transactions
- `POST /api/transactions` - Create new transaction
- `GET /api/recharges` - Get all recharges
- `POST /api/recharges` - Create new recharge
