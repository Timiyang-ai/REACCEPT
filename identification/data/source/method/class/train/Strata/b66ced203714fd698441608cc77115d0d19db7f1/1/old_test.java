  @Test
  public void test_parRateSensitivity() {
    PointSensitivities computedSpread = PRICER.parSpreadSensitivity(RTERM_DEPOSIT, IMM_PROV);
    PointSensitivities computedRate = PRICER.parRateSensitivity(RTERM_DEPOSIT, IMM_PROV);
    assertThat(computedSpread.equalWithTolerance(computedRate, NOTIONAL * EPS_FD)).isTrue();
  }