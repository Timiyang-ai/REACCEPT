public final void operate(int index, Decimal price, Decimal amount) {
        if (currentTrade.isClosed()) {
            // Current trade closed, should not occur
            throw new IllegalStateException("Current trade should not be closed");
        }
        boolean newOrderWillBeAnEntry = currentTrade.isNew();
        Order newOrder = currentTrade.operate(index, price, amount);
        recordOrder(newOrder, newOrderWillBeAnEntry);
    }