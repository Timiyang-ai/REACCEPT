    @Test
    public void writeUnsignedVarLongTest() throws IOException {
        checkUnsignedVarLong(Long.MAX_VALUE);
        checkUnsignedVarLong(0);
        checkUnsignedVarLong(1001);
    }