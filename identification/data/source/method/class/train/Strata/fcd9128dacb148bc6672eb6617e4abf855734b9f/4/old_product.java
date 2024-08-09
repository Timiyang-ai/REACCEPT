@Override
  public IborFutureOptionSensitivity multipliedBy(double factor) {
    return new IborFutureOptionSensitivity(
        index, expiration, fixingDate, strikePrice, futurePrice, currency, sensitivity * factor);
  }