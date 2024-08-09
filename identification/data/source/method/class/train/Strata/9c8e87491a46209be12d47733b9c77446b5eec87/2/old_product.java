@Override
  public FxResetNotionalExchange adjustPaymentDate(TemporalAdjuster adjuster) {
    LocalDate adjusted = paymentDate.with(adjuster);
    return adjusted.equals(paymentDate) ? this : toBuilder().paymentDate(adjusted).build();
  }