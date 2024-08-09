Function<SchedulePeriod, FxReset> resolve(ReferenceData refData) {
    DateAdjuster fixingDateAdjuster = fixingDateOffset.resolve(refData);
    Function<LocalDate, FxIndexObservation> obsFn = index.resolve(refData);
    return period -> buildFxReset(period, fixingDateAdjuster, obsFn);
  }