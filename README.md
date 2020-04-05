# Demo-App : Building Scalable, Containerised and Secure Microservices

#### Stack

- Spring security
- Spring cloud Netflix - Zuul
- [JWT](https://jwt.io/introduction/)
- OpenJDK13

#### Modules

##### 1. **api-auth**

The service to issue the `JWT` token.

- The client POST `{username,password}` to `/login`.
- This service will authenticate the username and password via `Spring Security`,
  generate the token, and issue it to client.

##### 2. **api-demo**

Provide three simple services:
- `http:localhost:8082/service1/test1?param1=Hello`
- `http:localhost:8082/service2/test2`
- `http:localhost:8082/service3/test3`
 
##### 3. **api-gateway**

The `Zuul` gateway:

- Define `Zuul` routes to `api-auth` and `api-demo`.
- Verify `JWT` token.
- Define role-based auth via `Spring Security`:
    - `/login` is public to all.
    - `/api/**` can only be accessed by role `ADMIN` & `USER`.

#### Run and Verify

##### 1. Compile and package
```bash
mvn clean package
```

##### 2. Start services

```bash
java -jar api-auth/target/api-auth-1.0.0.jar
java -jar api-demo/target/aoi-demo-1.0.0.jar
java -jar api-gateway/target/api-gateway-1.0.0.jar
```

##### 3. Get token
```bash
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"demo","password":"demo"}' http://localhost:8080/login
```
You will see the token in response header for user `demo`. 
Note that the status code `401` will be returned if you provide incorrect username or password. 
And similarly, get token for user `admin`:

```bash
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"admin","password":"admin"}' http://localhost:8080/login
```

The user `admin` is defined with two roles: `USER` and `ADMIN`, while `demo` is only a `USER`.


##### 4. Verify Token

The general command to verify if the auth works is as follows:

```bash
curl -i -H "Authorization: Bearer token-from-step-3" http:localhost:8080/service1/test1?param1=Hello
```
or without token:

```bash
curl -i http:localhost:8080/service1/test1?param1=Hello
```

#Container orchestration

Using [kompose](https://github.com/kubernetes/kompose) tool to build Kubernetes resources from Docker Compose file.

Convert docker compose file to Kubernetes resources for deployment

```bash
kompose convert -f /docker-compose/docker-compose.yaml
```
