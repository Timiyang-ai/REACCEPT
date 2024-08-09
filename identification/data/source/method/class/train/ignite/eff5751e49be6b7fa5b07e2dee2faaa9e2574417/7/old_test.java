@Test
    public void filterTest() {
        TreeFilter filter1 = features -> features[0] > 2;
        TreeFilter filter2 = features -> features[1] > 2;
        TreeFilter filterAnd = filter1.and(features -> features[1] > 2);

        TreeDataIndex filtered1 = index.filter(filter1);
        TreeDataIndex filtered2 = filtered1.filter(filter2);
        TreeDataIndex filtered3 = index.filter(filterAnd);

        assertEquals(2, filtered1.rowsCount());
        assertEquals(4, filtered1.columnsCount());
        assertEquals(1, filtered2.rowsCount());
        assertEquals(4, filtered2.columnsCount());
        assertEquals(1, filtered3.rowsCount());
        assertEquals(4, filtered3.columnsCount());

        double[] obj1 = {3, 4, 1, 2};
        double[] obj2 = {4, 1, 2, 3};
        double[][] restObjs = new double[][] {obj1, obj2};
        int[][] restObjIndxInSortedOrderPerFeatures = new int[][] {
            {0, 1}, //feature 0
            {1, 0}, //feature 1
            {0, 1}, //feature 2
            {0, 1}, //feature 3
        };

        for (int featureId = 0; featureId < filtered1.columnsCount(); featureId++) {
            for (int k = 0; k < filtered1.rowsCount(); k++) {
                int objId = restObjIndxInSortedOrderPerFeatures[featureId][k];
                double[] obj = restObjs[objId];
                assertArrayEquals(obj, filtered1.featuresInSortedOrder(k, featureId), 0.01);
            }
        }

        for (int featureId = 0; featureId < filtered2.columnsCount(); featureId++) {
            for (int k = 0; k < filtered2.rowsCount(); k++) {
                assertArrayEquals(obj1, filtered2.featuresInSortedOrder(k, featureId), 0.01);
                assertArrayEquals(obj1, filtered3.featuresInSortedOrder(k, featureId), 0.01);
            }
        }
    }