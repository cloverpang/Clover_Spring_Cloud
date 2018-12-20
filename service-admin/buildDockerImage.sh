#!/usr/bin/env bash

set -eo pipefail

modules=( service-admin )

for module in "${modules[@]}"; do
    docker build -t "${module}:latest" ${module}
done