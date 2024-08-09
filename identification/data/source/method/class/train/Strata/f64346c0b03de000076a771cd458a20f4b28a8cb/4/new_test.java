  @Test
  public void test_resolve() {
    ResolvedBill resolved = US_BILL.resolve(REF_DATA);
    assertThat(resolved.getDayCount()).isEqualTo(DAY_COUNT);
    assertThat(resolved.getLegalEntityId()).isEqualTo(LEGAL_ENTITY);
    assertThat(resolved.getNotional()).isEqualTo(NOTIONAL.resolve(REF_DATA));
    assertThat(resolved.getSecurityId()).isEqualTo(SECURITY_ID);
    assertThat(resolved.getSettlementDateOffset()).isEqualTo(SETTLE);
    assertThat(resolved.getYieldConvention()).isEqualTo(YIELD_CONVENTION);
  }