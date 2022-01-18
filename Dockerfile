FROM jenkins/jenkins:lts

WORKDIR /app

USER root

COPY jenkins .

COPY script .

COPY images .

ENV DOCKER_HOST unix:///var/run/docker.sock

RUN /usr/local/bin/install-plugins.sh \
    cloudbees-folder                  \
    configuration-as-code             \
    credentials                       \
    github                            \
    job-dsl                           \
    script-security                   \
    structs                           \
    role-strategy                     \
    ws-cleanup

RUN apt-get update && apt-get install -y docker.io