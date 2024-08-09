public static void normalize(double[][] x) {
        int n = x.length;
        int p = x[0].length;

        double[] min = colMin(x);
        double[] max = colMax(x);

        for (int j = 0; j < p; j++) {
            double scale = max[j] - min[j];
            if (!Math.isZero(scale)) {
                for (int i = 0; i < n; i++) {
                    x[i][j] = (x[i][j] - min[j]) / scale;
                }
            }
        }
    }