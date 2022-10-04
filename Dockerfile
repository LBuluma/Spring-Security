FROM tomcat:9.0-jre11

WORKDIR /security

COPY .mvn/ .mvn
COPY mvnw pom.xml
RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["catalina.sh", "run"]