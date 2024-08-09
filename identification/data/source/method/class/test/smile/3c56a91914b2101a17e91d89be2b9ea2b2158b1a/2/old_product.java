public static double[] colSum(double[][] data) {
        double[] x = data[0].clone();

        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < x.length; j++) {
                x[j] += data[i][j];
            }
        }

        return x;
    }