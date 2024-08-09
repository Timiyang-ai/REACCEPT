@Override
  public IborRateSensitivity multipliedBy(double factor) {
    return new IborRateSensitivity(index, fixingDate, currency, sensitivity * factor);
  }