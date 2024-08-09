@Test
    public void testPutVar32() throws Exception {
        checkVarInt(Integer.MAX_VALUE, 5);
        checkVarInt(25, 1);
        checkVarInt(100, 1);

        checkVarInt(Integer.MIN_VALUE, -1);

        checkVarInt(Integer.MAX_VALUE / 2, -1);
        checkVarInt(Integer.MAX_VALUE / 10, -1);
        checkVarInt(Integer.MAX_VALUE / 10000, -1);

        checkVarInt(Integer.MIN_VALUE / 2, -1);
        checkVarInt(Integer.MIN_VALUE / 10, -1);
        checkVarInt(Integer.MIN_VALUE / 10000, -1);


        checkVarInt(0, -1);
        checkVarInt(127, -1);
        checkVarInt(128, -1);
        checkVarInt(16383, -1);
        checkVarInt(16384, -1);
        checkVarInt(268435455, -1);
        checkVarInt(268435456, -1);

    }