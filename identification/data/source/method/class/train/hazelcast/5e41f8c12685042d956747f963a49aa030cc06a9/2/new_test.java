    @Test
    public void setProperties() {
        Properties properties = new Properties();
        properties.put("a", "b");
        MapStoreConfig cfg = new MapStoreConfig().setProperties(properties);
        assertEquals(properties, cfg.getProperties());
        assertEquals("b", cfg.getProperty("a"));
        Properties otherProperties = new Properties();
        otherProperties.put("a", "b");
        assertEquals(new MapStoreConfig().setProperties(otherProperties), cfg);
    }