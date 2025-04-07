
import java.util.Random;

public class NeuralNetwork {
    private int inputSize, hiddenSize, outputSize;
    private double[][] weightsInputHidden;
    private double[] weightsHiddenOutput;
    private double[] hiddenBias;
    private double outputBias;
    private double learningRate = 0.005;

    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize) {
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;

        weightsInputHidden = new double[hiddenSize][inputSize];
        weightsHiddenOutput = new double[hiddenSize];
        hiddenBias = new double[hiddenSize];
        initWeights();
    }

    private void initWeights() {
        Random rand = new Random();
        for (int i = 0; i < hiddenSize; i++) {
            hiddenBias[i] = rand.nextDouble();
            weightsHiddenOutput[i] = rand.nextDouble();
            for (int j = 0; j < inputSize; j++) {
                weightsInputHidden[i][j] = rand.nextDouble();
            }
        }
        outputBias = rand.nextDouble();
    }

    private double sigmoid(double x) {
        return 1.0 / (1 + Math.exp(-x));
    }

    private double sigmoidDerivative(double x) {
        return x * (1 - x);
    }

    public double train(double[] input, double target) {
        double[] hiddenOutputs = new double[hiddenSize];
        for (int i = 0; i < hiddenSize; i++) {
            double sum = hiddenBias[i];
            for (int j = 0; j < inputSize; j++) {
                sum += input[j] * weightsInputHidden[i][j];
            }
            hiddenOutputs[i] = sigmoid(sum);
        }

        double sumOutput = outputBias;
        for (int i = 0; i < hiddenSize; i++) {
            sumOutput += hiddenOutputs[i] * weightsHiddenOutput[i];
        }
        double output = sigmoid(sumOutput);
        double error = target - output;

        double deltaOutput = error * sigmoidDerivative(output);
        for (int i = 0; i < hiddenSize; i++) {
            weightsHiddenOutput[i] += learningRate * deltaOutput * hiddenOutputs[i];
        }
        outputBias += learningRate * deltaOutput;

        for (int i = 0; i < hiddenSize; i++) {
            double deltaHidden = sigmoidDerivative(hiddenOutputs[i]) * deltaOutput * weightsHiddenOutput[i];
            for (int j = 0; j < inputSize; j++) {
                weightsInputHidden[i][j] += learningRate * deltaHidden * input[j];
            }
            hiddenBias[i] += learningRate * deltaHidden;
        }

        return error * error;
    }

    public double predict(double[] input) {
        double[] hiddenOutputs = new double[hiddenSize];
        for (int i = 0; i < hiddenSize; i++) {
            double sum = hiddenBias[i];
            for (int j = 0; j < inputSize; j++) {
                sum += input[j] * weightsInputHidden[i][j];
            }
            hiddenOutputs[i] = sigmoid(sum);
        }

        double sumOutput = outputBias;
        for (int i = 0; i < hiddenSize; i++) {
            sumOutput += hiddenOutputs[i] * weightsHiddenOutput[i];
        }
        return sigmoid(sumOutput);
    }
}
