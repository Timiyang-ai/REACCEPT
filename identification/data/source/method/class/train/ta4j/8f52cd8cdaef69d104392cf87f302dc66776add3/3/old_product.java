public final void operate(int index, Decimal price, Decimal amount) {
        recordOrder(currentTrade.operate(index, price, amount));
        recordTrade();
    }