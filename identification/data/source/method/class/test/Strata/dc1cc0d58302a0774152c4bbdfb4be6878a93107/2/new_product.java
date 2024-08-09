public double parSpread(ResolvedIborFutureTrade trade, RatesProvider provider, double settlementPrice) {
    double referencePrice = referencePrice(trade, provider.getValuationDate(), settlementPrice);
    return price(trade, provider) - referencePrice;
  }