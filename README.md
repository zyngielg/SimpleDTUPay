# DTUSimplePay Group17

## Group 17 members

- Adrienne Cohrt
- Matej Majtan
- Gustaw Żyngiel
- Viktor Tsanev
- Marta Pacuszka
- Jerry Chen


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvn compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:

```shell script
./mvn package
```

It produces the `dtu-pay-1.0.0-SNAPSHOT.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvn package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/dtu-pay-1.0.0-SNAPSHOT-runner.jar`.
