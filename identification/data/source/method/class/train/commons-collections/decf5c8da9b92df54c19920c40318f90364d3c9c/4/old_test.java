    @Test
    public void testgetMap() {
        final Map<String, Map<String,String>> in = new HashMap<>();
        final Map<String, String> valMap = new HashMap<>();
        valMap.put("key1", "value1");
        in.put("key1", valMap);
        final Map<?, ?> outValue =  MapUtils.getMap(in,"key1", (Map<?, ?>) null);

        assertEquals("value1", outValue.get("key1"));
        assertEquals(null, outValue.get("key2"));
        assertEquals(null, MapUtils.getMap(in, "key2", (Map<?, ?>) null));
        assertEquals(null, MapUtils.getMap(null, "key2", (Map<?, ?>) null));
    }