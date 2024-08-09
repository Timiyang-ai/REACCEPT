@Test
    public void testAdd() throws Exception {
        String id = "key";
        //use a local file as this test will load the result and check the timestamp
        String url = new File("target/test-classes/nvdcve-2.0-2012.xml").toURI().toString();
        UpdateableNvdCve instance = new UpdateableNvdCve();
        instance.add(id, url, url, false);

        boolean expResult = false;
        boolean result = instance.isUpdateNeeded();
        assertEquals(expResult, result);

        instance.add("nextId", url, url, false);
        NvdCveInfo results = instance.get(id);

        assertEquals(id, results.getId());
        assertEquals(url, results.getUrl());
        assertEquals(url, results.getOldSchemaVersionUrl());

    }