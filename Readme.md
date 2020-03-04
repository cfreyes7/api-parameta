# Spring Boot, MySQL, JPA, Hibernate Rest API Parameta Test


## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 8.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/cfreyes7/api-parameta.git
```

**2. Create Mysql database**
```bash
create database parameta
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/api-parameta-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The example.

    GET /api/employee?firstName=fabian&lastName=reyes&typeDocument=cedula&document=80799947&bithday=08-07-1984&linkingDate=04-03-2019&position=developer&salary=1000    
	
	
	Response api:
	
	{
    "employee": {
        "id": 7,
        "firstName": "fabian",
        "lastName": "reyes",
        "typeDocument": "cedula",
        "document": "80799947",
        "bithday": "1984-07-08",
        "linkingDate": "2019-03-04",
        "position": "developer",
        "salary": 1000.0,
        "createdAt": null,
        "updatedAt": null
    },
    "message": "Employee Save!",
    "timeofLinkingCompany": "Years:1,Months:0,Days:0",
    "currentEmployeeAge": "Years:35,Months:7,Days:25"
	}

## Explore SOAP Service

	/ws/employees.wsdl