public CashFlows cashFlows(FraProduct product, RatesProvider provider) {
    ExpandedFra fra = product.expand();
    LocalDate paymentDate = fra.getPaymentDate();
    double forecastValue = forecastValue0(fra, provider);
    double df = provider.discountFactor(fra.getCurrency(), paymentDate);
    CashFlow cashFlow = CashFlow.ofForecastValue(paymentDate, fra.getCurrency(), forecastValue, df);
    return CashFlows.of(cashFlow);
  }