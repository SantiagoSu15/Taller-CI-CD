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

---
## Endpoints 

### Receta Controller
**El Request será de la forma**

- Put  ```/recetas/{id}```
- Get ```/recetas/{id}```

        {id} Un numero entero


- Get ```/recetas/tipo-chef/{chef}```

        {chef} Un String de los diponibles: 
                      JURADO,TELEVIDENTE,CONCURSANTE



- Get ```/recetas/temporada/{temporada}```

        {id} Un numero entero

- Get ```/recetas/ingrediente/{ingrediente}```

       {ingrediente} Un numero String cualquiera


- Post ```/recetas```

        {
        "titulo": "string",
        "ingredientes": [
        "string"
        ],
        "pasosPreparacion": [
        "string"
        ],
        "chefId": "string",
        "temporada": 0
        }


**El Response será de la forma:** 

- Put  ```/recetas/{id}```
- Get ```/recetas/{id}```

        {
            "titulo": "string",
            "ingredientes": [
                "string"
            ],
            "pasosPreparacion": [
               "string"
            ],
            "temporada": 0,
            "fecha": "2025-11-01",
            "nombreChef": "string",
            "tipoChef": "string"
        }

- Post ```/recetas```
- Get ```/recetas/tipo-chef/{chef}```
- Get ```/recetas```
- Get ```/recetas/temporada/{temporada}```
- Get ```/recetas/ingrediente/{ingrediente}```

        [
            {
                "titulo": "string",
                "ingredientes": [
                    "string"
                ],
                "pasosPreparacion": [
                   "string"
                ],
                "temporada": 0,
                "fecha": "2025-11-01",
                "nombreChef": "string",
                "tipoChef": "string"
            },
            {
                "titulo": "string",
                "ingredientes": [
                    "string"
                ],
                "pasosPreparacion": [
                   "string"
                ],
                "temporada": 0,
                "fecha": "2025-11-01",
                "nombreChef": "string",
                "tipoChef": "string"
            }
        ]
        









### Chef Controller

**El request sera de la forma:**
- Post ```/chefs```

        {
        "tipoChef": "JURADO",
        "temporada": 0,
        "fullName": "string"
        }
Donde tipo chef hace parte del conjutno: JURADO,TELEVIDENTE,CONCURSANTE



**El response para Post y Put serán de la forma:**

- /chefs

            {
                "chefId": "string",
                "tipoChef": "JURADO",
                "temporada": 0,
                "fullName": "string"
            }



test  uytuy

