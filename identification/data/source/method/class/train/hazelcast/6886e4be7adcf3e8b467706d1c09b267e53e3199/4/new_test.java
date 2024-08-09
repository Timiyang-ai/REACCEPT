    @Test
    public void getString_whenDeprecatedNameUsed() {
        Properties props = new Properties();
        props.setProperty("oldname", "10");
        HazelcastProperties properties = new HazelcastProperties(props);

        HazelcastProperty property = new HazelcastProperty("newname")
                .setDeprecatedName("oldname");
        String value = properties.getString(property);

        assertEquals("10", value);
    }