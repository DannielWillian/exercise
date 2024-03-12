# Java Test Exercise

This exercise is composed of two backend services. The first one is a REST API to create, delete and retrieve information about Orders and Customers. The second service listens to a RabbitMQ server, expecting messages from the first server whenever an Order is created or deleted.

The services were developed using Spring Boot with an in-memory database (H2) and RabbitMQ. All services are tied together using Docker Compose. Three containers are created, one for the REST API, one for RabbitMQ and one for the Message Consumer.

Maven is the dependency manager selected. It's also used as the package builder.

## Dependencies

1. Maven (version 3.8.4 was used during development)
2. Docker (Docker Engine version 25.0.3 was used during development)

## Installation

In the root folder `assessment` you'll find two project folders: `consumer` and `restapi`. You'll have to run `mvn install` in each of these folders:

```bash
\assessment> cd consumer
\assessment\consumer> mvn install
\assessment\consumer> cd ../restapi
\assessment\restapi> mvn install
```

You can also import the projects into your preferred IDE. For Eclipse, you can use the `File` menu, then `Import`, `Existing Maven project`, and then select the `consumer` or `restapi` folder.

After running the maven install command, you can then start up the services running Docker Compose in the `assessment` folder. The Docker engine must be running.

```bash
\assessment\restapi> cd ..
\assessment> docker-compose up
```

Once the services are up, you can hit the REST API at `http://localhost:8080`.

## Additional Information

A Postman collection is provided in the `assessment` folder. All operations requested by the exercise are available in the collection.

The system is pre-loaded with some base data about Customers, Items and Orders. The data file can be found under `restapi/src/main/resources/data.sql`. Below are the tables and pre-loaded data. As I use an in-memory database, the database gets wiped on every container restart.

### Customer

<table>
    <tr>
        <th>
            ID
        </th>
        <th>
            NAME
        </th>
    </tr>
    <tr>
        <td>
            1
        </td>
        <td>
            JONATHAN
        </td>
    </tr>
    <tr>
        <td>
            2
        </td>
        <td>
            GEORGE
        </td>
    </tr>
    <tr>
        <td>
            3
        </td>
        <td>
            JOSEPH
        </td>
    </tr>     
</table>

### Category

<table>
    <tr>
        <th>
            ID
        </th>
        <th>
            NAME
        </th>
    </tr>
    <tr>
        <td>
            1
        </td>
        <td>
            TOYS
        </td>
    </tr>
    <tr>
        <td>
            2
        </td>
        <td>
            CLOTHES
        </td>
    </tr>   
</table>

### Items

<table>
    <tr>
        <th>
            ID
        </th>
        <th>
            NAME
        </th>
        <th>
            CATEGORY_ID
        </th>
        <th>
            PRICE
        </th>            
    </tr>
    <tr>
        <td>
            1
        </td>
        <td>
            PUPPY
        </td>
        <td>
            1
        </td>
        <td>
            3.00
        </td>                
    </tr>
    <tr>
        <td>
            1
        </td>
        <td>
            PLANE
        </td>
        <td>
            1
        </td>
        <td>
            4.00
        </td>
    </tr>
    <tr>
        <td>
            1
        </td>
        <td>
            CLACKERS
        </td>
        <td>
            1
        </td>
        <td>
            2.00
        </td>                
    </tr>
    <tr>
        <td>
            1
        </td>
        <td>
            BOWTIE
        </td>
        <td>
            2
        </td>
        <td>
            5.00
        </td>                
    </tr>
    <tr>
        <td>
            1
        </td>
        <td>
            UNIFORM
        </td>
        <td>
            2
        </td>
        <td>
            6.00
        </td>                
    </tr>
    <tr>
        <td>
            1
        </td>
        <td>
            SCARF
        </td>
        <td>
            2
        </td>
        <td>
            7.00
        </td>                
    </tr>       
</table>
