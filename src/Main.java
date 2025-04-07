import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NeuralNetwork network = new NeuralNetwork(480000, 64, 1);
        Trainer trainer = new Trainer(network);
        trainer.train("imagenes/yo.jpg", 100);

        // Obtener vector oculto de la imagen base
        double[] originalInput = ImageProcessor.processImage("imagenes/yo.jpg");
        if (originalInput == null) {
            System.out.println("Error carhando la imagen base");
            return;
        }
        double[] originalVector = network.getHiddenRepresentation(originalInput);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("deeseas comparar una imagen? (S/N): ");
            String opc = scanner.nextLine();
            if (!opc.equalsIgnoreCase("S")) break;

            System.out.print("Ruta de la imagen a cmparar: ");
            String ruta = scanner.nextLine();

            double[] comparada = ImageProcessor.processImage(ruta);
            if (comparada == null) continue;

            double[] comparadaVector = network.getHiddenRepresentation(comparada);
            double distancia = calcularDistancia(originalVector, comparadaVector);

            System.out.printf("Distancia euclidiana entre representaciones: %.6f%n", distancia);

            if (distancia < 0.05) {
                System.out.println(" ES LA MISMA IMAGEN");
            } else {
                System.out.println("IMAGEN DIFERENTE");
            }
        }
//si no lo cierro me sale que puede ocupar memoria
        scanner.close();
    }

    // Método auxiliar para calcular la distancia euclidiana entre dos vectores
    public static double calcularDistancia(double[] a, double[] b) {
        double suma = 0;
        for (int i = 0; i < a.length; i++) {
            double diff = a[i] - b[i];
            suma += diff * diff;
        }
        return Math.sqrt(suma);
    }
}
