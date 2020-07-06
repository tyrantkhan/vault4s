FROM eed3si9n/sbt:jdk11-alpine

RUN apk add curl
WORKDIR /usr/src/app

ENTRYPOINT ["/usr/src/app/vault/scripts/testrunner.sh"]