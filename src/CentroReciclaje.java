import java.util.Scanner;
 
public class CentroReciclaje {
 
    static final int MIN_CARGAS = 3;
    static final int MAX_CARGAS = 20;
    static final int PESO_MIN = 5;
    static final int PESO_MAX = 200;
    static final int LIMITE = 80;
 
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        int cantidad = leerCantidad(lector);
        int[] pesos = new int[cantidad];
        cargarPesos(pesos, lector);
        mostrarResumen(pesos);
        
        lector.close();
    }
 
    static int leerCantidad(Scanner lector) {
        System.out.print("Cantidad de cargas (" + MIN_CARGAS + " a " + MAX_CARGAS + "): ");
        int cantidad = lector.nextInt();
        while (cantidad < MIN_CARGAS || cantidad > MAX_CARGAS) {
            System.out.print("Cantidad invalida, debe estar entre " + MIN_CARGAS + " y " + MAX_CARGAS + ": ");
            cantidad = lector.nextInt();
        }
        return cantidad;
    }
 
    static boolean esPesoValido(int peso) {
        return peso >= PESO_MIN && peso <= PESO_MAX;
    }
 
    static void cargarPesos(int[] pesos, Scanner lector) {
        for (int i = 0; i < pesos.length; i++) {
            System.out.print("Peso de la carga " + (i + 1) + ": ");
            int peso = lector.nextInt();
            while (!esPesoValido(peso)) {
                System.out.print("Peso invalido, debe estar entre " + PESO_MIN + " y " + PESO_MAX + ": ");
                peso = lector.nextInt();
            }
            pesos[i] = peso;
        }
    }
 
    static int calcularSuma(int[] pesos) {
        int suma = 0;
        for (int i = 0; i < pesos.length; i++) {
            suma += pesos[i];
        }
        return suma;
    }
 
    static int contarEnRango(int[] pesos, int minimo, int maximo) {
        int contador = 0;
        for (int i = 0; i < pesos.length; i++) {
            if (pesos[i] >= minimo && pesos[i] <= maximo) {
                contador++;
            }
        }
        return contador;
    }
 
    static int contarMayores(int[] pesos, double limite) {
        int contador = 0;
        for (int i = 0; i < pesos.length; i++) {
            if (pesos[i] > limite) {
                contador++;
            }
        }
        return contador;
    }
 
    static double calcularPromedio(int suma, int cantidad) {
        return (double) suma / cantidad;
    }
 
    static int buscarMayor(int[] pesos) {
        int mayor = pesos[0];
        for (int i = 1; i < pesos.length; i++) {
            if (pesos[i] > mayor) {
                mayor = pesos[i];
            }
        }
        return mayor;
    }
 
    static int buscarPosicion(int[] pesos, int valor) {
        int i = 0;
        while (pesos[i] != valor) {
            i++;
        }
        return i;
    }
 
    static void mostrarResumen(int[] pesos) {
        int total = calcularSuma(pesos);
        double promedio = calcularPromedio(total, pesos.length);
        int mayor = buscarMayor(pesos);
 
        System.out.println("\nListado de cargas:");
        for (int i = 0; i < pesos.length; i++) {
            System.out.println("Carga " + (i + 1) + ": " + pesos[i] + " kg");
        }
 
        System.out.println("\nCargas entre " + PESO_MIN + " y " + LIMITE + " kg: " + contarEnRango(pesos, PESO_MIN, LIMITE));
        System.out.println("Cargas superiores a " + LIMITE + " kg: " + contarMayores(pesos, LIMITE));
        System.out.println("Peso total: " + total + " kg");
        System.out.println("Promedio: " + promedio + " kg");
        System.out.println("Mayor peso: " + mayor + " kg");
        System.out.println("Cargas sobre el promedio: " + contarMayores(pesos, promedio));
        System.out.println("Primera carga con el mayor peso: " + (buscarPosicion(pesos, mayor) + 1));
    }
}