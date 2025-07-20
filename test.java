import java.util.Random;

public class test {
    private double[] weights;
    private double learningRate;
    private int epochs;

    public test(int inputSize, double learningRate, int epochs) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.weights = new double[inputSize + 1]; // +1 for bias
        Random rand = new Random();
        for (int i = 0; i < weights.length; i++) {
            weights[i] = rand.nextDouble() * 2 - 1; // Random values between -1 and 1
        }
    }

    // Activation function (Step Function)
    private int stepFunction(double x) {
        return x >= 0 ? 1 : 0;
    }

    // Predict output for a given input
    public int predict(int[] inputs) {
        double sum = weights[0]; // Bias term
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i + 1]; // Weighted sum
        }
        return stepFunction(sum);
    }

    // Train the perceptron with given data
    public void train(int[][] X, int[] y) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            double totalLoss = 0;
            for (int i = 0; i < X.length; i++) {
                double prediction = predict(X[i]);
                double error = y[i] - prediction;
                totalLoss += error*error;

                // Update weights
                weights[0] += learningRate * error; // Update bias
                for (int j = 0; j < X[i].length; j++) {
                    weights[j + 1] += learningRate * error * X[i][j];
                }
            }
            if (epoch % 1000 == 0) {
                System.out.println("Epoch " + epoch + "- Loss: " + totalLoss/ weights.length);
            }
        }
    }

    public static void main(String[] args) {
        int[][] X = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };
        int[] y = {0, 0, 0, 1}; // AND gate outputs

        test perceptron = new test(2, 0.1, 5000);
        perceptron.train(X, y);

        // Testing the perceptron
        for (int[] inputs : X) {
            System.out.println("Input: " + inputs[0] + ", " + inputs[1] + " → Prediction: " + perceptron.predict(inputs));
        }
    }
}
