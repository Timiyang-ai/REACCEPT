@Test
    public void testCalculateIndexUpdates_DeleteRow_NoRowFieldDefinedForIndexer() throws IOException {
        KeyValue toDelete = new KeyValue("_row_".getBytes(), "_cf_".getBytes(), "_qual_".getBytes(), 0L, Type.Delete);
        RowData eventRowData = createEventRowData("_row_", toDelete);
        
        indexer.calculateIndexUpdates(ImmutableList.of(eventRowData), updateCollector);

        assertTrue(updateCollector.getDeleteQueries().isEmpty());
        assertTrue(updateCollector.getIdsToDelete().isEmpty());
        assertTrue(updateCollector.getDocumentsToAdd().isEmpty());
    }