@Override
  public double rate(
      RateComputation computation,
      LocalDate startDate,
      LocalDate endDate,
      RatesProvider provider) {

    // dispatch by runtime type
    if (computation instanceof FixedRateComputation) {
      // inline code (performance) avoiding need for FixedRateComputationFn implementation
      return ((FixedRateComputation) computation).getRate();
    } else if (computation instanceof IborRateComputation) {
      return iborRateComputationFn.rate(
          (IborRateComputation) computation, startDate, endDate, provider);
    } else if (computation instanceof IborInterpolatedRateComputation) {
      return iborInterpolatedRateComputationFn.rate(
          (IborInterpolatedRateComputation) computation, startDate, endDate, provider);
    } else if (computation instanceof IborAveragedRateComputation) {
      return iborAveragedRateComputationFn.rate(
          (IborAveragedRateComputation) computation, startDate, endDate, provider);
    } else if (computation instanceof OvernightAveragedRateComputation) {
      return overnightAveragedRateComputationFn.rate(
          (OvernightAveragedRateComputation) computation, startDate, endDate, provider);
    } else if (computation instanceof OvernightCompoundedRateComputation) {
      return overnightCompoundedRateComputationFn.rate(
          (OvernightCompoundedRateComputation) computation, startDate, endDate, provider);
    } else if (computation instanceof OvernightAveragedDailyRateComputation) {
      return overnightAveragedDailyRateComputationFn.rate(
          (OvernightAveragedDailyRateComputation) computation, startDate, endDate, provider);
    } else if (computation instanceof InflationMonthlyRateComputation) {
      return inflationMonthlyRateComputationFn.rate(
          (InflationMonthlyRateComputation) computation, startDate, endDate, provider);
    } else if (computation instanceof InflationInterpolatedRateComputation) {
      return inflationInterpolatedRateComputationFn.rate(
          (InflationInterpolatedRateComputation) computation, startDate, endDate, provider);
    } else if (computation instanceof InflationEndMonthRateComputation) {
      return inflationEndMonthRateComputationFn.rate(
          (InflationEndMonthRateComputation) computation, startDate, endDate, provider);
    } else if (computation instanceof InflationEndInterpolatedRateComputation) {
      return inflationEndInterpolatedRateComputationFn.rate(
          (InflationEndInterpolatedRateComputation) computation, startDate, endDate, provider);
    } else {
      throw new IllegalArgumentException("Unknown Rate type: " + computation.getClass().getSimpleName());
    }
  }