@Override
  public IborRateSensitivity multipliedBy(double factor) {
    return new IborRateSensitivity(index, currency, fixingDate, sensitivity * factor);
  }