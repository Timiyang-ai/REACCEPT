  @Test
  public void test_findNotional() {
    ResolvedSwapLeg test = ResolvedSwapLeg.builder()
        .type(IBOR)
        .payReceive(RECEIVE)
        .paymentPeriods(RPP1, RPP2)
        .build();
    // Date is before the start date
    assertThat(test.findNotional(RPP1.getStartDate().minusMonths(1))).isEqualTo(Optional.of(RPP1.getNotionalAmount()));
    // Date is on the start date
    assertThat(test.findNotional(RPP1.getStartDate())).isEqualTo(Optional.of(RPP1.getNotionalAmount()));
    // Date is after the start date
    assertThat(test.findNotional(RPP1.getStartDate().plusDays(1))).isEqualTo(Optional.of(RPP1.getNotionalAmount()));
    // Date is before the end date
    assertThat(test.findNotional(RPP2.getEndDate().minusDays(1))).isEqualTo(Optional.of(RPP2.getNotionalAmount()));
    // Date is on the end date
    assertThat(test.findNotional(RPP2.getEndDate())).isEqualTo(Optional.of(RPP2.getNotionalAmount()));
    // Date is after the end date
    assertThat(test.findNotional(RPP2.getEndDate().plusMonths(1))).isEqualTo(Optional.of(RPP2.getNotionalAmount()));
  }