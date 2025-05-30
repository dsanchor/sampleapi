# OpenAPI Documentation Guide

This document provides details about the OpenAPI documentation implemented in this Todo API project.

## OpenAPI/Swagger Configuration

The API uses SpringDoc OpenAPI to automatically generate comprehensive API documentation based on the code and annotations. The implementation includes:

1. **Controller Annotations**: Each controller method is annotated with detailed OpenAPI annotations including:
   - `@Operation` - Describes the operation/endpoint
   - `@ApiResponses` - Documents possible response codes
   - `@Parameter` - Documents request parameters
   - `@Tag` - Groups operations by functionality

2. **Model Annotations**: The data model classes are annotated with:
   - `@Schema` - Provides detailed descriptions of model properties

3. **Custom Configuration**:
   - `OpenApiConfig` - Provides global API information like title, description, contact info, license, etc.
   - `SwaggerUiConfig` - Configures the Swagger UI interface

## Accessing the Documentation

The OpenAPI documentation can be accessed in multiple formats:

- **Swagger UI**: 
  - URL: `http://localhost:8080/swagger-ui.html`
  - Interactive web UI that allows exploring and testing the API endpoints

- **OpenAPI JSON**: 
  - URL: `http://localhost:8080/api-docs`
  - Raw OpenAPI specification in JSON format

- **OpenAPI YAML**: 
  - URL: `http://localhost:8080/api-docs.yaml`
  - Raw OpenAPI specification in YAML format

## OpenAPI Implementation Details

### Controller Documentation

Each controller method has been annotated with:

1. **Operation Information**:
   ```java
   @Operation(summary = "Summary text", description = "Detailed description")
   ```

2. **Response Documentation**:
   ```java
   @ApiResponses(value = {
       @ApiResponse(responseCode = "200", description = "Success description", 
                content = @Content(schema = @Schema(implementation = ReturnType.class))),
       @ApiResponse(responseCode = "404", description = "Error description")
   })
   ```

3. **Parameter Documentation**:
   ```java
   @Parameter(description = "Parameter description", required = true)
   ```

### Model Documentation

The models use annotations to describe each property:

```java
@Schema(description = "Property description", example = "Example value")
private String property;
```

### Configuration

The `application.properties` file contains settings for SpringDoc:

```properties
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operations-sorter=method
```

## OpenAPI Customization

The API documentation is customized through the `OpenApiConfig` class which provides:

1. API General Information:
   - Title, description, version
   - Contact information
   - License details
   - Terms of service
   - External documentation links

2. Server Information:
   - Available server URLs
   - Server descriptions

3. Security Schemes:
   - Authentication methods
   - Authorization details

## Best Practices Used

1. **Consistent Descriptions**: All endpoints have clear, consistent descriptions
2. **Response Documentation**: All possible response status codes are documented
3. **Request Documentation**: All request parameters are explained
4. **Examples**: Examples are included for properties
5. **Structuring**: Related endpoints are grouped with tags
6. **Authentication**: Security schemes are defined

## Future Enhancements

1. Add more detailed examples for request/response bodies
2. Include more detailed error responses
3. Add pagination documentation for list endpoints
4. Document rate limiting information
