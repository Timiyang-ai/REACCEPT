    @Test
    public void trits() throws Exception {
        Hash hash = TransactionHash.calculate(SpongeFactory.Mode.CURLP81, getTransactionTrits());
        Assert.assertFalse(Arrays.equals(new byte[Hash.SIZE_IN_TRITS], hash.trits()));
    }