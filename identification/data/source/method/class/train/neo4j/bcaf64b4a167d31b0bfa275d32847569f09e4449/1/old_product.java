public void blockNewTransactions()
    {
        newTransactionsLock.writeLock().lock();
    }