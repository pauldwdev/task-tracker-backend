FROM openjdk:11
COPY ./target/task-tracker-backend-0.0.1-SNAPSHOT /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch task-tracker-backend-0.0.1-SNAPSHOT'
ENTRYPOINT ["java","-jar","task-tracker-backend-0.0.1-SNAPSHOT"]
ENV JAVA_OPTS="-XX:PermSize=2048m -XX:MaxPermSize=1024m"
 