package com.payconiq.payconiqstockapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${api.title}") String title,
                                 @Value("${api.version}") String version,
                                 @Value("${api.description}") String description,
                                 @Value("${api.termsOfService}") String termsOfService,
                                 @Value("${api.contact.name}") String contactName,
                                 @Value("${api.contact.email}") String contactEmail,
                                 @Value("${api.license.name}") String licenseName,
                                 @Value("${api.license.url}") String licenseUrl
    ) {
        return new OpenAPI().info(new Info()
                .title(title)
                .version(version)
                .description(description)
                .termsOfService(termsOfService)
                .contact(new Contact().name(contactName)
                        .email(contactEmail))
                .license(new License().name(licenseName)
                        .url(licenseUrl)));
    }
}
