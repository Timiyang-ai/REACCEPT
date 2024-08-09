public static double[] rowMean(double[][] data) {
        double[] x = new double[data.length];

        for (int i = 0; i < x.length; i++) {
            x[i] = mean(data[i]);
        }

        return x;
    }