# Portfolier

Construct your own IT skill portfolio.

## How to execute(developing)

```sh
mvn clean install exec:java
```

or

```sh
mvn clean install assembly:single
unzip target/portfolier-apl.zip -d target/tmp
java -jar target/tmp/portfolier.jar
```

## TODO

- SHA256 encoding
- load application configurations from .config file
- Change Database
- Implement input validation

