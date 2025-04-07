import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Cargar la imagen original y entrenar solo por formalidad
        NeuralNetwork network = new NeuralNetwork(480000, 64, 2);
        Trainer trainer = new Trainer(network);
        trainer.train("imagenes/yo.jpg", 100); // entrenamiento simbólico

        // Procesar la imagen original como referencia
        double[] original = ImageProcessor.processImage("imagenes/yo.jpg");
        if (original == null) {
            System.out.println("ups, errro ya que no se  procesar la imagen original.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("¿Deseas comparar una imagen? (S/N): ");
            String opc = scanner.nextLine();
            if (!opc.equalsIgnoreCase("S")) break;

            System.out.print("la RUta de la imagen a comparar: ");
            String ruta = scanner.nextLine();

            double[] comparada = ImageProcessor.processImage(ruta);
            if (comparada == null) continue;

            double diferencia = 0;
            for (int i = 0; i < original.length; i++) {
                diferencia += Math.abs(original[i] - comparada[i]);
            }
            diferencia /= original.length;

            System.out.printf("LA iferencia promedio : %.6f%n", diferencia);
            if (diferencia < 0.01) {
                System.out.println(" ES LA MISMA IMAGEN");
            } else {
                System.out.println("IMAGEN DIFERENTE");
            }
        }

        scanner.close();
    }
}
