
public class Predictor {
    private NeuralNetwork network;

    public Predictor(NeuralNetwork network) {
        this.network = network;
    }

    public double predict(String imagePath) {
        double[] input = ImageProcessor.processImage(imagePath);
        if (input == null) return 0;
        return network.predict(input);
    }
}
