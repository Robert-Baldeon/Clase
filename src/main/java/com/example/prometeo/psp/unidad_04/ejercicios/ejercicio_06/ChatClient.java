// Objetivo: Crear un sistema de chat donde múltiples usuarios puedan conectarse simultáneamente
// Cuando un usuario envía un mensaje, el servidor debe reenviarlo a todos los demás clientes conectados

// Especificaciones del Cliente (ChatClient.java)
// Hasta ahora, tus clientes hacían: Enviar -> Esperar respuesta -> Leer. En un chat, esto no sirve porque alguien puede escribirte mientras tú estás pensando qué poner
// Multihilo en el Cliente: El cliente debe lanzar un hilo secundario que esté en un bucle infinito haciendo entrada.readLine()
// Hilo Principal: Se encargará únicamente de leer del teclado y enviar al servidor
// Resultado: El usuario podrá recibir mensajes en cualquier momento, independientemente de si está escribiendo o no

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_06;
