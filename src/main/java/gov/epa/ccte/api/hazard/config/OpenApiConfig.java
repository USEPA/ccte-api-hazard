package gov.epa.ccte.api.hazard.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;



@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Computational Toxicology and Exposure Data APIs - Hazard",
                description = "The Hazard APIs are part of a set of public computational toxicology and exposure APIs and provide hazard data through a set of API endpoints. Users have the option to get all the data or only data related to human health or related to ecotoxicology.",
                contact = @Contact(
                        name = "",
                        url = "",
                        email = ""),
                version = "1.0.0"
        ),
        servers = { @Server(url = "${application.api-url}", description = "${application.api-env}")}
)
@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = "api_key",
        in = SecuritySchemeIn.HEADER,
        description = "Each API call should have api_key, Contact author for getting the new api_key. ",
        paramName = "x-api-key"
)
public class OpenApiConfig {
}
