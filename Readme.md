https://taller-ci-cd-dkhbcmbvghesd5a2.canadacentral-01.azurewebsites.net/swagger-ui/index.html

# Taller CI/CD 

## Descripción
Este proyecto es una API de gestión de recetas de cocina para un programa. 
Permite a los televidentes, concursantes y chefs registrar, consultar y administrar recetas a través de diferentes endpoints.

## Tecnologías Usadas

- **Spring boot:** Framework para el desarrollo de la API.

- **MongoDB:** Base de datos para almacenar las recetas.

- **Swagger:** Documentación de la API.

- **JUnit:** Para pruebas unitarias de la API.

- **GitHub Actions:** Para CI/CD.

- **Azure:** Para el despliegue del back de la aplicación.


## -
El proyecto sigue el patrón **MVC (Modelo - Vista - Controlador)**:

- **Model**: Entidades de negocio.
- **Repository**: Manejo de persistencia en MongoDB.
- **Service**: Lógica de negocio.
- **Controller**: Exposición de endpoints REST.


### **Util**: 
- Mappers para el manejo de DTOs.
- Error Handler
- Excepciones personalizadas

 

---


## Instalación 

### Local 
- Git clone https://github.com/SantiagoSu15/Taller-CI-CD.git
- ./mvn spring-boot:run

### Azure

**Link del despliegue en azure:**
- https://taller-ci-cd-dkhbcmbvghesd5a2.canadacentral-01.azurewebsites.net

**Link de swagger en azure:**
- https://taller-ci-cd-dkhbcmbvghesd5a2.canadacentral-01.azurewebsites.net/swagger-ui/index.html

---


## Endpoints 

---
## CI/CD con GitHub Actions
Se cuenta con tres pipelines:
- test.yml
- buildyml
- deploy.yml


- Ejecución de pruebas: Cada vez que se haga un push o pull request a la rama develop/main, 
    se ejecutarán las pruebas unitarias.
- Despliegue automático en Azure: Cada vez que se haga un push a la rama main, 
  la aplicación se desplegará automáticamente en Azure.

---
## Pruebas unitarias

![Prueba jacoco](/docs/pruebas_1.png)

Se realizaron pruebas unitarias para asegurar el correcto funcionamiento de los siguientes casos:

- Registrar receta correctamente.
  ![Prueba jacoco](/docs/receta.png)

- Buscar recetas por ingrediente.
  ![Prueba jacoco](/docs/ingredietne.png)

- Devolver error si se consulta una receta inexistente.
![Prueba jacoco](/docs/recetaEx.png)


