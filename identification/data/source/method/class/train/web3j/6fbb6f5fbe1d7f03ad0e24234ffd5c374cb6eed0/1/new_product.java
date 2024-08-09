public RemoteCall<TransactionReceipt> sendFunds(
            final String toAddress, final BigDecimal value, final Convert.Unit unit) {
        return new RemoteCall<TransactionReceipt>(new Callable<TransactionReceipt>() {
            @Override
            public TransactionReceipt call() throws Exception {
                return Transfer.this.send(toAddress, value, unit);
            }
        });
    }