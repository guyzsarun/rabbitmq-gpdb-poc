# Greenplum Database Docs

## Installation

1. Create minikube cluster

```sh
minikube start --kubernetes-version=v1.18.10 --cpus='6' --memory='8g' --driver=vmware
```

2. Load Greenplum Operator and Docker Image to current terminal
   ( Download from [Pivotal](https://network.pivotal.io/))

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
kubectl apply -f ./config/gp-instance.yaml
```

## Notes

- View exposed postgres url

```sh
minikube service list
```

- default username, password

```
PGUSER: gpadmin
PGPASSWORD: changeme
```

- View database oid

```sql
SELECT oid, datname FROM pg_database;

  oid  |  datname
-------+-----------
     1 | template1
 12815 | template0
 12818 | postgres
 16384 | gpadmin
 16411 | db
 16419 | gpperfmon
(6 rows)
```

- View data distribution across segment

```sh
gpssh -f ~/hosts -e "du -b /greenplum/data/base/<oid>" | \
    grep -v "du -b" | sort | \
    awk -F " " '{ arr[$1] = arr[$1] + $2 ; tot = tot + $2 }; END \
    { for ( i in arr ) print "Segment node" i, arr[i], "bytes (" arr[i]/(1024^3)" GB)"; \
    print "Total", tot, "bytes (" tot/(1024^3)" GB)" }' -
```

- Enable PL/python support

```sh
psql -d <db-name> -c 'CREATE EXTENSION plpythonu;'
```
