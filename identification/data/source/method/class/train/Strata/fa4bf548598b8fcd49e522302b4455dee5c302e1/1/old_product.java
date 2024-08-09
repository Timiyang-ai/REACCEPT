public static TemporalAdjuster nextLeapDay() {
    return TemporalAdjusters.ofDateAdjuster(DateAdjusters::nextLeapDay);
  }