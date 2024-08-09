public CashFlows cashFlows(FraProduct product, RatesProvider provider) {
    ExpandedFra fra = product.expand();
    LocalDate paymentDate = fra.getPaymentDate();
    double futureValue = futureValue(fra, provider);
    double df = provider.discountFactor(fra.getCurrency(), paymentDate);
    CashFlow cashFlow = CashFlow.ofFutureValue(paymentDate, fra.getCurrency(), futureValue, df);
    return CashFlows.of(cashFlow);
  }