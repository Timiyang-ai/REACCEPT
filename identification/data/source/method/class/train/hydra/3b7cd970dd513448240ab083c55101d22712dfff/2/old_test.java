    @Test
    public void getBooleanValue() {
        kv.add("foo", "true");
        kv.add("toto", "0");
        assertTrue("primary key", KVUtils.getBooleanValue(kv, false, "foo", "toto"));
        assertFalse("secondary key", KVUtils.getBooleanValue(kv, true, "bogus", "toto"));
        assertTrue("default value", KVUtils.getBooleanValue(kv, true, "bogus", "bogus_too"));
    }