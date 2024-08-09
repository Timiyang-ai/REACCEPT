public FxRate forwardFxRate(ResolvedFxSingle fx, RatesProvider provider) {
    FxForwardRates fxForwardRates = provider.fxForwardRates(fx.getCurrencyPair());
    Payment basePayment = fx.getBaseCurrencyPayment();
    Payment counterPayment = fx.getCounterCurrencyPayment();
    double forwardRate = fxForwardRates.rate(basePayment.getCurrency(), fx.getPaymentDate());
    return FxRate.of(basePayment.getCurrency(), counterPayment.getCurrency(), forwardRate);
  }