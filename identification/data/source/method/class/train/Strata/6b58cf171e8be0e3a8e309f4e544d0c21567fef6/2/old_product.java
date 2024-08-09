@Override
  public SwaptionSensitivity withCurrency(Currency ccy) {
    return new SwaptionSensitivity(convention, expiry, tenor, strike, forward, ccy, sensitivity);
  }