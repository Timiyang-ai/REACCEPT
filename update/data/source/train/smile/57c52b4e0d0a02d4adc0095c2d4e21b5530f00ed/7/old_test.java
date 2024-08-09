@Test
    public void testPredict() {
        System.out.println("predict");
        ArffParser arffParser = new ArffParser();
        arffParser.setResponseIndex(4);
        try {
            AttributeDataset iris = arffParser.parse(smile.data.parser.IOUtils.getTestDataFile("weka/iris.arff"));
            double[][] x = iris.toArray(new double[iris.size()][]);
            int[] y = iris.toArray(new int[iris.size()]);

            int n = x.length;
            LOOCV loocv = new LOOCV(n);
            int error = 0;
            for (int l = 0; l < n; l++) {
                double[][] trainx = Math.slice(x, loocv.train[l]);
                int[] trainy = Math.slice(y, loocv.train[l]);

                int p = trainx[0].length;
                int k = Math.max(trainy) + 1;

                double[] priori = new double[k];
                Distribution[][] condprob = new Distribution[k][p];
                for (int i = 0; i < k; i++) {
                    priori[i] = 1.0 / k;
                    for (int j = 0; j < p; j++) {
                        ArrayList<Double> axi = new ArrayList<Double>();
                        for (int m = 0; m < trainx.length; m++) {
                            if (trainy[m] == i) {
                                axi.add(trainx[m][j]);
                            }
                        }

                        double[] xi = new double[axi.size()];
                        for (int m = 0; m < xi.length; m++) {
                            xi[m] = axi.get(m);
                        }

                        condprob[i][j] = new GaussianMixture(xi, 3);
                    }
                }

                NaiveBayes bayes = new NaiveBayes(priori, condprob);

                if (y[loocv.test[l]] != bayes.predict(x[loocv.test[l]]))
                    error++;
            }

            System.out.format("Iris error rate = %.2f%%%n", 100.0 * error / x.length);
            assertEquals(5, error);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }