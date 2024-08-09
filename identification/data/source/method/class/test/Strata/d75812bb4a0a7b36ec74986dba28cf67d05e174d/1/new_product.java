public Tenor normalized() {
    if (period.getDays() == 0 && period.toTotalMonths() == 12) {
      return TENOR_12M;
    }
    Period norm = period.normalized();
    return (norm != period ? Tenor.of(norm) : this);
  }