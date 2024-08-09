@Test
    public void featuresInSortedOrderTest() {
        assertEquals(features.length, index.rowsCount());
        assertEquals(features[0].length, index.columnsCount());

        for (int k = 0; k < index.rowsCount(); k++) {
            for (int featureId = 0; featureId < index.columnsCount(); featureId++)
                assertArrayEquals(featuresInSortedOrder[k][featureId], index.featuresInSortedOrder(k, featureId), 0.01);
        }
    }