# file-export
A file export benchmark


## Requisites

- docker

## Instalation

1.- Run command

```
./stack.sh start
```

2.- Add to your host file

```
127.0.0.1 app.test
127.0.0.1 api.app.test
```

3.1- Install benchmark db on windows

```
./stack.sh sshw
mysql -h mariadb -u root < apps/test-db/employees.sql
```

then type root password "root"


3.2- Install benchmark db on linux

```
./stack.sh ssh
mysql -h mariadb -u root < apps/test-db/employees.sql
```

then type root password "root" 😎

4.- Install angular npm modules

```
./stack.sh npm install
```

5.- Compile angular client

```
./stack.sh ng build
```