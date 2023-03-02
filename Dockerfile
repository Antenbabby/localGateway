FROM openjdk:17-oracle
RUN rm -f /etc/localtime \
&& ln -sv /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo "Asia/Shanghai" > /etc/timezoneVOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT java -Xmx64m -Djava.security.egd=file:/dev/./urandom -jar /app.jar