@Override
  public IborRateSensitivity multipliedBy(double factor) {
    return new IborRateSensitivity(observation, currency, sensitivity * factor);
  }