    @Test
    public void testgetObject() {
        final Map<String, Object> in = new HashMap<>();
        in.put("key", "str");

        assertEquals("str", MapUtils.getObject(in,"key", "defualt"));
        assertEquals("str", MapUtils.getObject(in,"key"));
        assertEquals(null, MapUtils.getObject(null,"key"));
        assertEquals("default", MapUtils.getObject(in,"noKey", "default"));
        assertEquals("default", MapUtils.getObject(null,"noKey", "default"));
    }