public CashFlows cashFlows(ResolvedFra fra, RatesProvider provider) {
    LocalDate paymentDate = fra.getPaymentDate();
    double forecastValue = forecastValue0(fra, provider);
    double df = provider.discountFactor(fra.getCurrency(), paymentDate);
    CashFlow cashFlow = CashFlow.ofForecastValue(paymentDate, fra.getCurrency(), forecastValue, df);
    return CashFlows.of(cashFlow);
  }