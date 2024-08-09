    @Test(expected = IllegalArgumentException.class)
    public void bigIntegerToBytes_convertNegativeNumber() {
        BigInteger b = BigInteger.valueOf(-1);
        Utils.bigIntegerToBytes(b, 32);
    }