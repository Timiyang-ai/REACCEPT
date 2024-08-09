    @Test
    public void fromByteTest() {
        byte b0 = 0;
        byte b127 = 127;
        byte b128 = (byte) 128; // overflow due to
        byte bn128 = -128; // signed bytes
        byte bn1 = -1;

        int ar0 = Utils.fromByte(b0);
        int ar127 = Utils.fromByte(b127);
        int ar128 = Utils.fromByte(b128);
        int arn128 = Utils.fromByte(bn128);
        int arn1 = Utils.fromByte(bn1);

        assertEquals(0, ar0);
        assertEquals(127, ar127);
        assertEquals(128, ar128);
        assertEquals(128, arn128);
        assertEquals(255, arn1);
    }