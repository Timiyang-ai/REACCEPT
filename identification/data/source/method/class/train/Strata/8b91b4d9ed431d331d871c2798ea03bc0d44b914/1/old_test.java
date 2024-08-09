  @Test
  public void test_rate_FixedRateComputation() {
    FixedRateComputation ro = FixedRateComputation.of(0.0123d);
    DispatchingRateComputationFn test = DispatchingRateComputationFn.DEFAULT;
    assertThat(test.rate(ro, ACCRUAL_START_DATE, ACCRUAL_END_DATE, MOCK_PROV)).isCloseTo(0.0123d, offset(0d));
  }