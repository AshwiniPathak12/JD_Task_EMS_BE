/*
 * package com.ems.config;
 * 
 * import java.util.Collections;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
 * import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 * 
 * 
 * 
 * @Configuration //@EnableSwagger2 //@EnableWebMvc public class SwaggerConfig
 * implements WebMvcConfigurer {
 * 
 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
 * registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations(
 * "classpath:/META-INF/resources/swagger-ui.html");
 * registry.addResourceHandler("/api/webjars/**").addResourceLocations(
 * "classpath:/META-INF/resources/webjars/"); }
 * 
 * @Bean public Docket api() { return new
 * Docket(DocumentationType.SWAGGER_2).select()
 * .apis(RequestHandlerSelectors.basePackage("com.ems")).paths(PathSelectors.any
 * ()) .build(); } }
 * 
 * //#spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
 * 
 * 
 * private ApiInfo apiInfo() { return new ApiInfo("Service Name",
 * "API Description", "API", "Terms of service", new Contact("name",
 * "webaddress", "email"), "License of API", "API license URL",
 * Collections.emptyList()); }
 */