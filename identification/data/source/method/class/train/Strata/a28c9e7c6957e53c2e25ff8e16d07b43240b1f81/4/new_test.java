  @Test
  public void test_createSchedule_sameFrequency() {
    PaymentSchedule test = PaymentSchedule.builder()
        .paymentFrequency(P1M)
        .paymentDateOffset(DaysAdjustment.ofBusinessDays(2, GBLO))
        .build();
    Schedule schedule = test.createSchedule(ACCRUAL_SCHEDULE, REF_DATA);
    assertThat(schedule).isEqualTo(ACCRUAL_SCHEDULE);
  }