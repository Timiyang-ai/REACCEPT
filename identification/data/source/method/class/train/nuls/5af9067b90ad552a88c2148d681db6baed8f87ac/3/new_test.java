    public void clear() {
        manager.clear();
        assertEquals(manager.getSmallBlockCount(), 0);
        assertEquals(manager.getTxCount(), 0);
    }