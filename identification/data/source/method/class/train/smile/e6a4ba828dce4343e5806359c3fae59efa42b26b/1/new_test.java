    private void impute(double[][] data, MissingValueImputation imputation, double rate, double expected) throws Exception {
        MathEx.setSeed(19650218); // to get repeatable results.

        int n = 0;
        double[][] dat = new double[data.length][data[0].length];
        for (int i = 0; i < dat.length; i++) {
            for (int j = 0; j < dat[i].length; j++) {
                if (MathEx.random() < rate) {
                    n++;
                    dat[i][j] = Double.NaN;
                } else {
                    dat[i][j] = data[i][j];                    
                }
            }
        }

        imputation.impute(dat);

        double error = 0.0;
        for (int i = 0; i < dat.length; i++) {
            for (int j = 0; j < dat[i].length; j++) {
                error += Math.abs(data[i][j] - dat[i][j]) / data[i][j];
            }
        }

        error = 100 * error / n;
        System.out.format("The error of %d%% missing values = %.2f%n", (int) (100 * rate),  error);
        assertEquals(expected, error, 1E-2);
    }