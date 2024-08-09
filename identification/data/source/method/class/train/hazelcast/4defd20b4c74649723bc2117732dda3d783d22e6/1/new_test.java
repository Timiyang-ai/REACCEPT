    @Test
    public void setInitialLoadMode() {
        MapStoreConfig cfg = new MapStoreConfig().setInitialLoadMode(EAGER);
        assertEquals(EAGER, cfg.getInitialLoadMode());
        assertEquals(new MapStoreConfig().setInitialLoadMode(EAGER), cfg);
    }