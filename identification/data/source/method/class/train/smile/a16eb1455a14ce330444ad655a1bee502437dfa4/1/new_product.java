public static java.util.function.Function<double[], double[]> standardize(double[][] x) {
        int n = x.length;
        int p = x[0].length;

        double[] center = colMean(x);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                x[i][j] = x[i][j] - center[j];
            }
        }

        double[] scale = new double[p];
        for (int j = 0; j < p; j++) {
            for (int i = 0; i < n; i++) {
                scale[j] += Math.sqr(x[i][j]);
            }
            scale[j] = Math.sqrt(scale[j] / (n-1));

            if (!Math.isZero(scale[j])) {
                for (int i = 0; i < n; i++) {
                    x[i][j] /= scale[j];
                }
            }
        }

        return (double[] xi) -> {
            if (xi.length != p)
                throw new IllegalArgumentException(String.format("array size: %d, expected: %d", xi.length, p));

            double[] y = new double[p];
            for (int j = 0; j < p; j++) {
                if (!Math.isZero(scale[j])) {
                    y[j] = (xi[j] - center[j]) / scale[j];
                } else {
                    y[j] = 0.0;
                }
            }

            return y;
        };
    }