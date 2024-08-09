@Test
    public void testLloyd64() {
        System.out.println("Lloyd 64");
        MathEx.setSeed(19650218); // to get repeatable results.
        KMeans kmeans = KMeans.lloyd(data, 64);
        AdjustedRandIndex ari = new AdjustedRandIndex();
        RandIndex rand = new RandIndex();
        double r = rand.measure(label, kmeans.y);
        double r2 = ari.measure(label, kmeans.y);
        System.out.format("Training rand index = %.2f%%\tadjusted rand index = %.2f%%%n", 100.0 * r, 100.0 * r2);
    }