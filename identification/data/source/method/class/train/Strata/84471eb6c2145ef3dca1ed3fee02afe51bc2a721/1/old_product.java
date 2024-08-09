public void coverage() {
    BusinessDayAdjustment bda = BusinessDayAdjustment.of(BusinessDayConventions.FOLLOWING, HolidayCalendars.SAT_SUN);
    PeriodicScheduleDefn defn = PeriodicScheduleDefn.of(
        AdjustableDate.of(date(2014, JUNE, 4), bda),
        AdjustableDate.of(date(2014, SEPTEMBER, 17), bda),
        P1M, 
        bda,
        SHORT_INITIAL,
        false);
    coverImmutableBean(defn);
  }