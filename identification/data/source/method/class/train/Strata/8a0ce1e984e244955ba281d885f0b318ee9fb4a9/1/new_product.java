@Override
  public int compareKey(PointSensitivity other) {
    if (other instanceof SwaptionSabrSensitivity) {
      SwaptionSabrSensitivity otherSwpt = (SwaptionSabrSensitivity) other;
      return ComparisonChain.start()
          .compare(currency, otherSwpt.currency)
          .compare(expiry, otherSwpt.expiry)
          .compare(tenor, otherSwpt.tenor)
          .compare(sensitivityType, otherSwpt.sensitivityType)
          .compare(convention.toString(), otherSwpt.convention.toString())
          .result();
    }
    return getClass().getSimpleName().compareTo(other.getClass().getSimpleName());
  }