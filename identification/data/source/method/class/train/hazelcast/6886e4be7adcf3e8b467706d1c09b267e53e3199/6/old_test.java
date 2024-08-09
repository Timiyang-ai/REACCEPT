    @Test
    public void getInteger() {
        HazelcastProperty property = new HazelcastProperty("key", 3);
        int ioThreadCount = defaultProperties.getInteger(property);

        assertEquals(3, ioThreadCount);
    }