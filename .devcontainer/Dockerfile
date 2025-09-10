FROM ubuntu:latest

RUN apt-get update \
  && apt-get install -y curl zip unzip git locales-all \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

ENV LANG ja_JP.UTF-8


WORKDIR /developer/ghoul_git

USER ubuntu

RUN curl -s "https://get.sdkman.io" | bash
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install java && sdk install gradle && gradle --version > /dev/null"

