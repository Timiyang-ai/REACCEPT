public CurrencyAmount currentCash(ResolvedFraTrade trade, RatesProvider provider) {
    ResolvedFra fra = trade.getProduct();
    if (fra.getPaymentDate().isEqual(provider.getValuationDate())) {
      return productPricer.presentValue(fra, provider);
    }
    return CurrencyAmount.zero(fra.getCurrency());
  }