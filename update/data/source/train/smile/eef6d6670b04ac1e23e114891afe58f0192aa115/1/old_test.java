@Test
    public void testLearn_3args() {
        System.out.println("learn");
        ArffParser arffParser = new ArffParser();
        arffParser.setResponseIndex(4);
        try {
            AttributeDataset iris = arffParser.parse(smile.util.Paths.getTestData("weka/iris.arff"));
            double[][] x = iris.toArray(new double[0][]);
            int[] y = iris.toArray(new int[0]);

            KNN<double[]> knn = KNN.learn(x, y, 1);
            int error = 0;
            for (int i = 0; i < x.length; i++) {
                if (knn.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("1-nn error = " + error);
            assertEquals(6, error);

            knn = KNN.learn(x, y, 3);
            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (knn.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("3-nn error = " + error);
            assertEquals(6, error);

            knn = KNN.learn(x, y, 5);
            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (knn.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("5-nn error = " + error);
            assertEquals(5, error);

            knn = KNN.learn(x, y, 7);
            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (knn.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("7-nn error = " + error);
            assertEquals(5, error);

            knn = KNN.learn(x, y, 9);
            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (knn.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("9-nn error = " + error);
            assertEquals(5, error);

            knn = KNN.learn(x, y, 11);
            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (knn.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("11-nn error = " + error);
            assertEquals(4, error);

            knn = KNN.learn(x, y, 13);
            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (knn.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("13-nn error = " + error);
            assertEquals(5, error);

            knn = KNN.learn(x, y, 15);
            error = 0;
            for (int i = 0; i < x.length; i++) {
                if (knn.predict(x[i]) != y[i]) {
                    error++;
                }
            }
            System.out.println("15-nn error = " + error);
            assertEquals(4, error);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }