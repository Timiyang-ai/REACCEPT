@Test
    public void testCalculate() {
        double[][] data = new double[][]{{0, 1}, {1, 0}, {2, 2}, {3, 3}};
        double[] labels = new double[]{0, 1, 1, 1};

        Map<Double, Integer> encoder = new HashMap<>();
        encoder.put(0.0, 0);
        encoder.put(1.0, 1);
        GiniImpurityMeasureCalculator calculator = new GiniImpurityMeasureCalculator(encoder);

        StepFunction<GiniImpurityMeasure>[] impurity = calculator.calculate(new DecisionTreeData(data, labels));

        assertEquals(2, impurity.length);

        // Check Gini calculated for the first column.
        assertArrayEquals(new double[]{Double.NEGATIVE_INFINITY, 0, 1, 2, 3}, impurity[0].getX(), 1e-10);
        assertEquals(-2.500, impurity[0].getY()[0].impurity(), 1e-3);
        assertEquals(-4.000, impurity[0].getY()[1].impurity(),1e-3);
        assertEquals(-3.000, impurity[0].getY()[2].impurity(),1e-3);
        assertEquals(-2.666, impurity[0].getY()[3].impurity(),1e-3);
        assertEquals(-2.500, impurity[0].getY()[4].impurity(),1e-3);

        // Check Gini calculated for the second column.
        assertArrayEquals(new double[]{Double.NEGATIVE_INFINITY, 0, 1, 2, 3}, impurity[1].getX(), 1e-10);
        assertEquals(-2.500, impurity[1].getY()[0].impurity(),1e-3);
        assertEquals(-2.666, impurity[1].getY()[1].impurity(),1e-3);
        assertEquals(-3.000, impurity[1].getY()[2].impurity(),1e-3);
        assertEquals(-2.666, impurity[1].getY()[3].impurity(),1e-3);
        assertEquals(-2.500, impurity[1].getY()[4].impurity(),1e-3);
    }