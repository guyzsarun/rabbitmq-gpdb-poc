# RabbitMQ-Greenplum-POC

[![Data Generator](https://github.com/guyzsarun/rabbitmq-gpdb-poc/actions/workflows/python-app.yaml/badge.svg)](https://github.com/guyzsarun/rabbitmq-gpdb-poc/actions/workflows/python-app.yaml)
[![RabbitMQ](https://github.com/guyzsarun/rabbitmq-gpdb-poc/actions/workflows/rabbitmq.yaml/badge.svg)](https://github.com/guyzsarun/rabbitmq-gpdb-poc/actions/workflows/rabbitmq.yaml)

## Installation

Clone the repo from Github

```
git clone --recursive https://github.com/guyzsarun/rabbitmq-gpdb-poc.git
```

Separate README is available for [RabbitMQ](https://github.com/guyzsarun/rabbitmq-gpdb-poc/blob/master/rabbitmq/README.md) and [Greenplum Database](https://github.com/guyzsarun/rabbitmq-gpdb-poc/blob/master/gpdb/README.md) in subfolder

## Project Structure

    .
    ├── gpdb                            # Greenplum Database folder
    │   ├── config                      # Greenplum cluster configuration
    │   ├── data                        # Mock Data generator
    │   ├── operator                    # Helm Templates
    │   ├── plots                       # Data Analytics plots
    │   ├── madlib-demo.ipynb
    │   └── README.md
    │
    ├── rabbitmq                        # RabbitMQ folder
    │   ├── hotel-controller-demo       # UI component submodule
    │   ├── spring-rabbitmq-consumer    # RabbitMQ Consumer database connector
    │   ├── spring-rabbitmq-producer    # RabbitMQ producer
    │   ├── docker-compose.yml
    │   └── README.md
    │
    └── README.md

## Usage

To integrate Greenplum with RabbitMQ update the env variables in RabbitMQ [docker-compose](https://github.com/guyzsarun/rabbitmq-gpdb-poc/blob/master/rabbitmq/docker-compose.yml) with Greenplum cluster credentials.

```yaml
ui-service-backend:
  environment:
    PORT: <port> # Update
    USER: "gpadmin" # Update
    PASSWORD: "changeme" # Update
    DATABASE: <database> # Update
    HOST: <greenplum-ip> # Update

spring-consumer:
  environment:
    SPRING_DATASOURCE_URL: jdbc:postgresql://<greenplum-ip>:<port>/<database> # Update
```

## Resources

<img src="https://rabbitmq.com/img/logo-rabbitmq.svg" width="200" align="center"> <br>

[VMware Tanzu RabbitMQ](https://tanzu.vmware.com/rabbitmq)

<img src="https://s3.amazonaws.com/greenplum.org/wp-content/uploads/2020/06/01070542/Greenplum-Horizontal-OnLight.png" width="200">

[VMware Tanzu Greenplum](https://tanzu.vmware.com/greenplum)
