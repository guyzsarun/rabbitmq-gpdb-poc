# Overview Architecture

## System Flow

_under constrcution... waiting for the flow diagram_

# Prerequisite

Please make sure that you have follow **all the instructions.**

Build all RabbitMQ services with docker-compose

```
docker-compose build
docker-compose up
```

or setup the service manually

## 1. Setup Docker for RabbitMQ

Start your container (_`my-rabbit`_ is the name of container, _be able to modify_)

```
docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management
```

## 2. Start RabbitMQ Producer and Consumer

RabbitMQ producer has responsible for receiving data from APIs to the queue and RabbitMQ consumer pass those data from queue and store data to PostgreSQL database. Therefore, we must start producer and cosumer respectively.

### 2.1. Start RabbitMQ Producer

**Producer** will run on port `8080`
Get inside folder [/rabbitmq/spring-rabbitmq-producer/](https://github.com/guyzsarun/rabbitmq-gpdb-poc/tree/master/rabbitmq/spring-rabbitmq-producer) and **run Java file** [SpringRabbitmqProducerApplication.java](https://github.com/guyzsarun/rabbitmq-gpdb-poc/blob/master/rabbitmq/spring-rabbitmq-producer/src/main/java/com/example/rabbitmq/springrabbitmqproducer/SpringRabbitmqProducerApplication.java)

### 2.2 Start RabbitMQ Consumer

**Consumer** will run on port `8081`
Get inside folder [/rabbitmq/spring-rabbitmq-consumer/](https://github.com/guyzsarun/rabbitmq-gpdb-poc/tree/master/rabbitmq/spring-rabbitmq-consumer) and **run Java file** [SpringRabbitmqConsumerApplication.java](https://github.com/guyzsarun/rabbitmq-gpdb-poc/blob/master/rabbitmq/spring-rabbitmq-consumer/src/main/java/com/example/rabbitmq/springrabbitmqconsumer/SpringRabbitmqConsumerApplication.java)

## 3. Generate Data Using Postman API

The API document is already provided on _[Postman private workspace](https://vmware-squad.postman.co/workspace/fc3299f2-8526-48f7-84f6-42ad6423b545)_. **For grant access, please contact directly to contributors.**

## 4. Start Web application (Backend)

Hotel controller have to communicate with PostgreSQL database to retrieve data and show on webapp. Thus, [**expressJS**](https://expressjs.com) will take responsible to complete this workflow. Go to folder [express-progresql](https://github.com/Raksani/hotel-controller-demo/tree/main/express-postgres) and run these commands:

```
npm install express --save
node index.js
```

App will be available on port `3001`

## 5. Start Web application (UI)

Hotel controller is made for interacting with users, we have built the web application using [**React**](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjBwNz9nOXzAhV6yzgGHUqmCiwQFnoECAkQAQ&url=https://reactjs.org/&usg=AOvVaw26YbpVhaFnAB4A6G8-4uAs) framework. To start the web application, go to repo [hotel-controller-demo](https://github.com/Raksani/hotel-controller-demo) and get inside [hotel-controller-ui](https://github.com/Raksani/hotel-controller-demo/tree/main/hotel-controller-UI) folder and **execute these commands**:

```
yarn install
yarn build
yarn start
```

App will be available on port `3000`
_\*\*For more details about `yarn` commands, please visit [here](https://github.com/Raksani/hotel-controller-demo/tree/main/hotel-controller-UI#readme)._
