public TemporalAdjuster adjustBy(int amount) {
    return TemporalAdjusters.ofDateAdjuster(date -> shift(date, amount));
  }