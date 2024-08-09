@Override
  public FxIndexRates fxIndexRates(FxIndex index) {
    LocalDateDoubleTimeSeries fixings = timeSeries(index);
    FxForwardRates fxForwardRates = fxForwardRates(index.getCurrencyPair());
    return DiscountFxIndexRates.of(index, fxForwardRates, fixings);
  }