    @Test
    public void toBytes() throws Exception {
        for(String string : strings) {
            byte [] bytes = Hex.decode(string);
            assertArrayEquals(bytes, HexUtils.toBytes(string));
        }
    }