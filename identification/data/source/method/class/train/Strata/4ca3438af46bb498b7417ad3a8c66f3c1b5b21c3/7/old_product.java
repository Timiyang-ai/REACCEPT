public CurrencyAmount currentCash(FraTrade trade, RatesProvider provider) {
    ExpandedFra fra = trade.getProduct().expand();
    if (fra.getPaymentDate().isEqual(provider.getValuationDate())) {
      return productPricer.presentValue(fra, provider);
    }
    return CurrencyAmount.zero(fra.getCurrency());
  }