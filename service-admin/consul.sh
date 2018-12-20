#!/bin/sh
consul agent -client=0.0.0.0 -data-dir=/consul_data/ -node=client_docker_clover1 -ui -join 10.0.3.120
