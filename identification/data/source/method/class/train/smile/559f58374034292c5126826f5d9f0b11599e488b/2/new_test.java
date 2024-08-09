@Test
    public void testLearn() {
        System.out.println("learn");
        ArffParser arffParser = new ArffParser();
        arffParser.setResponseIndex(4);
        try {
            AttributeDataset iris = arffParser.parse(smile.data.parser.IOUtils.getTestDataFile("weka/iris.arff"));
            double[][] x = iris.toArray(new double[iris.size()][]);
            int[] y = iris.toArray(new int[iris.size()]);

            SVM<double[]> svm = new SVM<>(new LinearKernel(), 10.0, MathEx.max(y) + 1, SVM.Multiclass.ONE_VS_ALL);
            svm.learn(x, y);
            svm.learn(x, y);
            svm.finish();
            
            int error = 0;
            for (int i = 0; i < x.length; i++) {
                if (svm.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("Linear ONE vs. ALL error = " + error);
            assertTrue(error <= 10);

            svm = new SVM<>(new GaussianKernel(1), 1.0, MathEx.max(y) + 1, SVM.Multiclass.ONE_VS_ALL);
            svm.learn(x, y);
            svm.learn(x, y);
            svm.finish();
            svm.trainPlattScaling(x, y);

            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (svm.predict(x[i]) != y[i]) {
                    error++;
                }
                double[] prob = new double[3];
                int yp = svm.predict(x[i], prob);
                //System.out.format("%d %d %.2f, %.2f %.2f\n", y[i], yp, prob[0], prob[1], prob[2]);
            }
            System.out.println("Gaussian ONE vs. ALL error = " + error);
            assertTrue(error <= 5);

            svm = new SVM<>(new GaussianKernel(1), 1.0, MathEx.max(y) + 1, SVM.Multiclass.ONE_VS_ONE);
            svm.learn(x, y);
            svm.learn(x, y);
            svm.finish();
            assertTrue(!svm.hasPlattScaling());
            svm.trainPlattScaling(x, y);
            assertTrue(svm.hasPlattScaling());

            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (svm.predict(x[i]) != y[i]) {
                    error++;
                }
                double[] prob = new double[3];
                int yp = svm.predict(x[i], prob);
                //System.out.format("%d %d %.2f, %.2f %.2f\n", y[i], yp, prob[0], prob[1], prob[2]);
            }
            System.out.println("Gaussian ONE vs. ONE error = " + error);
            assertTrue(error <= 5);

            svm = new SVM<>(new PolynomialKernel(2), 1.0, MathEx.max(y) + 1, SVM.Multiclass.ONE_VS_ALL);
            svm.learn(x, y);
            svm.learn(x, y);
            svm.finish();
            
            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (svm.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("Polynomial ONE vs. ALL error = " + error);
            assertTrue(error <= 5);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }