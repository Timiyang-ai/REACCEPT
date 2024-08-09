    @Test
    public void getLongValue() {
        kv.add("foo", "1");
        kv.add("toto", "2");
        assertEquals("primary key", new Long(1), KVUtils.getLongValue(kv, 0L, "foo", "toto"));
        assertEquals("secondary key", new Long(2), KVUtils.getLongValue(kv, 0L, "bogus", "toto"));
        assertEquals("default value", new Long(0), KVUtils.getLongValue(kv, 0L, "bogus", "bogus_too"));
    }