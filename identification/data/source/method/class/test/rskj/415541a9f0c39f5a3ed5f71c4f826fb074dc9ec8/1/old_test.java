    @Test
    public void toJsonHex() {
        Assert.assertEquals("0x20", TypeConverter.toJsonHex(new byte[] { 32 }));
    }