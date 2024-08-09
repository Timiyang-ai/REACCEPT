    @Test
    public void testToProperties() {
        final Map<String, String> in = new HashMap<>();
        in.put("key1", "A");
        in.put("key2", "B");
        in.put("key3", "C");

        final Properties out =  MapUtils.toProperties(in);

        assertEquals(in.get("key1"), out.get("key1"));
        assertEquals(in.get("key2"), out.get("key2"));
        assertEquals(in.get("key3"), out.get("key3"));
    }