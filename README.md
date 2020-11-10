# Getting Started
1. Download Java 15
    * `brew install java`
    * `sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk`
    * `java --version`
    * If Java home is set to an older version, find where the JDK was installed and update your JAVA_HOME
    * `/usr/libexec/java_home -V`
    * `export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-15.jdk/Contents/Home` (update path to be appropriate path to 15)
1. Clone the repo: https://github.com/elliemiller5/zoo-api.git
1. Set up SQL Server Docker container
```
docker run --name sql-server -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=P4ssword' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2019-latest
```
1. Set up your local database instance via command line
    * `brew tap microsoft/mssql-release https://github.com/Microsoft/homebrew-mssql-release`
    * `brew install msodbcsql mssql-tools` 
    * `sqlcmd -S localhost -U sa -P P4ssword`
    * `CREATE DATABASE zooDatabase`
    * `GO`
1. Check that File > Project Structure > Project SDK and Project language level are both on Java 15  
1. Check that Preferences > Build, Execution, Deployment > Build Tools > Gradle JVM is on Java 15
1. Set application VM arguments `-Dspring.profiles.active=development`
1. Run the application
    * In Postman or a web browser, hit `http://localhost:8080/animals/ping` to verify the app is running


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

