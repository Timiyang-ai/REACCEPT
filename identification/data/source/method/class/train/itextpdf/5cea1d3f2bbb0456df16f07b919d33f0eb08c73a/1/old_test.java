    @Test
    public void convertCharsToBytesTest() {
        byte[] check = {check1, check2};
        char[] vals = {input};
        byte[] result = StringUtils.convertCharsToBytes(vals);

        Assert.assertArrayEquals(check, result);
    }