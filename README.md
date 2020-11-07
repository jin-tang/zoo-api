# Getting Started
1. Download Java 15
    * `brew install java`
    * `sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk`
    * `java --version`
1. Clone the repo: https://github.com/elliemiller5/zoo-api.git
1. Set up SQL Server Docker container
```
docker run --name sql-server -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=P4ssword' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2019-latest
```
1. Create local Microsoft SQL Server (name: zoo-database, port: 1433, user: sa, pass: P4ssword)
    * Create DB instance (zoo-database) off SQL Server instance
1. Set application VM arguments `-Dspring.profiles.active=development`


### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/gradle-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/reference/htmlsingle/#howto-execute-liquibase-database-migrations-on-startup)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

