@Override
  public IborFutureOptionSensitivity multipliedBy(double factor) {
    return new IborFutureOptionSensitivity(
        volatilitiesName, expiry, fixingDate, strikePrice, futurePrice, currency, sensitivity * factor);
  }