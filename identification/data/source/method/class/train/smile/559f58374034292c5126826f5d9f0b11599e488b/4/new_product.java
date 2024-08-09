public static <T> double[] cv(int k, RegressionTrainer<T> trainer, T[] x, double[] y, RegressionMeasure[] measures) {
        if (k < 2) {
            throw new IllegalArgumentException("Invalid k for k-fold cross validation: " + k);
        }
        
        int n = x.length;
        double[] predictions = new double[n];
        
        CrossValidation cv = new CrossValidation(n, k);
        for (int i = 0; i < k; i++) {
            T[] trainx = MathEx.slice(x, cv.train[i]);
            double[] trainy = MathEx.slice(y, cv.train[i]);
            
            Regression<T> model = trainer.train(trainx, trainy);

            for (int j : cv.test[i]) {
                predictions[j] = model.predict(x[j]);
            }
        }
        
        int m = measures.length;
        double[] results = new double[m];
        for (int i = 0; i < m; i++) {
            results[i] = measures[i].measure(y, predictions);
        }
        
        return results;
    }