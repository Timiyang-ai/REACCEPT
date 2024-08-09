    @Test
    public void destroy() {
        manager.destroy();
        assertNull(EhCacheManager.getInstance().getCache("temp-small-block-cache"));
        assertNull(EhCacheManager.getInstance().getCache("temp-tx-cache"));
    }