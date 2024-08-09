    @Test
    public void setImplementation() {
        Object mapStoreImpl = new Object();
        MapStoreConfig cfg = new MapStoreConfig().setImplementation(mapStoreImpl);
        assertEquals(mapStoreImpl, cfg.getImplementation());
        assertEquals(new MapStoreConfig().setImplementation(mapStoreImpl), cfg);
    }