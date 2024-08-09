@Test
    public void testCeilPowerOfTwo() {
        assertEquals(8, TeraMath.ceilPowerOfTwo(8));
        assertEquals(8, TeraMath.ceilPowerOfTwo(7));
        assertEquals(0, TeraMath.ceilPowerOfTwo(-100));
    }