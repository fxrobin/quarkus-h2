[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/fxrobin/quarkus-h2)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/694254e5b1e845d7abf19de55bb17523)](https://www.codacy.com/gh/fxrobin/quarkus-h2/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fxrobin/quarkus-h2&amp;utm_campaign=Badge_Grade)
![Java CI with Maven](https://github.com/fxrobin/quarkus-h2/workflows/Java%20CI%20with%20Maven/badge.svg)

# quarkus-h2

Tiny REST API with Quarkus and H2

## How to run in dev mode

```bash
$ mvn quarkus:dev
```

## How to run in JVM mode

:warning: JDK 11 is needed

```bash
$ mvn package
$ java -jar target/quarkus-h2-0.0.1-SNAPSHOT-runner.jar
```

## How to build and run the native image 

:warning: cannot be used with gitpod. Please use real computer instead.

```bash
$ mvn package -Pnative
$ ./target/quarkus-h2-0.0.1-SNAPSHOT-runner
```
