// Objetivo: Crear un sistema de chat donde múltiples usuarios puedan conectarse simultáneamente
// Cuando un usuario envía un mensaje, el servidor debe reenviarlo a todos los demás clientes conectados

// Especificaciones del Manejador (ChatHandler.java)
// Registro: Al iniciar el método run(), el manejador debe añadirse a sí mismo a la lista global del servidor
// Escucha Activa: Debe esperar mensajes del cliente. Al recibir uno, llamará al método del servidor para repartirlo a los demás
// Salida Limpia: En el bloque finally, debe eliminarse obligatoriamente de la lista global para evitar errores al intentar enviar mensajes a un socket que ya se ha cerrado

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_06;

