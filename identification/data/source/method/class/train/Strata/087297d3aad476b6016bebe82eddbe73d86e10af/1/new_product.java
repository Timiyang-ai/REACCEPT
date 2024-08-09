public double price(ResolvedDsf future, RatesProvider ratesProvider) {
    ResolvedSwap swap = future.getUnderlyingSwap();
    Currency currency = future.getCurrency();
    CurrencyAmount pvSwap = swapPricer.presentValue(swap, currency, ratesProvider);
    double df = ratesProvider.discountFactor(currency, future.getDeliveryDate());
    return 1d + pvSwap.getAmount() / df;
  }