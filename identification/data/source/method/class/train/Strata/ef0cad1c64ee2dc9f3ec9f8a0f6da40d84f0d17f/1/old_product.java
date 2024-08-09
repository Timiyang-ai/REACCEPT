@Override
  public String toString() {
    return representsWeeks() ?
        (period.getDays() / 7) + "W" :
        period.toString().substring(1);
  }