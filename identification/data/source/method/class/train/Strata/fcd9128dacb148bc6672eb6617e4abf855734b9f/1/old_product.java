@Override
  public IborFutureOptionSensitivity multipliedBy(double factor) {
    return new IborFutureOptionSensitivity(
        index, expiry, fixingDate, strikePrice, futurePrice, currency, sensitivity * factor);
  }