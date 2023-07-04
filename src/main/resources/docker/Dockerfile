# 基础镜像
FROM java:8
# 作者
MAINTAINER whitence
# 创建工作目录
RUN mkdir -p /app
# 切换工作目录
WORKDIR /app

ADD ./maven/quick-monomer-1.0.jar /app/app.jar

VOLUME /app/log

EXPOSE 8888

ENTRYPOINT ["java","-jar","/app/app.jar",">","/app/log/quickmonomer.log"]