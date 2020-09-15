# FROM gradle:5.4.0-jdk8-alpine
FROM gradle:6.5.1-jdk8
ADD --chown=gradle . /code
WORKDIR /code
RUN gradle clean build -x test
CMD gradle bootRun
