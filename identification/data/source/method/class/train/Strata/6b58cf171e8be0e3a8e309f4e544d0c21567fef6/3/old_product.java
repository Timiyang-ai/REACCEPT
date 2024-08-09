@Override
  public SwaptionSensitivity withCurrency(Currency ccy) {
    return new SwaptionSensitivity(index, expiry, tenor, strike, forward, ccy, sensitivity);
  }