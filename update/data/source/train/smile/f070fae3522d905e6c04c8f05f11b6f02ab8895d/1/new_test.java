@Test
    public void testNearest() {
        System.out.println("nearest");

        double[][] data = Matrix.randn(1000, 10).toArray();
        CoverTree<double[]> coverTree = new CoverTree<>(data, new EuclideanDistance());
        LinearSearch<double[]> naive = new LinearSearch<>(data, new EuclideanDistance());

        for (int i = 0; i < data.length; i++) {
            Neighbor n1 = coverTree.nearest(data[i]);
            Neighbor n2 = naive.nearest(data[i]);
            assertEquals(n1.index, n2.index);
            assertEquals(n1.value, n2.value);
            assertEquals(n1.distance, n2.distance, 1E-7);
        }
    }