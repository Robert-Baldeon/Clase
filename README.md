# Acceso a datos Mongo
## Ejercicio 1
La ruta a los ficheros son:

```sql
src/main/java/com/example/database/mongodb/
```

Para probar que todo funciona bien ejecuta este comando
```bash
mvn exec:java -Dexec.mainClass="com.example.database.mongodb.Main"
```

## Ejercicio 2
Para el ejercicio de leer el fichero json e insertar los datos en MongoDB, la ruta es la siguiente:
```sql
src/main/java/com/example/database/mongodb/gson/
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
