    private void broadcastTx() {
        for (int i = txList.size() - 1; i >= 0; i--) {
            Transaction tx = txList.get(i);
            Result result = this.transactionService.forwardTx(tx, null);
            assertTrue(result.isSuccess());
        }
    }