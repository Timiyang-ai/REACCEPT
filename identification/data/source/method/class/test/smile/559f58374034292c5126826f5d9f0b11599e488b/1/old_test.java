@Test
    public void testPredict() {
        System.out.println("predict");

        for (int lambda = 0; lambda <= 20; lambda+=2) {
            int n = longley.length;

            LOOCV loocv = new LOOCV(n);
            double rss = 0.0;
            for (int i = 0; i < n; i++) {
                double[][] trainx = Math.slice(longley, loocv.train[i]);
                double[] trainy = Math.slice(y, loocv.train[i]);
                RidgeRegression ridge = new RidgeRegression(trainx, trainy, 0.01*lambda);

                double r = y[loocv.test[i]] - ridge.predict(longley[loocv.test[i]]);
                rss += r * r;
            }

            System.out.format("LOOCV MSE with lambda %.2f = %.3f%n", 0.01*lambda, rss/n);
        }
    }