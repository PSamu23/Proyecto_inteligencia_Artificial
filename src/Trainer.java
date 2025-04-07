
public class Trainer {
    private NeuralNetwork network;

    public Trainer(NeuralNetwork network) {
        this.network = network;
    }

    public void train(String imagePath, int maxEpochs) {
        double[] input = ImageProcessor.processImage(imagePath);
        if (input == null) return;

        System.out.println("Iniciando entrenamiento...");
        for (int epoch = 1; epoch <= maxEpochs; epoch++) {
            double error = network.train(input, 0.9);
            double precision = (1.0 - error) * 100;
            System.out.printf("Epoch %d - Error promedio: %.4f - Precisión: %.2f%%%n", epoch, error, precision);
            if (precision > 99) {
                System.out.println("Entrenamiento Opimo alcanzado ");
                break;
            }
        }
    }
}
