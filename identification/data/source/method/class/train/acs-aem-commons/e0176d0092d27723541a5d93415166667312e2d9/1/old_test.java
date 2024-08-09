@Test
    public void testToMap() {
        String[] values = {"key1:value1", "key2:value2", "key3:value3"};
        String separator = ":";
        Map expResult = new HashMap<String, String>();
        expResult.put("key1", "value1");
        expResult.put("key2", "value2");
        expResult.put("key3", "value3");

        Map result = OsgiPropertyUtil.toMap(values, separator);
        assertEquals(expResult, result);
    }