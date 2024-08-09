@Override
  public double fxRate(Currency baseCurrency, Currency counterCurrency) {
    if (baseCurrency.equals(counterCurrency)) {
      return 1;
    }
    // Try direct pair
    Optional<FxRate> rate = marketData.findValue(FxRateId.of(baseCurrency, counterCurrency, fxRatesSource));
    if (rate.isPresent()) {
      return rate.get().fxRate(baseCurrency, counterCurrency);
    }
    // try specified triangulation currency
    if (triangulationCurrency != null) {
      Optional<FxRate> rateBase1 = marketData.findValue(FxRateId.of(baseCurrency, triangulationCurrency, fxRatesSource));
      Optional<FxRate> rateBase2 = marketData.findValue(FxRateId.of(triangulationCurrency, counterCurrency, fxRatesSource));
      if (rateBase1.isPresent() && rateBase2.isPresent()) {
        return rateBase1.get().crossRate(rateBase2.get()).fxRate(baseCurrency, counterCurrency);
      }
    }
    // Try triangulation on base currency
    Currency triangularBaseCcy = baseCurrency.getTriangulationCurrency();
    Optional<FxRate> rateBase1 = marketData.findValue(FxRateId.of(baseCurrency, triangularBaseCcy, fxRatesSource));
    Optional<FxRate> rateBase2 = marketData.findValue(FxRateId.of(triangularBaseCcy, counterCurrency, fxRatesSource));
    if (rateBase1.isPresent() && rateBase2.isPresent()) {
      return rateBase1.get().crossRate(rateBase2.get()).fxRate(baseCurrency, counterCurrency);
    }
    // Try triangulation on counter currency
    Currency triangularCounterCcy = counterCurrency.getTriangulationCurrency();
    Optional<FxRate> rateCounter1 = marketData.findValue(FxRateId.of(baseCurrency, triangularCounterCcy, fxRatesSource));
    Optional<FxRate> rateCounter2 = marketData.findValue(FxRateId.of(triangularCounterCcy, counterCurrency, fxRatesSource));
    if (rateCounter1.isPresent() && rateCounter2.isPresent()) {
      return rateCounter1.get().crossRate(rateCounter2.get()).fxRate(baseCurrency, counterCurrency);
    }
    // Infer the triangulation from the data available
    Set<Currency> baseOthers = new HashSet<>();
    Set<Currency> counterOthers = new HashSet<>();
    for (MarketDataId<?> id : marketData.getIds()) {
      if (id instanceof FxRateId) {
        FxRateId fxId = (FxRateId) id;
        if (fxId.getPair().contains(baseCurrency)) {
          baseOthers.add(fxId.getPair().other(baseCurrency));
        }
        if (fxId.getPair().contains(counterCurrency)) {
          counterOthers.add(fxId.getPair().other(counterCurrency));
        }
      }
    }
    Set<Currency> intersection = Sets.intersection(baseOthers, counterOthers);
    if (intersection.contains(Currency.USD)) {
      FxRate rate1 = marketData.getValue(FxRateId.of(baseCurrency, Currency.USD, fxRatesSource));
      FxRate rate2 = marketData.getValue(FxRateId.of(Currency.USD, counterCurrency, fxRatesSource));
      return rate1.crossRate(rate2).fxRate(baseCurrency, counterCurrency);
    } else if (intersection.contains(Currency.EUR)) {
      FxRate rate1 = marketData.getValue(FxRateId.of(baseCurrency, Currency.EUR, fxRatesSource));
      FxRate rate2 = marketData.getValue(FxRateId.of(Currency.EUR, counterCurrency, fxRatesSource));
      return rate1.crossRate(rate2).fxRate(baseCurrency, counterCurrency);
    } else if (intersection.size() == 1) {
      Currency cross = intersection.iterator().next();
      FxRate rate1 = marketData.getValue(FxRateId.of(baseCurrency, cross, fxRatesSource));
      FxRate rate2 = marketData.getValue(FxRateId.of(cross, counterCurrency, fxRatesSource));
      return rate1.crossRate(rate2).fxRate(baseCurrency, counterCurrency);
    }
    // Double triangulation
    if (rateBase1.isPresent() && rateCounter2.isPresent()) {
      Optional<FxRate> rateTriangular2 =
          marketData.findValue(FxRateId.of(triangularBaseCcy, triangularCounterCcy, fxRatesSource));
      if (rateTriangular2.isPresent()) {
        return rateBase1.get().crossRate(rateTriangular2.get()).crossRate(rateCounter2.get())
            .fxRate(baseCurrency, counterCurrency);
      }
    }
    if (fxRatesSource.equals(ObservableSource.NONE)) {
      throw new MarketDataNotFoundException(Messages.format(
          "No FX rate market data for {}/{}", baseCurrency, counterCurrency));
    }
    throw new MarketDataNotFoundException(Messages.format(
        "No FX rate market data for {}/{} using source '{}'", baseCurrency, counterCurrency, fxRatesSource));
  }