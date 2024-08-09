public static double[] rowSum(double[][] data) {
        double[] x = new double[data.length];

        for (int i = 0; i < x.length; i++) {
            x[i] = sum(data[i]);
        }

        return x;
    }