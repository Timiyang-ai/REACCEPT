@Override
  public double rate(IborIndexObservation observation) {
    if (!observation.getFixingDate().isAfter(getValuationDate())) {
      return historicRate(observation);
    }
    return rateIgnoringTimeSeries(observation);
  }