[![CircleCI](https://circleci.com/gh/costanzopa/quasar-mission-challenge/tree/master.svg?style=svg&circle-token=8a7989516853b14b77c65c7e6dd80ac522491922)](https://circleci.com/gh/costanzopa/quasar-mission-challenge/tree/master)
[![MIT Licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/costanzopa/quasar-mission-challenge/master/LICENSE.txt)

# Operación Fuego de Quasar

## Objetivo

Como jefe de comunicaciones rebelde, tu misión es crear un programa en Java que retorne la fuente y
contenido del mensaje de auxilio. Para esto, cuentas con tres satélites que te permitirán triangular
la posición, ¡pero cuidado! el mensaje puede no llegar completo a cada satélite debido al campo de
asteroides frente a la nave.

## Arquitectura

Se optó por implementar un tipo de arquitectura clean ya que nos facilita centrarnos en la lógica de
negocio y diferir ciertas decisiones, como por ejemplo que BBDD o Frameworks utilizar. Objetivo
Principal del Nivel 1 del enunciado.

![Screenshot](misc/images/CleanArchitecture.jpeg?raw=true)

## Nivel 1 ([Solución feature/Level_1](https://github.com/costanzopa/quasar-mission-challenge/tree/feature/Level_1)) 

La solución general del nivel 1 se puede observar el el branch feature/Level_1 del corriente
repositorio. Se observa que la problemática a resolver es la lógica de negocio de la aplicación.

### Obtener Localización de la fuente del mensaje.

Para solución de dicho problema geométrico se analizaron dos posibles métodos:

1. Triangulación

2. Trilateración

Se opto por la utilización del método de la trilateración ya que nos abstrae de la dificultad que
conlleva el calculo con ángulos.

#### Trilateración

La trilateración es una técnica geométrica para determinar la posición de un objeto conociendo su
distancia a tres puntos de referencia. A diferencia de la más conocida técnica de triangulación, en
la que se miden ángulos y distancias, en la trilateración se utilizan sólo distancias.

![Screenshot](misc/images/trilateration/trilateration-diagram.png?raw=true)

[Geometría y Solución](https://www.101computing.net/cell-phone-trilateration-algorithm/)

Se utilizo la siguiente librería matemática que implementa la trilateración para disminuir la
complejidad de realizar una implementación propia.

[Trilateration Github](https://github.com/lemmingapex/trilateration)

#### UML Diagram

![Screenshot](misc/images/SourceLocation_UML.png?raw=true)

`El algoritmo de localización se encuentra en la carpeta model dentro del core de la aplicación.`

### Decodificar Mensaje Recibido.

Para la implementación del algoritmo de decodificación se consideraron algunos supuestos:

1. Se considera que los desplazamientos de los mensajes son de izquierda a derecha únicamente (->).
2. Se considera que al menos a un satélite le llega el mensaje en la primera posición, de esta
   manera podemos saber la longitud inicial del mensaje.

#### UML Diagram

![Screenshot](misc/images/DecodeMessage_UML.png?raw=true)

#### Algoritmo pasos

1. Encontrar el mensaje que nos indica la longitud correcta del mismo. Según supuesto 2.
2. Eliminar desplazamientos que se le realizaron a los mensajes.
3. Hacer un merge de los diferentes arrays.
4. Verificar que el mensaje no posea huecos.

`El algoritmo de decodificación se encuentra en la carpeta model dentro del core de la aplicación.`

## Nivel 2 ([Solución feature/Level_2](https://github.com/costanzopa/quasar-mission-challenge/tree/feature/Level_2))

Se realizó mediante el uso del framework Spring Boot dos endpoints:

### POST /quasar/v1/topsecret/

Obtener la ubicación de la nave y el mensaje que emite.

![Screenshot](misc/images/post_top_secret_1.png?raw=true)

### GET /quasar/v1/satellite/

Obtener todos los Satelites activos.

![Screenshot](misc/images/get_satellites.png?raw=true)

## Nivel 3 ([Solución feature/Level_3](https://github.com/costanzopa/quasar-mission-challenge/tree/feature/Level_3))

### POST /quasar/v1/topsecret_split/{satellite_name}

Guardar mesaje llegado al satelite

![Screenshot](misc/images/post_top_secret_split_1.png?raw=true)

### GET /quasar/v1/topsecret_split/

Obtener la ubicación de la nave y el mensaje que emite.

![Screenshot](misc/images/get_top_secret_split.png?raw=true)

## Pipeline de integración continua (CI/CD)

Pipeline de integración continua para la generación de las imagenes de docker a deployar.

![Screenshot](misc/images/pipeline_ci_cd.png?raw=true)

## Uso de la API en Develop

## Prerrequisitos

* Maven
* Docker

### Test con Maven

```
mvn test
```

### Build con Maven

```
mvn clean package
```

### Ejecución con maven

```
mvn package spring-boot:run
```

### Ejecución con docker compose

Build

```
docker-compose build 
```

Arranque de la api

```
docker-compose up 
```

Detener la api

```
docker-compose down
```

## Documentación

Se utilizó Swagger V3 (OpenApi) para documentar los diferentes endpoints.

### Qué es Swagger?

Swagger es una serie de reglas, especificaciones y herramientas que nos ayudan a documentar nuestras
APIs.

También nos brinda una interfaz de usuario para poder ejecutar los diferentes endpoints.

URL: https://quasar-mission-challenge.herokuapp.com

![Screenshot](misc/images/swagger.png?raw=true)