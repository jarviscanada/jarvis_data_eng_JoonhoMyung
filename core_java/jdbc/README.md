# Introduction
This application used JDBC which stands for Java Database Connectivity and it
is Java API which helps Java programs access database management systems.

PSQL database system was used and JDBC created a connection between Java programs and PSQL.
CRUD operations were performed on the customer table using Java API and Maven was used to manage dependencies

Therefore, the technologies used for this application are
- JDBC
- Java
- PSQL
- Maven
- Docker


# Implementaiton
## ER Diagram
It is an Entity Relationship Diagram that shows the relationship between table entities in the Database.
![my image](./assets/ER_diagram.png)

## Design Patterns

This application used **JDBC** to access data in RDBMS and two different design patterns
were considered.

First, the **DAO pattern** is applied for this application. DAO pattern is an **abstraction
of data persistence** and is considered closer to the database. So the 
DAO pattern allows us to isolate the application or the business layer 
from the persistence layer using an abstract API. Eventually, the DAO pattern allows developers to
develop business layer and persistent layer separately. The DAO was implemented
as an abstract class that consist of a connection to RDBMS and has CRUD operations. 

Second, the **repository pattern** consists of classes or components that centralize and 
encapsulates various operations which interact and access our database. 
Since we join the code instead of joining the table, the repository pattern is an
abstraction of a collection of objects. This also shows Repository pattern is a 
higher-level concept that is closer more like the Domain object.

Both design patterns increase code maintainability, flexibility, and decrease development cost
However, the **repository pattern** focuses only on **single-table access per class** but
**DAO pattern** can **query multiple tables**. Since the DAO pattern can query multiple tables, 
DAO pattern suits well and is valuable for highly normalized data. Also, DAO pattern joins
the database but the repository pattern will join in the code.

Since our application only interacts with a single table, our application can apply either
repository or DAO pattern.


# Test
The database was tested by creating the database and running provided SQL scripts 
through psql command. `The JDBCExecuter` class was used to run and test the table.
Lastly, CRUD operations were tested by using and testing data and comparing printed
data to find out if the results are as same as expected results.

