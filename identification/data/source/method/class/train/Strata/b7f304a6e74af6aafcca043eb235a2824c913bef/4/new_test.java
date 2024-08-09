  @Test
  public void test_parRate() {
    double parRate = PRICER.parRate(RDEPOSIT, IMM_PROV_NOFIX);
    IborFixingDeposit deposit0 = DEPOSIT.toBuilder().fixedRate(parRate).build();
    CurrencyAmount pv0 = PRICER.presentValue(deposit0.resolve(REF_DATA), IMM_PROV_NOFIX);
    assertThat(pv0.getAmount()).isCloseTo(0, offset(TOLERANCE_RATE));
    double parRate2 = PRICER.parRate(RDEPOSIT, IMM_PROV_NOFIX);
    assertThat(parRate).isCloseTo(parRate2, offset(TOLERANCE_RATE));
  }