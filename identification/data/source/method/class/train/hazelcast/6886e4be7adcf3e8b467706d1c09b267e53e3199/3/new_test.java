    @Test
    public void getFloat() {
        HazelcastProperty property = new HazelcastProperty("foo", "10");

        float maxFileSize = defaultProperties.getFloat(property);

        assertEquals(10, maxFileSize, 0.0001);
    }