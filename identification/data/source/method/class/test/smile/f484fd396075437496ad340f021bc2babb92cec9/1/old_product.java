public static void normalize(double[][] x) {
        int n = x.length;
        int p = x[0].length;

        for (int j = 0; j < p; j++) {
            double mu = 0.0;
            double sd = 0.0;
            for (int i = 0; i < n; i++) {
                mu += x[i][j];
                sd += x[i][j] * x[i][j];
            }

            sd = Math.sqrt(sd / (n-1) - (mu / n) * (mu / (n-1)));
            mu /= n;

            if (sd <= 0) {
                throw new IllegalArgumentException(String.format("Column %d has variance of 0.", j));
            }

            for (int i = 0; i < n; i++) {
                x[i][j] = (x[i][j] - mu) / sd;
            }
        }
    }