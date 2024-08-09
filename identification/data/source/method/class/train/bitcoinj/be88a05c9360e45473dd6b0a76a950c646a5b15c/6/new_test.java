    @Test
    public void getMinNonDustValue() throws Exception {
        TransactionOutput p2pk = new TransactionOutput(UNITTEST, null, Coin.COIN, myKey);
        assertEquals(Coin.valueOf(576), p2pk.getMinNonDustValue());
        TransactionOutput p2pkh = new TransactionOutput(UNITTEST, null, Coin.COIN, LegacyAddress.fromKey(UNITTEST, myKey));
        assertEquals(Coin.valueOf(546), p2pkh.getMinNonDustValue());
        TransactionOutput p2wpkh = new TransactionOutput(UNITTEST, null, Coin.COIN, SegwitAddress.fromKey(UNITTEST, myKey));
        assertEquals(Coin.valueOf(294), p2wpkh.getMinNonDustValue());
    }