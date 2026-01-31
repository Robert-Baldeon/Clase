// Nivel 1: Fácil - El Método de Pago (Bloqueo de objeto)
// Objetivo: Aplicar synchronized en un contexto de recurso compartido simple.
// Enunciado: Crea una clase CuentaBancaria con un saldo inicial de 1000€. Crea un método retirarDinero(int cantidad).
// Tarea: Crea dos hilos ("Cajero 1" y "Cajero 2") que intenten retirar 600€ cada uno al mismo tiempo.
// Desafío: Sin sincronización, ambos hilos verán que hay saldo suficiente y dejarán la cuenta en números negativos (-200€). Usa synchronized para que el segundo hilo vea que ya no queda saldo suficiente y lance un mensaje de error.
