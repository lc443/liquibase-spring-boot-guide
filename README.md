# liquibase-spring-boot-app-engine-guide


## Liquibase Instruction
- Step 1: 
  - Add liquibase dependency from maven or gradle from springinitializr
- Step 2:
  -  add liquibase plugin in pom xml along with path 
```xml
	<build>
		<plugins>
			<plugin>
				<groupId>org.liquibase</groupId>
				<artifactId>liquibase-maven-plugin</artifactId>
				<configuration>
					<propertyFile>src/main/resources/application.yml</propertyFile>
				</configuration>
			</plugin>
		</plugins>
	</build>
```
- Step 3:
  - Add liquibase configuration in application.yml
```yml
spring:
  application:
    name: liquibase demo
  liquibase:
    enabled: true
    changeLog: classpath:db/changelog/changelog-master.xml
  datasource:
    driverClassName: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5433/Java
    username: postgres
    password: password
  jpa:
    rest:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        default_schema: liquibase_demo
    hibernate:
      ddl-auto: update
      show-sql: true
```
- Step 4: 
  - Create your <i> <b>changelog-master.xml </b> and <b> changelog-v1.0.xml</b></i>  file in directory directory pointed from application.yml
  

```xml
changelog-master.xml should look like like: 

<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
                    http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.1.xsd">


    <include file="db/changelog/changelog-v1.0.xml"/>
</databaseChangeLog>
    
```

```xml
changelog-v1.0.xml should look like like this:
This is where you will define your create your schema for your database:


        <?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
      http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.1.xsd">


  <!--    If we want to add new column to database use this below -->
  <!--    <changeSet id="1" author="leron">-->
  <!--        <addColumn schemaName="liquibase_demo" tableName="person">-->
  <!--            <column name="address" type="varchar(255)"></column>-->
  <!--        </addColumn>-->
  <!--        <addColumn schemaName="liquibase-demo" tableName="person">-->
  <!--            <column name="phone_number" type="varchar(255)"></column>-->
  <!--        </addColumn>-->
  <!--    </changeSet>-->

  <!--    Creating the table with liquibase instead of hibernate-->
  <changeSet id="20230726_person_table" author="leron">
    <sql>
      create sequence if not exists liquibase_demo.person_sequence_generator start 1 increment 1;

      create table if not exists liquibase_demo.person (
      id bigint not null,
      name varchar(255),
      address varchar(255),
      phone_number varchar(255),
      primary key (id)
      );
    </sql>
  </changeSet>
  <changeSet id="renamecolumn-name" author="leron">
    <renameColumn schemaName="liquibase_demo"
                  tableName="person"
                  newColumnName="first_name"
                  oldColumnName="name"
                  columnDataType="varchar(255)"
                  remarks="A change in names"/>
  </changeSet>
  <changeSet id="dropcolumn-name" author="leron">
    <dropColumn schemaName="liquibase_demo"
                tableName="person"
                columnName="name"/>
  </changeSet>
</databaseChangeLog>
```

```java
Keep in mind that your changelog-v1.0.xml should match your entity definition in your models.It should be the same as this:

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "person")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "address")
  private String address;
  @Column(name = "phone_number")
  private String phoneNumber;
}
```

- Step 5:
  - Runt eh Application then your schema will be created on database

## App Engine Instruction 

#### Liquibase Source:
https://www.youtube.com/watch?v=xjXHecGOy84


#### App Engine Source:
https://www.youtube.com/watch?v=1TqIL7RLrx4

