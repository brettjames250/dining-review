# Dining Review API

## Quick endpoint testing
curl http://localhost:3001/users

curl http://localhost:3001/restaurants

curl http://localhost:3001/dining-review

See further integrations tests under src/test/java/com/example/diningreview/tests
## H2 Database
This project has a H2 database implemented for unit/integration testing. It can be configured in two different ways:

- In memory
```
spring.datasource.url = jdbc:h2:mem:testdb
spring.jpa.defer-datasource-initialization = true
```
- Persist (stored in a file)
```
spring.datasource.url = jdbc:h2:file:./data
```
## Integration testing

Integration tests are run by using the maven-failsafe-plugin (below).
- RestAssured used as HTTP client
- tests suffixed with 'IT' to signify an integration test
- JUnit used as test runner
- @SpringBootTest used to run application context
```
           <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>3.0.0-M5</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>integration-test</goal>
                            <goal>verify</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```
The goal for the plugin is *verify* , so tests can be run with the following maven command

`mvn verify`

