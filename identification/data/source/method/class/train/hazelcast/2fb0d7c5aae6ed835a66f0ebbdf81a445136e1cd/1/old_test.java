    @Test
    public void test_init() {
        Data dataKey = serializationService.toData("dataKey");
        Data dataValue = serializationService.toData("dataValue");
        QueryableEntry queryEntry = createEntry(dataKey, dataValue, newExtractor());
        Object objectValue = queryEntry.getValue();
        Object objectKey = queryEntry.getKey();

        initEntry(queryEntry, serializationService, serializationService.toData(objectKey), objectValue, newExtractor());

        // compare references of objects since they should be cloned after QueryEntry#init call.
        assertTrue("Old dataKey should not be here", dataKey != queryEntry.getKeyData());
        assertTrue("Old dataValue should not be here", dataValue != queryEntry.getValueData());
    }