package br.com.ekan.planosaude.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

	@Bean
	public GroupedOpenApi storeOpenApi() {
		String paths[] = {"/**"};
		return GroupedOpenApi.builder()
				.group("planosaude")
				.pathsToMatch(paths)
				.build();
	}

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API com Java 17 e Spring Boot 3.0.1")
						.version("v0.0.1")
						.description("API Responsavél pelo Cadastro de Beneficiários e seus Documentos!")
						.termsOfService("http://localhost:8080/api/planosaude")
						.license(
								new License()
										.name("Apache 2.0")
										.url("http://springdoc.org")
						)
				);
	}

}
