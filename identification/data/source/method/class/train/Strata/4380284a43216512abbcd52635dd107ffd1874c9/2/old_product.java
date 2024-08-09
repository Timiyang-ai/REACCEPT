@Override
  public FxIndexRates fxIndexRates(FxIndex index) {
    DiscountFactors base = discountFactors(index.getCurrencyPair().getBase());
    DiscountFactors counter = discountFactors(index.getCurrencyPair().getCounter());
    return DiscountFxIndexRates.of(index, timeSeries(index), fxMatrix, base, counter);
  }