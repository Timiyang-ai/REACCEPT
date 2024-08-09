@Override
  public NotionalExchange adjustPaymentDate(TemporalAdjuster adjuster) {
    LocalDate adjusted = paymentDate.with(adjuster);
    return adjusted.equals(paymentDate) ? this : toBuilder().paymentDate(adjusted).build();
  }