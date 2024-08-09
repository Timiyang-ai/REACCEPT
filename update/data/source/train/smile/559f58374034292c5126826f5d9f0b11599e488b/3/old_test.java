@Test
    public void testCv_5args_2() {
        System.out.println("cv");
        ArffParser parser = new ArffParser();
        parser.setResponseIndex(6);
        try {
            AttributeDataset data = parser.parse(smile.data.parser.IOUtils.getTestDataFile("weka/cpu.arff"));
            double[] y = data.toArray(new double[data.size()]);
            double[][] x = data.toArray(new double[data.size()][]);
            Math.standardize(x);

            RBFNetwork.Trainer<double[]> trainer = new RBFNetwork.Trainer<>(new EuclideanDistance());
            trainer.setNumCenters(20);
            RegressionMeasure[] measures = {new RMSE(), new MeanAbsoluteDeviation()};
            double[] results = Validation.cv(10, trainer, x, y, measures);
            System.out.println("RMSE = " + results[0]);
            System.out.println("MAD = " + results[1]);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }