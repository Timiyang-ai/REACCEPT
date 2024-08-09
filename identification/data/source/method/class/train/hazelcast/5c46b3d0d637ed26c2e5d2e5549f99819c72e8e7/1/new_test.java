    @Test
    public void setWriteCoalescing() {
        MapStoreConfig cfg = new MapStoreConfig();
        cfg.setWriteCoalescing(false);
        assertFalse(cfg.isWriteCoalescing());
        MapStoreConfig otherCfg = new MapStoreConfig();
        otherCfg.setWriteCoalescing(false);
        assertEquals(otherCfg, cfg);
    }