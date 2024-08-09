    @Test
    public void getBitsTest() {
        boolean b1[] = Utils.getBits(0xFF);

        assertEquals(b1.length, 8);
        for (int i = 0; i < 8; i++) {
            assertEquals(true, b1[i]);
        }

        boolean b2[] = Utils.getBits(0x5A);

        assertEquals(b2.length, 8);
        assertEquals(false, b2[0]);
        assertEquals(true, b2[1]);
        assertEquals(false, b2[2]);
        assertEquals(true, b2[3]);
        assertEquals(true, b2[4]);
        assertEquals(false, b2[5]);
        assertEquals(true, b2[6]);
        assertEquals(false, b2[7]);
    }