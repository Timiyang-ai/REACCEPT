    @Test
    public void hashToIndex_whenHashPositive() {
        assertEquals(hashToIndex(20, 100), 20);
        assertEquals(hashToIndex(420, 100), 20);
    }