public static double[] colSd(double[][] data) {
        if (data.length < 2) {
            throw new IllegalArgumentException("Array length is less than 2.");
        }

        int p = data[0].length;
        double[] sum = new double[p];
        double[] sumsq = new double[p];
        for (double[] x : data) {
            for (int i = 0; i < p; i++) {
                sum[i] += x[i];
                sumsq[i] += x[i] * x[i];
            }
        }

        int n = data.length - 1;
        for (int i = 0; i < p; i++) {
            sumsq[i] = java.lang.Math.sqrt(sumsq[i] / n - (sum[i] / data.length) * (sum[i] / n));
        }

        return sumsq;
    }