    @Test
    public void getValue() {
        kv.add("foo", "bar");
        kv.add("toto", "tata");
        assertEquals("primary key", "bar", KVUtils.getValue(kv, "default", "foo", "toto"));
        assertEquals("secondary key", "tata", KVUtils.getValue(kv, "default", "bogus", "toto"));
        assertEquals("default value", "default", KVUtils.getValue(kv, "default", "bogus", "bogus_too"));
    }