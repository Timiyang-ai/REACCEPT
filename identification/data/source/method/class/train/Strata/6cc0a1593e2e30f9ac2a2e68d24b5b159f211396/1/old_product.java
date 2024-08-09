@Override
  public PointSensitivities convertedTo(Currency resultCurrency, FxRateProvider rateProvider) {
    List<PointSensitivity> mutable = new ArrayList<>();
    for (PointSensitivity sensi : sensitivities) {
      insert(mutable, sensi.convertedTo(resultCurrency, rateProvider));
    }
    return new PointSensitivities(ImmutableList.copyOf(mutable));
  }