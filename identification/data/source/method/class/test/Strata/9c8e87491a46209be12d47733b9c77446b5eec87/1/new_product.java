@Override
  public NotionalExchange adjustPaymentDate(TemporalAdjuster adjuster) {
    LocalDate adjusted = payment.getDate().with(adjuster);
    return of(payment.getValue(), adjusted);
  }