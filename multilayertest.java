public class multilayertest {
    

    public static void main (String[] args) {
        double[][] inputs = {{0,0}, {0,1}, {1,0}, {1,1}};
        double[] ortargets = {0, 1, 1, 1};
        double[] andtargets = {0, 0, 0, 1};
        double[] test1inputs = new double[inputs.length];

        test2 testor = new test2(2, 0.1, ortargets);
        test2 testand = new test2(2, 0.1, andtargets);

        testand.train(inputs, 5000);

        testor.train(inputs, 5000);

        System.out.println("Predictions:");
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(inputs[i][0] + " AND " + inputs[i][1] + " = " + testand.predict(inputs[i]));

            System.out.println(inputs[i][0] + " or " + inputs[i][1] + " = " + testor.predict(inputs[i]));

            
            if (testand.predict(inputs[i]) > .5) {
                test1inputs[i] = 1;
            } else {
                test1inputs[i] = 0;
            }

            System.out.println(test1inputs[i]);
        }

        // for (int i = 0; i < inputs.length; i++) {
        //     System.out.println(inputs[i][0] + " AND " + inputs[i][1] + " = " + testand.predict(inputs[i]));
        // }

        
    }

}
