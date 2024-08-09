@Override
  public FxIndexRates fxIndexRates(FxIndex index) {
    LocalDateDoubleTimeSeries fixings = timeSeries(index);
    FxForwardRates fxForwardRates = fxForwardRates(index.getCurrencyPair());
    return ForwardFxIndexRates.of(index, fxForwardRates, fixings);
  }