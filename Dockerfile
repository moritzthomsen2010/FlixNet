FROM eclipse-temurin:21 as builder

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build -x test

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

EXPOSE 8080

VOLUME /tmp

COPY --from=builder /app/build/libs/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
