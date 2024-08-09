    public void test_hashCode() {
        assertEquals(1, new Byte((byte) 1).hashCode());
        assertEquals(2, new Byte((byte) 2).hashCode());
        assertEquals(0, new Byte((byte) 0).hashCode());
        assertEquals(-1, new Byte((byte) -1).hashCode());
    }