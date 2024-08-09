    @Test
    public void writeSignedVarLongTest() throws IOException {
        checkSignedVarLong(Long.MIN_VALUE);
        checkSignedVarLong(Long.MAX_VALUE);
        checkSignedVarLong(0);
        checkSignedVarLong(1001);
        checkSignedVarLong(-1001);
    }