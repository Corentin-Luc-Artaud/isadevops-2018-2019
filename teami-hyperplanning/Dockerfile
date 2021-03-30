FROM mono:5.18.0.225 
# version of mono known as stable

MAINTAINER Corentin Artaud (corentin.luc.artaud@gmail.com)

# exposing the port 9090
EXPOSE 9090 9090/tcp

# to prevent removing somthing important in the based image
WORKDIR /opt/hyper.planning

# ADD the service
Add service.exe ./

# ADD firstnames and lastnames data.
ADD ressources ./ressources/ 

# run the service in standalone mod listening on port 9090
ENTRYPOINT ["mono", "service.exe", "--url", "http://*:9090", "--standalone"]