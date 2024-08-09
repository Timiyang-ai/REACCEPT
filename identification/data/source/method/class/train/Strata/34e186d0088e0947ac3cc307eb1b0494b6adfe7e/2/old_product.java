public RateCalculationSwapLeg toLeg(
      LocalDate startDate,
      LocalDate endDate,
      PayReceive payReceive,
      double notional) {

    return toLeg(startDate, endDate, payReceive, notional, 0d);
  }