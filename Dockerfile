FROM openjdk:11.0-jdk AS builder

WORKDIR /opt

COPY . .

RUN ./gradlew clean build


FROM openjdk:11.0-jre

ARG JAVA_PARAM="-Xms256M -Xmx1024M -XX:+UseG1GC"
ENV JAVA_PARAM $JAVA_PARAM

WORKDIR /opt/app

COPY --from=builder /opt/build/libs/mobile-1.0.0-SNAPSHOT-plain.jar /opt/app/app.jar

RUN chown nobody -R /opt/*

USER 65534

EXPOSE 8080

CMD ["sh", "-c",  "java ${JAVA_PARAM} -jar /opt/app/app.jar"]