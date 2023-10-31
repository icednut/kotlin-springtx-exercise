## kotlin-spring-tx-exercise
Kotlin의 Context Receiver를 이용한 Spring @Transacional 연습

### Build
```
$ ./gradlew build
```

### Run
```
$ ./gradlew bootRun
```

### Test
```
$ ./gradlew test
```

### API Test
#### 1. User 생성
```
$ curl -X POST -H "Content-Type: application/json" -d '{"name":"test"}' http://localhost:8080/user
```

#### 2. User 조회
```
$ curl -X GET http://localhost:8080/user\?userId\=1
```