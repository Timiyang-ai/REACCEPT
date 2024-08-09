    @Test
    public void calculate() throws Exception {
        Hash hash = TransactionHash.calculate(SpongeFactory.Mode.CURLP81, getTransactionTrits());
        Assert.assertNotEquals(0, hash.hashCode());
        Assert.assertNotEquals(null, hash.bytes());
        Assert.assertNotEquals(null, hash.trits());
    }