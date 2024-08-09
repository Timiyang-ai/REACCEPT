@Override
  public ResolvedBulletPayment resolve(ReferenceData refData) {
    CurrencyAmount signed = payReceive == PayReceive.PAY ? value.negated() : value;
    Payment payment = Payment.of(signed, date.adjusted());
    return ResolvedBulletPayment.of(payment);
  }