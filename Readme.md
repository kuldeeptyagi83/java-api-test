https://dev.to/francescoxx/java-crud-rest-api-using-spring-boot-hibernate-postgres-docker-and-docker-compose-5cln

```
Make sure docker is installed on your machine
```

```
docker compose up  or
docker-compose up --build.  This command forces a rebuild of the images for the services defined in the docker-compose.yml file before starting the containers. It is useful when you have made changes to the Dockerfile or the application code and want to ensure that the latest changes are included in the images.
```

```
docker run -d --name my_container <image_name>
docker exec -it my_container <command>

docker build --progress=plain --no-cache -f DockerfileKarate -t karate .
docker-compose build --no-cache
docker-compose up

```


