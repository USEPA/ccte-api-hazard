# Computational Toxicology and Exposure (CTX) APIs - CTX Hazard API

<!-- badges: start -->
[![Active](http://img.shields.io/badge/Status-Active-green.svg)](https://comptox.epa.gov/ctx-api/hazard/health) 
<!-- badges: end -->

## Welcome to the GitHub repository for the CTX Hazard API

Originating from the US EPA's Center of Computational Toxicology and Exposure (CCTE), the CTX Hazard API is a RESTful API designed to manage and provide Hazard data. It interacts with a PostgreSQL database and offers various endpoints for hazard information. More information on the CTX API development and utility, as well as guidance on how to technically access and use them, is available here: https://www.epa.gov/comptox-tools/computational-toxicology-and-exposure-apis

- If you would like to report a bug or have other questions related to the CTX APIs, please contact the [CTX API Admins](mailto:ccte_api@epa.gov).
- If you are interested in contributing, please submit a issue or start a discussion. See [CONTRIBUTING](https://github.com/USEPA/ccte-api-exposure/blob/dev/CONTRIBUTING.md) for more information.

### Available Data
Data underlying the Hazard API comes from the US EPA's [Toxicity Value Database (ToxValDB)](https://github.com/USEPA/toxvaldbmain) and the [Toxicity Reference Database (ToxRefDB)](https://github.com/USEPA/CompTox-ToxRefDB). There are additional endpoints to pull external links from US EPA’s [Provisional Peer-Reviewed Toxicity Values (PPRTVs)](https://www.epa.gov/pprtv), [Integrated Risk Information System (IRIS)](https://www.epa.gov/iris), and [Health Assessment Workspace Collaborative (HAWC)](https://hawc.epa.gov/portal/).

- ToxValDB is a compilation of information sourced from multiple public datasets, databases and open literature and includes data on thousands of chemicals from tens of thousands of records, with an emphasis on quantitative estimates of relevant points-of-departure from in vivo toxicology studies, such as no- and low-observable adverse effect levels, screening levels, reference doses, tolerable daily intake, etc.
- ToxValDB includes summary values from ToxRefDB. ToxRefDB contains in vivo study data from over 6000 guideline or guideline-like human health relevant studies for over 1000 chemicals.

### Technologies Used
- **Java**: Programming language used for the development of the application.
- **Spring Boot**: Framework used to create stand-alone, production-grade Spring-based applications.
- **Spring Data JPA**: Part of the Spring Data family, used to simplify data access and persistence.
- **Maven**: Build automation tool used for managing project dependencies and build lifecycle.
- **Swagger/OpenAPI**: Used for API documentation and testing.

### Configuration
The application configuration is managed through properties files. The main configuration file is `application.properties`, and environment-specific configurations can be added as needed.

### Build and Deployment
The project uses Maven for build and deployment processes. Continuous integration and deployment can be set up using tools like GitHub Actions.

### Getting Started
To build and run the project locally, use the following Maven commands:

```sh
mvn clean install
mvn spring-boot:run
```

### Project Structure
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

### Dependencies
- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `lombok`
- `springdoc-openapi-starter-webmvc-ui`
- `spring-boot-starter-test`
- `jackson-datatype-hibernate6`

## Disclaimer
The United States Environmental Protection Agency (EPA) GitHub project code is provided on an "as is" basis and the user assumes responsibility for its use. EPA has relinquished control of the information and no longer has responsibility to protect the integrity, confidentiality, or availability of the information. Any reference to specific commercial products, processes, or services by service mark, trademark, manufacturer, or otherwise, does not constitute or imply their endorsement, recommendation or favoring by EPA. The EPA seal and logo shall not be used in any manner to imply endorsement of any commercial product or activity by EPA or the United States Government. 