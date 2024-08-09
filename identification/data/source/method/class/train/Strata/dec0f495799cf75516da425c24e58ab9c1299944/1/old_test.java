  @Test
  public void test_yearFraction_scheduleInfo() {
    ResolvedCapitalIndexedBond base = sut();
    CapitalIndexedBondPaymentPeriod period = base.getPeriodicPayments().get(0);
    AtomicBoolean eom = new AtomicBoolean(false);
    DayCount dc = new DayCount() {
      @Override
      public double yearFraction(LocalDate firstDate, LocalDate secondDate, ScheduleInfo scheduleInfo) {
        assertThat(scheduleInfo.getStartDate()).isEqualTo(base.getUnadjustedStartDate());
        assertThat(scheduleInfo.getEndDate()).isEqualTo(base.getUnadjustedEndDate());
        assertThat(scheduleInfo.getPeriodEndDate(firstDate)).isEqualTo(period.getUnadjustedEndDate());
        assertThat(scheduleInfo.getFrequency()).isEqualTo(base.getFrequency());
        assertThat(scheduleInfo.isEndOfMonthConvention()).isEqualTo(eom.get());
        return 0.5;
      }

      @Override
      public int days(LocalDate firstDate, LocalDate secondDate) {
        return 182;
      }

      @Override
      public String getName() {
        return "";
      }
    };
    ResolvedCapitalIndexedBond test = base.toBuilder().dayCount(dc).build();
    assertThat(test.yearFraction(period.getUnadjustedStartDate(), period.getUnadjustedEndDate())).isEqualTo(0.5);
    // test with EOM=true
    ResolvedCapitalIndexedBond test2 = test.toBuilder().rollConvention(RollConventions.EOM).build();
    eom.set(true);
    assertThat(test2.yearFraction(period.getUnadjustedStartDate(), period.getUnadjustedEndDate())).isEqualTo(0.5);
  }