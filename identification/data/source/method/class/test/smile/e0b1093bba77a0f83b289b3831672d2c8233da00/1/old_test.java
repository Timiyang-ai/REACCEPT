@Test
    public void testLloyd64() {
        System.out.println("Lloyd 64");
        KMeans kmeans = KMeans.lloyd(data, 64, 100);
        AdjustedRandIndex ari = new AdjustedRandIndex();
        RandIndex rand = new RandIndex();
        double r = rand.measure(label, kmeans.getClusterLabel());
        double r2 = ari.measure(label, kmeans.getClusterLabel());
        System.out.format("Training rand index = %.2f%%\tadjusted rand index = %.2f%%%n", 100.0 * r, 100.0 * r2);
    }