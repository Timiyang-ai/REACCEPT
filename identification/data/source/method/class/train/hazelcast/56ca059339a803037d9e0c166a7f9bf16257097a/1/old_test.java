    @Test
    public void setFactoryImplementation() {
        Object mapStoreFactoryImpl = new Object();
        MapStoreConfig cfg = new MapStoreConfig().setFactoryImplementation(mapStoreFactoryImpl);
        assertEquals(mapStoreFactoryImpl, cfg.getFactoryImplementation());
        assertEquals(new MapStoreConfig().setFactoryImplementation(mapStoreFactoryImpl), cfg);
    }