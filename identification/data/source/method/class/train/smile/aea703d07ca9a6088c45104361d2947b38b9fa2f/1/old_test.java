@Test
    public void testLearn() {
        System.out.println("learn");

        int size = 100;
        int generation = 20;
        ClassifierTrainer<double[]> trainer = new LDA.Trainer();
        ClassificationMeasure measure = new Accuracy();

        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setResponseIndex(new NominalAttribute("class"), 0);
        try {
            AttributeDataset train = parser.parse("USPS Train", smile.util.Paths.getTestData("usps/zip.train"));
            AttributeDataset test = parser.parse("USPS Test", smile.util.Paths.getTestData("usps/zip.test"));

            double[][] x = train.toArray(new double[train.size()][]);
            int[] y = train.toArray(new int[train.size()]);
            double[][] testx = test.toArray(new double[test.size()][]);
            int[] testy = test.toArray(new int[test.size()]);

            GAFeatureSelection instance = new GAFeatureSelection();
            BitString[] result = instance.learn(size, generation, trainer, measure, x, y, testx, testy);
            
            for (BitString bits : result) {
                System.out.format("%.2f%% %d ", 100*bits.fitness(), MathEx.sum(bits.bits()));
                for (int i = 0; i < x[0].length; i++) {
                    System.out.print(bits.bits()[i] + " ");
                }
                System.out.println();
            }

            assertTrue(result[result.length-1].fitness() > 0.88);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }