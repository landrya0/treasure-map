# Carte au Trésor

## Requirements
* Java 16.0.1 (or later)
* Maven 3.6.3 (or later)

## Build and Run Tests
* Build and Test
```shell
mvn clean intall
```

* The Jar file is located in the following path:
```shell
<project-dir>/target/treasure-map-0.0.1-SNAPSHOT.jar
```
## Run
* Run jar file with your input-file

```shell
java -jar --enable-preview treasure-map-0.0.1-SNAPSHOT.jar <path-to-your-input-file>
```
* See the result in output file
```shell
cat output-file.txt
```

## Understand the code
The application entry Class is:  ``it.carbon.exercice.treasuremap.application.AppRunner``




