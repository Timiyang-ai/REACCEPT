@Override
  public double rate(IborRateObservation observation) {
    if (!observation.getFixingDate().isAfter(getValuationDate())) {
      return historicRate(observation);
    }
    return rateIgnoringTimeSeries(observation);
  }