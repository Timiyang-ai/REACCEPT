@Override
  public double fxRate(Currency baseCurrency, Currency counterCurrency) {
    if (baseCurrency.equals(counterCurrency)) {
      return 1;
    }
    // Try direct pair
    Optional<FxRate> rate = marketData.findValue(FxRateKey.of(baseCurrency, counterCurrency));
    if (rate.isPresent()) {
      return rate.get().fxRate(baseCurrency, counterCurrency);
    }
    // Try triangulation on base currency
    Currency triangularBaseCcy = baseCurrency.getTriangulationCurrency();
    Optional<FxRate> rateBase1 = marketData.findValue(FxRateKey.of(baseCurrency, triangularBaseCcy));
    Optional<FxRate> rateBase2 = marketData.findValue(FxRateKey.of(triangularBaseCcy, counterCurrency));
    if (rateBase1.isPresent() && rateBase2.isPresent()) {
      return rateBase1.get().crossRate(rateBase2.get()).fxRate(baseCurrency, counterCurrency);
    }
    // Try triangulation on counter currency
    Currency triangularCounterCcy = counterCurrency.getTriangulationCurrency();
    Optional<FxRate> rateCounter1 = marketData.findValue(FxRateKey.of(baseCurrency, triangularCounterCcy));
    Optional<FxRate> rateCounter2 = marketData.findValue(FxRateKey.of(triangularCounterCcy, counterCurrency));
    if (rateCounter1.isPresent() && rateCounter2.isPresent()) {
      return rateCounter1.get().crossRate(rateCounter2.get()).fxRate(baseCurrency, counterCurrency);
    }
    // Double triangulation
    if (rateBase1.isPresent() && rateCounter2.isPresent()) {
      Optional<FxRate> rateTriangular2 = marketData.findValue(FxRateKey.of(triangularBaseCcy, triangularCounterCcy));
      if (rateTriangular2.isPresent()) {
        return rateBase1.get().crossRate(rateTriangular2.get()).crossRate(rateCounter2.get())
            .fxRate(baseCurrency, counterCurrency);
      }
    }
    throw new IllegalArgumentException("No market data available for pair " + baseCurrency + "/" + counterCurrency);
  }