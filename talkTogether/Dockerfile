# 1단계: Gradle로 jar 빌드
FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace
COPY gradlew build.gradle setting.gradle ./
COPY gradle gradle
RUN chmod +x gradlew && ./gradlew --no-daemon dependencies > /dev/null
COPY src src
# 기존 contextLoads 테스트는 실행 중인 Postgres 가 필요해서 이미지 빌드에서는 건너뛴다
RUN ./gradlew --no-daemon bootJar -x test

# 2단계: JRE만 담은 실행 이미지
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar app.jar
EXPOSE 20301
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
