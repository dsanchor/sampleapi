# API Documentation for TODO List

This API allows you to manage a TODO list. Each TODO item has the following attributes:  
- **id**: Auto-generated identifier  
- **name**: Name of the task (mandatory on creation)  
- **details**: Details of the task (mandatory on creation)  
- **creationDate**: Date when the task was created (set automatically during creation)  
- **closeDate**: Date when the task was marked done (set automatically when marking as done)  
- **done**: Boolean flag to indicate if the task is finished

**Base URL:**  
`http://localhost:8080`

**OpenAPI Documentation:**  
- Swagger UI: `http://localhost:8080/swagger-ui.html`  
- JSON Specification: `http://localhost:8080/api-docs`  
- YAML Specification: `http://localhost:8080/api-docs.yaml`

---

- Detailed information about the API endpoints can be found in [this document](./ENDPOINTS.md).
- For comprehensive guide on the OpenAPI implementation, see our [OpenAPI Guide](./OPENAPI-GUIDE.md).

# Containerize the Application

## Build

```bash
mvn clean package
docker build -t sample-api .
```

## Run

```bash
docker run -p 8080:8080 -it sample-api
```

## Push in Docker Hub

```bash
USERNAME=<docker_io_username>
docker login --username=$USERNAME
docker tag sample-api docker.io/$USERNAME/sample-api
docker push docker.io/$USERNAME/sample-api
```

## Continuous Integration with GitHub Actions

This project includes a GitHub Actions workflow for building the image in the main branch and pushing it to github container registry. The workflow is defined in `.github/workflows/build-and-push.yml`.

The image is then available at:
`ghcr.io/dsanchor/sample-api:latest`

# Deploy in Azure Container Apps

## Create a Resource Group

```bash
RG=<resource_group_name>
LOCATION=<location>
az group create --name $RG --location $LOCATION
```

## Deploy the Container

```bash
IMAGE=docker.io/$USERNAME/sample-api
# or use the GitHub Container Registry image
IMAGE=ghcr.io/dsanchor/sample-api:latest
az containerapp up \
  --name sample-api \
  --resource-group $RG \
  --location $LOCATION \
  --environment sample-env \
  --image $IMAGE \
  --target-port 8080 \
  --ingress external \
  --query properties.configuration.ingress.fqdn
```

## Test the Application

```bash
FQDN=<fqdn_as_in_sample-api.orangehill-123d1w45.francecentral.azurecontainerapps.io>
curl -X POST https://$FQDN/todos -H "Content-Type: application/json" -d '{"name": "Comprar comestibles", "details": "Leche, pan, huevos"}'
curl -X GET https://$FQDN/todos
```