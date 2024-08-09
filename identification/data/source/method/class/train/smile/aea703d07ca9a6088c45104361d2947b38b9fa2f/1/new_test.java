@Test
    public void testLearn() {
        System.out.println("learn");

        MathEx.setSeed(19650218); // to get repeatable results.

        int size = 100;
        int generation = 20;
        ClassificationMeasure measure = new Accuracy();

        GAFeatureSelection gap = new GAFeatureSelection();
        BitString[] result = gap.learn(size, generation, USPS.x, USPS.y, USPS.testx, USPS.testy, measure, (x, y) -> LDA.fit(x, y));
            
        for (BitString bits : result) {
            System.out.format("%.2f%% %d ", 100*bits.fitness(), MathEx.sum(bits.bits()));
            for (int i = 0; i < USPS.x[0].length; i++) {
                System.out.print(bits.bits()[i] + " ");
            }
            System.out.println();
        }

        assertEquals(0.8859, result[result.length-1].fitness(), 1E-4);
    }