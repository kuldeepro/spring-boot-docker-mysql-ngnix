docker container run --name tenet-mysql --network tenet-network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=user1 -e MYSQL_PASSWORD=user1 -e MYSQL_DATABASE=tenet_app -p 3306:3306 -d mysql:5.7

while ! docker exec tenet-mysql mysqladmin --user=root --password=root --host "127.0.0.1" ping --silent &> /dev/null ; do
    echo "Waiting for database connection..."
    sleep 2
done

./mvnw clean install -DskipTests
docker-compose up --build
