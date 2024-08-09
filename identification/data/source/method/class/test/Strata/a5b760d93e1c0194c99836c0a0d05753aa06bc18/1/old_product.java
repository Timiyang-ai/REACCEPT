public CurrencyAmount accruedInterest(ResolvedSwapLeg leg, RatesProvider provider) {
    Optional<PaymentPeriod> period = leg.findPaymentPeriod(provider.getValuationDate());
    if (period.isPresent()) {
      double accruedInterest = paymentPeriodPricer.accruedInterest(period.get(), provider);
      return CurrencyAmount.of(leg.getCurrency(), accruedInterest);
    }
    return CurrencyAmount.zero(leg.getCurrency());
  }