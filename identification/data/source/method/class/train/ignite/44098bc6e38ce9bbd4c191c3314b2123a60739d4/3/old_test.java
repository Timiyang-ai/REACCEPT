@Test
    public void testFilter() {
        double[][] features = new double[][]{{0}, {1}, {2}, {3}, {4}, {5}};
        double[] labels = new double[]{0, 1, 2, 3, 4, 5};

        DecisionTreeData data = new DecisionTreeData(features, labels);
        DecisionTreeData filteredData = data.filter(obj -> obj[0] > 2);

        assertArrayEquals(new double[][]{{3}, {4}, {5}}, filteredData.getFeatures());
        assertArrayEquals(new double[]{3, 4, 5}, filteredData.getLabels(), 1e-10);
    }