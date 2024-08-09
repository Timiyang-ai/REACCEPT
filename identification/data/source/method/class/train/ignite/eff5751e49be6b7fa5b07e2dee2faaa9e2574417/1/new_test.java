@Test
    public void labelInSortedOrderTest() {
        assertEquals(features.length, idx.rowsCount());
        assertEquals(features[0].length, idx.columnsCount());

        for (int k = 0; k < idx.rowsCount(); k++) {
            for (int featureId = 0; featureId < idx.columnsCount(); featureId++)
                assertEquals(labelsInSortedOrder[k][featureId], idx.labelInSortedOrder(k, featureId), 0.01);
        }
    }