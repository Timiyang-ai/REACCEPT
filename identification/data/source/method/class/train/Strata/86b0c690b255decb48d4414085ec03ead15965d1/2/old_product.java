@Override
  public double rate(LocalDate fixingDate) {
    LocalDate publicationDate = index.calculatePublicationFromFixing(fixingDate);
    if (!publicationDate.isAfter(getValuationDate())) {
      return historicRate(fixingDate, publicationDate);
    }
    return forwardRate(fixingDate);
  }