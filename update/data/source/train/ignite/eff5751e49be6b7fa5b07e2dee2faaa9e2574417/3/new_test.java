@Test
    public void featuresInSortedOrderTest() {
        assertEquals(features.length, idx.rowsCount());
        assertEquals(features[0].length, idx.columnsCount());

        for (int k = 0; k < idx.rowsCount(); k++) {
            for (int featureId = 0; featureId < idx.columnsCount(); featureId++)
                assertArrayEquals(featuresInSortedOrder[k][featureId], idx.featuresInSortedOrder(k, featureId), 0.01);
        }
    }