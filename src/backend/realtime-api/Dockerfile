# Maven aşaması
FROM maven:3.8.4-openjdk-17 AS maven-builder
COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

# Java çalışma zamanı aşaması
FROM openjdk:17
COPY --from=maven-builder /app/target/realtime-app-0.0.1-SNAPSHOT.jar /app/realtime-app-0.0.1-SNAPSHOT.jar
# resources dosyalarını container'a ekleyin
COPY src/main/resources /app/resources

WORKDIR /app

EXPOSE 8383

# Active profile'ı çevre değişkeni ile al
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=${PROFILE} /app/realtime-app-0.0.1-SNAPSHOT.jar"]
