docker stop PostgresForWs
docker container rm PostgresForWs
docker run -d --name PostgresForWs -p 5432:5432 -e POSTGRES_PASSWORD=itmo_ws -e POSTGRES_USER=itmo_ws -e POSTGRES_DB=persons postgres:11.5-alpine
timeout 3 > NUL
type dbInit.sql | docker exec -i PostgresForWs psql -U itmo_ws -d persons
