@Override
  public FxIndexRates fxIndexRates(FxIndex index) {
    FxForwardRates fxForwardRates = fxForwardRates(index.getCurrencyPair());
    return DiscountFxIndexRates.of(index, fxForwardRates, timeSeries(index));
  }