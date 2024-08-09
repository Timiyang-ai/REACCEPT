@Override
  public double fxRate(Currency baseCurrency, Currency counterCurrency) {
    if (baseCurrency.equals(counterCurrency)) {
      return 1d;
    }
    if (baseCurrency.equals(pair.getBase()) && counterCurrency.equals(pair.getCounter())) {
      return rate;
    }
    if (counterCurrency.equals(pair.getBase()) && baseCurrency.equals(pair.getCounter())) {
      return 1d / rate;
    }
    throw new IllegalArgumentException(Messages.format(
        "No FX rate found for {}/{}", baseCurrency, counterCurrency));
  }