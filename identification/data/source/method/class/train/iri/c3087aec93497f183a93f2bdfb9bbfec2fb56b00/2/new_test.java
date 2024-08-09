    @Test
    public void trailingZeros() throws Exception {
        Hash hash = Hash.NULL_HASH;
        Assert.assertEquals(Hash.SIZE_IN_TRITS, hash.trailingZeros());
    }