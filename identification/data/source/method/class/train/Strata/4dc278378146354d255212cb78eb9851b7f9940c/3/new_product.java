Function<SchedulePeriod, FxReset> resolve(ReferenceData refData) {
    DateAdjuster fixingDateAdjuster = fixingDateOffset.toDateAdjuster(refData);
    return period -> buildFxReset(period, fixingDateAdjuster, refData);
  }