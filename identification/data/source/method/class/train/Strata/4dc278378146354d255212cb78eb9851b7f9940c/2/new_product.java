Function<SchedulePeriod, FxReset> resolve(ReferenceData refData) {
    DateAdjuster fixingDateAdjuster = fixingDateOffset.resolve(refData);
    return period -> buildFxReset(period, fixingDateAdjuster, refData);
  }