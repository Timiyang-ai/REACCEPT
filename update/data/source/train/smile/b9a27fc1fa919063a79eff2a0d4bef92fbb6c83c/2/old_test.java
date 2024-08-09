@Test
    public void testLearn() {
        System.out.println("learn");
        ArffParser arffParser = new ArffParser();
        arffParser.setResponseIndex(4);
        try {
            AttributeDataset iris = arffParser.parse(this.getClass().getResourceAsStream("/smile/data/weka/iris.arff"));
            double[][] x = iris.toArray(new double[iris.size()][]);
            int[] y = iris.toArray(new int[iris.size()]);

            SVM<double[]> svm = new SVM<double[]>(new LinearKernel(), 10.0, Math.max(y)+1, SVM.Multiclass.ONE_VS_ALL);
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

            svm = new SVM<double[]>(new GaussianKernel(1), 1.0, Math.max(y)+1, SVM.Multiclass.ONE_VS_ALL);
            svm.learn(x, y);
            svm.learn(x, y);
            svm.finish();
            
            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (svm.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("Gaussian ONE vs. ALL error = " + error);
            assertTrue(error <= 5);

            svm = new SVM<double[]>(new GaussianKernel(1), 1.0, Math.max(y)+1, SVM.Multiclass.ONE_VS_ONE);
            svm.learn(x, y);
            svm.learn(x, y);
            svm.finish();
            
            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (svm.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("Gaussian ONE vs. ONE error = " + error);
            assertTrue(error <= 5);

            svm = new SVM<double[]>(new PolynomialKernel(2), 1.0, Math.max(y)+1, SVM.Multiclass.ONE_VS_ALL);
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
            System.err.println(ex);
        }
    }