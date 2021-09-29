# Greenplum Database Docs

## Installation

---

1. Create minikube cluster

```sh
minikube start --kubernetes-version=v1.18.10 --cpus='6' --memory='8g' --driver=vmware
```

2. Load Greenplum Operator and Docker Image to current terminal
   ( Download from [Pivotal ](https://network.pivotal.io/))

```sh
eval $(minikube docker-env)

docker load -i ./images/greenplum-for-kubernetes
docker load -i ./images/greenplum-operator
```

3. Install Greenplum k8s template using Helm

```sh
helm install greenplum-operator operator/
```

4. Create Greenplum Cluster

```sh
kubectl apply -f gp-instance.yaml
```

## Notes

---

- View exposed postgres url

```sh
minikube service list
```

- default username, password

```
PGUSER: gpadmin
PGPASSWORD: changeme
```
