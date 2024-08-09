    @Test
    public void writeSignedVarIntTest() throws IOException {
        checkSignedVarInt(Integer.MIN_VALUE);
        checkSignedVarInt(Integer.MAX_VALUE);
        checkSignedVarInt(0);
        checkSignedVarInt(1001);
        checkSignedVarInt(-1001);
    }