@Override
  public FxIndexRates fxIndexRates(FxIndex index) {
    LocalDateDoubleTimeSeries timeSeries = timeSeries(index);
    FxForwardRates fxForwardRates = fxForwardRates(index.getCurrencyPair());
    return DiscountFxIndexRates.of(index, timeSeries, fxForwardRates);
  }