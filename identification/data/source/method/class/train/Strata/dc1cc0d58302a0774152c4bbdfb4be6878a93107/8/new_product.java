public double parSpread(ResolvedIborFutureTrade trade, RatesProvider ratesProvider, double lastSettlementPrice) {
    double referencePrice = referencePrice(trade, ratesProvider.getValuationDate(), lastSettlementPrice);
    return price(trade, ratesProvider) - referencePrice;
  }