  @Test
  public void test_findPaymentPeriod() {
    ResolvedSwapLeg test = ResolvedSwapLeg.builder()
        .type(IBOR)
        .payReceive(RECEIVE)
        .paymentPeriods(RPP1, RPP2)
        .build();
    assertThat(test.findPaymentPeriod(RPP1.getStartDate())).isEqualTo(Optional.empty());
    assertThat(test.findPaymentPeriod(RPP1.getStartDate().plusDays(1))).isEqualTo(Optional.of(RPP1));
    assertThat(test.findPaymentPeriod(RPP1.getEndDate())).isEqualTo(Optional.of(RPP1));
    assertThat(test.findPaymentPeriod(RPP2.getStartDate())).isEqualTo(Optional.of(RPP1));
    assertThat(test.findPaymentPeriod(RPP2.getStartDate().plusDays(1))).isEqualTo(Optional.of(RPP2));
    assertThat(test.findPaymentPeriod(RPP2.getEndDate())).isEqualTo(Optional.of(RPP2));
    assertThat(test.findPaymentPeriod(RPP2.getEndDate().plusDays(1))).isEqualTo(Optional.empty());
  }