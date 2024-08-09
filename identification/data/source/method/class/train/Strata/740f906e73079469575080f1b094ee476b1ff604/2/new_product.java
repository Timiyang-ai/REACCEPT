@Override
  public double rate(LocalDate fixingDate) {
    if (!fixingDate.isAfter(getValuationDate())) {
      return historicRate(fixingDate);
    }
    return rateIgnoringTimeSeries(fixingDate);
  }