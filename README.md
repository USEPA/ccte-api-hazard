# Hazard Microservice (ccte-api-hazard)

## Overview
The ccte-api-hazard is a RESTful API designed to manage and provide hazard data. It interacts with a database and offers various endpoints for hazard-related operations.

## Available Data
Data underlying the Hazard API comes from the Toxicity Value Database (ToxValDB). This resource is a compilation of information sourced from multiple public datasets, databases and open literature and includes data on thousands of chemicals from tens of thousands of records, with an emphasis on quantitative estimates of relevant points-of-departure from in vivo toxicology studies, such as no- and low-observable adverse effect levels, screening levels, reference doses, tolerable daily intake, etc.

## Technologies Used
- **Java**: Programming language used for the development of the application.
- **Spring Boot**: Framework used to create stand-alone, production-grade Spring-based applications.
- **Spring Data JPA**: Part of the Spring Data family, used to simplify data access and persistence.
- **Maven**: Build automation tool used for managing project dependencies and build lifecycle.
- **Swagger/OpenAPI**: Used for API documentation and testing.

## Configuration
The application configuration is managed through properties files. The main configuration file is `application.properties`, and environment-specific configurations can be added as needed.

## Build and Deployment
The project uses Maven for build and deployment processes. Continuous integration and deployment can be set up using tools like GitHub Actions.

## Getting Started
To build and run the project locally, use the following Maven commands:

```sh
mvn clean install
mvn spring-boot:run
```

## Project Structure
```plaintext
src/
├── main/
│   ├── java/
│   │   └── gov/epa/ccte/api/hazard/
│   │       ├── domain/
│   │       ├── repository/
│   │       ├── service/
│   │       └── web/
│   │           └── rest/
│   └── resources/
│       └── application.properties
└── test/
```

## Dependencies
- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `lombok`
- `springdoc-openapi-starter-webmvc-ui`
- `spring-boot-starter-test`
- `jackson-datatype-hibernate6`

## Contributing
Contributions are welcome! Please read the `CONTRIBUTING.md` file for guidelines.

## Contact
 For any inquiries, please contact the project maintainer. **[Rashid, Asif](https://github.com/asif-rashid)** , **[Dirks, Brianna](https://github.com/bridirks)**, **[Feshuk, Madison](https://github.com/madison-feshuk)**

## Disclaimer
The United States Environmental Protection Agency (EPA) GitHub project code is provided on an "as is" basis and the user assumes responsibility for its use. EPA has relinquished control of the information and no longer has responsibility to protect the integrity, confidentiality, or availability of the information. Any reference to specific commercial products, processes, or services by service mark, trademark, manufacturer, or otherwise, does not constitute or imply their endorsement, recommendation or favoring by EPA. The EPA seal and logo shall not be used in any manner to imply endorsement of any commercial product or activity by EPA or the United States Government. 