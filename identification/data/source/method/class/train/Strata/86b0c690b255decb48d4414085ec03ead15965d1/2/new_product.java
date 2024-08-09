@Override
  public double rate(OvernightIndexObservation observation) {
    if (!observation.getPublicationDate().isAfter(getValuationDate())) {
      return historicRate(observation);
    }
    return forwardRate(observation);
  }