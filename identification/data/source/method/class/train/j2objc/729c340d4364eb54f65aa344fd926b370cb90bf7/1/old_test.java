    public void test_hashCode() {
        assertEquals(1, new Short((short) 1).hashCode());
        assertEquals(2, new Short((short) 2).hashCode());
        assertEquals(0, new Short((short) 0).hashCode());
        assertEquals(-1, new Short((short) -1).hashCode());
    }