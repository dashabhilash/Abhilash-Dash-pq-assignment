# Payconic Stock API

It's a JVM based backend application using REST. That contains the following
endpoints:
    - GET /api/stocks (get a list of stocks)
    - POST /api/stocks (create a stock)
    - GET /api/stocks/1 (get one stock from the list)
    - PATCH /api/stocks/1 (update the price of a single stock)
    - DELETE/api/stocks/1 (delete a single stock)
    
The initial list of stocks are created on application start-up.

## Prerequisite
Docker is required to be install on the machine.

## Installation
1) Clone the Repo & run the following commands.

```bash
docker-compose up
```

## Output

| Endpoint | Method | Body | Description |
|:---:|:---:|:---:|:---:|
| http://localhost:8080/api/stocks | GET | - | Fetch all stocks with pegination (default pageNumber = 0 and pageSize = 5) |
| http://localhost:8080/api/stocks/{id} | GET | - | get one stock from the list |
| http://localhost:8080/api/stocks | POST | { "id":"1", "name":"Stock 1", "currentPrice":"1000" } | update the price of a single stock |
| http://localhost:8080/api/stocks{id}/{currentPrice} | PATCH | - | To update existing record. |
| http://localhost:8080/api/stocks/{id} | DELETE | - | delete a single stock |

```
