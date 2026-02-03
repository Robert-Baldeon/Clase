# Ejercicios de 2º DAM

## Acceso a datos Mongo
### Ejercicio 1
La ruta a los ficheros son:

```
src/main/java/com/example/database/mongodb/
```

Para probar que todo funciona bien ejecuta este comando
```bash
mvn exec:java -Dexec.mainClass="com.example.database.mongodb.Main"
```

### Ejercicio 2
Para el ejercicio de leer el fichero json e insertar los datos en MongoDB, la ruta es la siguiente:
```
src/main/java/com/example/database/mongodb/gson/
```

La ruta de los ficheros JSON que uso para leer e importar es la siguiente:
```
src/main/resources/json/
```

Para probar que todo funciona, ejecuta estos dos comandos:
```bash
# Para leer e insertar un solo elemento json
mvn exec:java -Dexec.mainClass="com.example.database.mongodb.gson.LeerClienteJson"

# Para leer e insertar 3 empleados del cine
mvn exec:java -Dexec.mainClass="com.example.database.mongodb.gson.InsertarEmpleados"

# Para leer e insertar personas del MOCK_DATA.json que nos generó Mockaroo
mvn exec:java -Dexec.mainClass="com.example.database.mongodb.gson.InsertarMOCK_DATA"
```

## Programación de servicios y procesos
Esa es la ruta de todos los ejercicios de JPA
```
src/main/java/com/example/jpa/
```

```bash
# Ejercicio 1
mvn compile exec:java -Dexec.mainClass="com.example.jpa.ejercicio_01.Main"

# Ejercicio 2
mvn compile exec:java -Dexec.mainClass="com.example.jpa.ejercicio_02.Main"

# Ejercicio 3
mvn compile exec:java -Dexec.mainClass="com.example.jpa.ejercicio_03.Main"

# Ejercicio 4
mvn compile exec:java -Dexec.mainClass="com.example.jpa.ejercicio_04.Main"

# Ejercicio 5
mvn compile exec:java -Dexec.mainClass="com.example.jpa.ejercicio_05.Main"

# Ejercicio 6
mvn compile exec:java -Dexec.mainClass="com.example.jpa.ejercicio_06.Main"
mvn compile exec:java -Dexec.mainClass="com.example.jpa.ejercicio_06.SistemaBibliotecaDos"

# Ejercicio 7
mvn compile exec:java -Dexec.mainClass="com.example.jpa.ejercicio_07.CargarDatos"
mvn compile exec:java -Dexec.mainClass="com.example.jpa.ejercicio_07.SistemaAcademico"
```
