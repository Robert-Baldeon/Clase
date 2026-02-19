// 2. Ingresar dos números enteros (a, b) por teclado y calcular la suma.
// Ejemplo de entrada: a:23, b:7
// Ejemplo de salida: La suma es 30

public class Ejercicio_02 {
    void main() {
        int a, b, c;
        a = Integer.parseInt(IO.readln("Ingrese a: "));
        b = Integer.parseInt(IO.readln("Ingrese b: "));
        c = a + b;

        IO.println("La suma es " + c);
    }
}
