    @Test
    public void fromHexTest() {
        assertEquals(0x00, Utils.fromHex("00"));
        assertEquals(0x01, Utils.fromHex("01"));
        assertEquals(0x1F, Utils.fromHex("1F"));
        assertEquals(0xFF, Utils.fromHex("FF"));
    }