# Portfolier

Construct your own IT skill portfolio.

## How to execute(developing)

```sh
mvn clean install && mvn assembly:single
unzip target/portfolier-apl.zip -d target/tmp
java -jar target/tmp/portfolier.jar
```

