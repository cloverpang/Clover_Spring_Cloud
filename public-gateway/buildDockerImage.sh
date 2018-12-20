#!/usr/bin/env bash

set -eo pipefail

modules=( public-gateway )

for module in "${modules[@]}"; do
    docker build -t "${module}:latest" ${module}
done