    @Test
    public void writeUnsignedVarIntTest() throws IOException {
        checkUnsignedVarInt(Integer.MAX_VALUE);
        checkUnsignedVarInt(0);
        checkUnsignedVarInt(1001);
    }