    @Test
    public void setEnabled() {
        assertFalse(cfgNotEnabled.isEnabled());
        assertEquals(new MapStoreConfig().setEnabled(false), cfgNotEnabled);
    }