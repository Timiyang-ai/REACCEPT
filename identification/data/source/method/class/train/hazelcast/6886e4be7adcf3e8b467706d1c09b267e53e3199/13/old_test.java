    @Test
    public void getBoolean() {
        HazelcastProperty property = new HazelcastProperty("foo", "true");

        boolean isHumanReadable = defaultProperties.getBoolean(property);

        assertTrue(isHumanReadable);
    }