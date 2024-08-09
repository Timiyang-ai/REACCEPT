    @Test
    public void isConsistent_duplicates() throws Exception {
        // This test ensures that isConsistent catches duplicate transactions, eg, because we submitted the same block
        // twice (this is not allowed).
        Transaction tx = createFakeTx(UNITTEST, COIN, myAddress);
        TransactionOutput output = new TransactionOutput(UNITTEST, tx, valueOf(0, 5), OTHER_ADDRESS);
        tx.addOutput(output);
        wallet.receiveFromBlock(tx, null, BlockChain.NewBlockType.BEST_CHAIN, 0);

        assertTrue(wallet.isConsistent());

        Transaction txClone = UNITTEST.getDefaultSerializer().makeTransaction(tx.bitcoinSerialize());
        try {
            wallet.receiveFromBlock(txClone, null, BlockChain.NewBlockType.BEST_CHAIN, 0);
            fail("Illegal argument not thrown when it should have been.");
        } catch (IllegalStateException ex) {
            // expected
        }
    }