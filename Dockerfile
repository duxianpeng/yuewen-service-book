FROM docker-enterprise-prod-local.artifactrepository.yuewen.net/cate-citicloud-java/oracle-jre-rhel6:1.8.0_7
MAINTAINER yuewen
ARG SPRING_PROFILE
ARG WORK_PATH=/app
ARG SERVICE_NAME=yuewen-service-recommend
ARG PORT=2111

ENV APP_EXECUTABLE_JAR=/tmp/$SERVICE_NAME.jar

VOLUME $WORK_PATH
EXPOSE $PORT

ADD target/$SERVICE_NAME-1.0-SNAPSHOT.jar $APP_EXECUTABLE_JAR

RUN sh -c 'touch $APP_EXECUTABLE_JAR'
WORKDIR $WORK_PATH

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar $APP_EXECUTABLE_JAR" ]
