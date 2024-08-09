@Override
  public double accruedInterest(RatePaymentPeriod period, RatesProvider provider) {
    LocalDate valDate = provider.getValuationDate();
    if (valDate.compareTo(period.getStartDate()) <= 0 || valDate.compareTo(period.getEndDate()) > 0) {
      return 0d;
    }
    ImmutableList.Builder<RateAccrualPeriod> truncated = ImmutableList.builder();
    for (RateAccrualPeriod rap : period.getAccrualPeriods()) {
      if (valDate.compareTo(rap.getEndDate()) > 0) {
        truncated.add(rap);
      } else {
        truncated.add(rap.toBuilder()
            .endDate(provider.getValuationDate())
            .unadjustedEndDate(provider.getValuationDate())
            .yearFraction(period.getDayCount().yearFraction(rap.getStartDate(), provider.getValuationDate()))
            .build());
        break;
      }
    }
    RatePaymentPeriod adjustedPaymentPeriod = period.toBuilder().accrualPeriods(truncated.build()).build();
    return futureValue(adjustedPaymentPeriod, provider);
  }