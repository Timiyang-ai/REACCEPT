public double parSpread(IborFutureTrade trade, RatesProvider provider, double lastMarginPrice) {
    double referencePrice = referencePrice(trade, provider.getValuationDate(), lastMarginPrice);
    return price(trade, provider) - referencePrice;
  }