@Override
  public IborFutureOptionSensitivity multipliedBy(double factor) {
    return new IborFutureOptionSensitivity(
        index, expiryDate, fixingDate, strikePrice, futurePrice, currency, sensitivity * factor);
  }