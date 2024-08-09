BiFunction<Integer, SchedulePeriod, Optional<FxReset>> resolve(ReferenceData refData) {
    DateAdjuster fixingDateAdjuster = fixingDateOffset.resolve(refData);
    Function<LocalDate, FxIndexObservation> obsFn = index.resolve(refData);
    return (periodIndex, period) -> buildFxReset(periodIndex, period, fixingDateAdjuster, obsFn);
  }