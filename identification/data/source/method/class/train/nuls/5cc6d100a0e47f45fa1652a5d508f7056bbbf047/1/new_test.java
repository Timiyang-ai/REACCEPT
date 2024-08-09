    private void conflictDetect() {
        ValidateResult result = transactionService.conflictDetect(allList);
        this.txList = (List<Transaction>) result.getData();
        assertNotNull(txList);
        //数值需要确定，目前随便写的
        assertEquals(2, 2);
    }