    private void rollback() {
        for (int i = txList.size() - 1; i >= 0; i--) {
            Transaction tx = txList.get(i);
            Result result = this.transactionService.rollbackTx(tx, null);
            assertTrue(result.isSuccess());
        }
        Result result = this.transactionService.rollbackTx(txList.get(0), null);
        assertFalse(result.isSuccess());
    }