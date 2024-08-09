    private void commitTx() {
        for (Transaction tx : txList) {
            Result result = this.transactionService.commitTx(tx, null);
            assertTrue(result.isSuccess());
        }
    }