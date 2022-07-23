FROM openjdk:8
COPY ./target/blog-app-apis-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
CMD ["java", "-jar", "blog-app-apis-0.0.1-SNAPSHOT.jar"]
