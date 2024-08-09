public static void standardize(double[][] x) {
        int n = x.length;
        int p = x[0].length;

        double[] center = colMean(x);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                x[i][j] = x[i][j] - center[j];
            }
        }

        for (int j = 0; j < p; j++) {
            double scale = 0.0;
            for (int i = 0; i < n; i++) {
                scale += Math.sqr(x[i][j]);
            }
            scale = Math.sqrt(scale / (n-1));

            if (!Math.isZero(scale)) {
                for (int i = 0; i < n; i++) {
                    x[i][j] /= scale;
                }
            }
        }
    }