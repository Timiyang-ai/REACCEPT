public static <T> double[][] bootstrap(int k, RegressionTrainer<T> trainer, T[] x, double[] y, RegressionMeasure[] measures) {
        if (k < 2) {
            throw new IllegalArgumentException("Invalid k for k-fold bootstrap: " + k);
        }
        
        int n = x.length;
        int m = measures.length;
        double[][] results = new double[k][m];
        
        Bootstrap bootstrap = new Bootstrap(n, k);
        for (int i = 0; i < k; i++) {
            T[] trainx = Math.slice(x, bootstrap.train[i]);
            double[] trainy = Math.slice(y, bootstrap.train[i]);
            
            Regression<T> model = trainer.train(trainx, trainy);

            int nt = bootstrap.test[i].length;
            double[] truth = new double[nt];
            double[] predictions = new double[nt];
            for (int j = 0; j < nt; j++) {
                int l = bootstrap.test[i][j];
                truth[j] = y[l];
                predictions[j] = model.predict(x[l]);
            }

            for (int j = 0; j < m; j++) {
                results[i][j] = measures[j].measure(truth, predictions);
            }
        }
        
        return results;
    }