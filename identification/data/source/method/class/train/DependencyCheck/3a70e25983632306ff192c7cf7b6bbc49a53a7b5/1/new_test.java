@Test
    public void testAdd() throws Exception {
        String id = "key";
        String url = new File("target/test-classes/nvdcve-2.0-2012.xml").toURI().toString();
        long timestamp = 42;
        UpdateableNvdCve instance = new UpdateableNvdCve();
        instance.add(id, url, url, timestamp, false);

        boolean expResult = false;
        boolean result = instance.isUpdateNeeded();
        assertEquals(expResult, result);

        instance.add("nextId", url, url, 23, false);
        NvdCveInfo results = instance.get(id);

        assertEquals(id, results.getId());
        assertEquals(url, results.getUrl());
        assertEquals(url, results.getOldSchemaVersionUrl());
        assertEquals(timestamp, results.getTimestamp());
    }