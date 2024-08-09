@Test
    public void labelInSortedOrderTest() {
        assertEquals(features.length, index.rowsCount());
        assertEquals(features[0].length, index.columnsCount());

        for (int k = 0; k < index.rowsCount(); k++) {
            for (int featureId = 0; featureId < index.columnsCount(); featureId++)
                assertEquals(labelsInSortedOrder[k][featureId], index.labelInSortedOrder(k, featureId), 0.01);
        }
    }