@Test
    public void testBootstrap_4args_1() {
        System.out.println("bootstrap");
        ArffParser arffParser = new ArffParser();
        arffParser.setResponseIndex(4);
        try {
            AttributeDataset iris = arffParser.parse(smile.data.parser.IOUtils.getTestDataFile("weka/iris.arff"));
            double[][] x = iris.toArray(new double[iris.size()][]);
            int[] y = iris.toArray(new int[iris.size()]);

            ClassifierTrainer<double[]> trainer = new LDA.Trainer();
            double[] accuracy = Validation.bootstrap(100, trainer, x, y);
            
            System.out.println("100-fold bootstrap accuracy average = " + Math.mean(accuracy));
            System.out.println("100-fold bootstrap accuracy std.dev = " + Math.sd(accuracy));
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }