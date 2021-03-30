FROM tomee:8-jdk-7.0.1-plus

MAINTAINER corentin artaud (corentin.luc.artaud@gmail.com)

# set workdir to the tomee directory 
WORKDIR /usr/local/tomee/

# copy war to webapps directory
COPY target/teami-polydiploma.war ./webapps/.

# config tomee
# Creating the tomee/eemot user to access to the web admin console
COPY ./dockerresources/tomcat-user.xml ./conf/tomcat-users.xml

# Allow one to access to the manager from outside the container
COPY ./dockerresources/manager-context.xml ./webapps/manager/META-INF/context.xml

# url of hyperplanning
# hyperplanning_host
# hyperplanning_port
ENV hyperplanning_host=localhost
ENV hyperplanning_port=9090

# health check (every 5 seconds, ping the service, fail silently)
HEALTHCHECK --interval=5s CMD curl --fail http://localhost:8080/ || exit 1

# expose the right port (tomee)
EXPOSE 8080

# run catalina
ENTRYPOINT ["catalina.sh", "run"]