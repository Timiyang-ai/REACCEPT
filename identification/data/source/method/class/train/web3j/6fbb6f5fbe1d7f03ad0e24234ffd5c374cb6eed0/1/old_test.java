    protected TransactionReceipt sendFunds(
            Credentials credentials, String toAddress, BigDecimal value, Convert.Unit unit)
            throws Exception {
        return new Transfer(web3j, getVerifiedTransactionManager(credentials))
                .sendFunds(toAddress, value, unit)
                .send();
    }