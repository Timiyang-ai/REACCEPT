    @Test
    public void getTransactionInfo() {
        Block block = getBlockWithOneTransaction();

        Assert.assertEquals(ImportResult.IMPORTED_BEST, blockChain.tryToConnect(block));

        Transaction tx = block.getTransactionsList().get(0);
        Assert.assertNotNull(blockChain.getTransactionInfo(tx.getHash().getBytes()));
    }