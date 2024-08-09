public CurrencyAmount accruedInterest(SwapLeg leg, RatesProvider provider) {
    ExpandedSwapLeg expanded = leg.expand();
    Optional<PaymentPeriod> period = expanded.findPaymentPeriod(provider.getValuationDate());
    if (period.isPresent()) {
      double accruedInterest = paymentPeriodPricer.accruedInterest(period.get(), provider);
      return CurrencyAmount.of(leg.getCurrency(), accruedInterest);
    }
    return CurrencyAmount.zero(leg.getCurrency());
  }