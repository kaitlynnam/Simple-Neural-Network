//Single Neuron
//BUILDING BLOCK FOR ALL NEURAL NETWORKS
//OR GATE

import java.util.Random;

public class SingleNeuron {
 
    static double w1, w2, bias;
    static final double LEARNING_RATE = 0.1;

    public static double sigmoid (double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double sigmoidDerivative (double x) {
        return x * (1 - x);
    }

    public static double forward (double x1, double x2) {
        double z = (x1 *w1) + (x2 * w2) + bias;
        return sigmoid(z);
    }

    public static void train (double[][] inputs, double[] targets, int epochs) {
        Random rand = new Random();
        w1 = rand.nextDouble() * 2 -1;
        w2 = rand.nextDouble() * 2 -1;
        bias = rand.nextDouble() * 2 -1;

        for (int epoch = 0; epoch < epochs; epoch++) {
            double totalLoss = 0;

            for (int i = 0; i < inputs.length; i++) {
                double x1 = inputs[i][0];
                double x2 = inputs[i][1];
                double yTrue = targets[i];

                double yPred = forward(x1, x2);

                double error = yTrue - yPred;
                totalLoss += error * error;

                double gradient = error * sigmoidDerivative(yPred);

                w1 += LEARNING_RATE * gradient * x1; 
                w2 += LEARNING_RATE * gradient * x2;
                bias += LEARNING_RATE * gradient;
            }

            if (epoch % 1000 == 0) {
                System.out.println("Epoch " + epoch + "- Loss: " + totalLoss/ inputs.length);
            }
        }


    }

    public static void main(String[] args) {
        double[][] inputs = { {0,0}, {0,1}, {1,0}, {1,1} };
        double[] targets = { 0, 1, 1, 1};

        train(inputs, targets, 5000);


        System.out.println("Predictions:");
        for (double[] input: inputs) {
            System.out.println(input[0] + " OR " + input[1] + " = " + forward(input[0], input[1]));
        }
    }
}
