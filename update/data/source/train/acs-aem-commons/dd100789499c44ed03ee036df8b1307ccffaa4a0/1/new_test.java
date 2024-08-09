@Test
    public void testArrayToMap() {
        String[] list = new String[]{"key1", "value1", "key2", "value2", "key3", "value3"};
        Map expResult = new HashMap<String, String>();
        expResult.put("key1", "value1");
        expResult.put("key2", "value2");
        expResult.put("key3", "value3");

        Map result = TypeUtil.arrayToMap(list);
        assertEquals(expResult, result);
    }