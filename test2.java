import java.util.Random;

public class test2 {
    private double[] weights;
    private double LEARNING_RATE;
    private double bias;
    private double[] targets;

    public test2 (int inputSize, double LEARNING_RATE, double[] targets) {
        this.LEARNING_RATE = LEARNING_RATE;
        this.weights = new double[inputSize];
        Random rand = new Random();
        this.bias = rand.nextDouble() * 2 - 1;
        this.targets = new double[targets.length];
        this.targets = targets.clone();

        for (int i = 0; i < weights.length; i++) {
            weights[i] = rand.nextDouble() * 2 - 1;
        }
    }

    public double sigmoid (double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public double sigmoidDerivative (double x) {
        return x * (1 - x);
    }

    public double predict (double[] inputs) {
        double sum = bias;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return sigmoid(sum);
    }

    public void train (double[][] inputs, int epochs) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            double totalLoss = 0;
            for (int i = 0; i < inputs.length; i++) {

                double pred = predict(inputs[i]);
                double error = targets[i] - pred;
                totalLoss += error * error;

                double gradient = error * sigmoidDerivative(pred);

                bias += LEARNING_RATE * gradient;
                for (int j = 0; j < inputs[i].length; j++) {
                    weights[j] += LEARNING_RATE * gradient * inputs[i][j];
                }

                
            }
            if (epoch % 1000 == 0) {
                System.out.println("Epoch " + epoch + "- Loss: " + totalLoss/ weights.length);
            }
        }
    }

    public static void main (String[] args) {
        double[][] inputs = {{0,0}, {0,1}, {1,0}, {1,1}};
        double[] target = {0, 1, 1, 1};

        test2 test = new test2(2, .8, target);

        test.train(inputs, 10000);

        System.out.println("Predictions:");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(inputs[i][0] + " OR " + inputs[i][1] + " = " + test.predict(inputs[i]));
        }
    }
}
