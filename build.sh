#!/bin/bash
tags="latest"

echo '######开始构建项目######'
git pull
mvn clean package -Dmaven.test.skip=true
echo '######开始构建镜像######'
echo '######开始登陆dockerHub######'
docker login
echo '######开始提交镜像######'
docker buildx build -t  antennababy/localgateway:$tags --platform=linux/arm64,linux/amd64 . --push
