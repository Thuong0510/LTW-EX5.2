# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests package

# copy source code
COPY src ./src

# build ra file WAR
RUN mvn -q -DskipTests package

# ---- Run stage ----
FROM tomcat:10.1.44-jdk17

# Xóa webapps mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# copy WAR thành ROOT.war (đổi tên để deploy làm app chính)
COPY --from=build /app/target/web4_5_1-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
