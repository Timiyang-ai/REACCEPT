  @Test
  public void test_parSpread_noFixing() {
    double parSpread = PRICER.parSpread(RDEPOSIT, IMM_PROV_NOFIX);
    IborFixingDeposit deposit0 = DEPOSIT.toBuilder().fixedRate(RATE + parSpread).build();
    CurrencyAmount pv0 = PRICER.presentValue(deposit0.resolve(REF_DATA), IMM_PROV_NOFIX);
    assertThat(pv0.getAmount()).isCloseTo(0, offset(TOLERANCE_RATE));
    double parSpread2 = PRICER.parSpread(RDEPOSIT, IMM_PROV_NOFIX);
    assertThat(parSpread).isCloseTo(parSpread2, offset(TOLERANCE_RATE));
  }