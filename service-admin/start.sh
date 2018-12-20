#!/bin/sh
sh /app/consul.sh $1 &
sh /app/java.sh

#consul_cmd = $(consul agent -client=0.0.0.0 -data-dir=/consul_data/ -node=client_docker_$1 -ui -join 10.0.3.120)
#if [$consul_cmd -eq 0]; then
#    java -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar
#fi
#java -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar
