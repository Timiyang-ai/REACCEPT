public static double[] colMeans(double[][] data) {
        double[] x = data[0].clone();

        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < x.length; j++) {
                x[j] += data[i][j];
            }
        }

        scale(1.0 / data.length, x);

        return x;
    }