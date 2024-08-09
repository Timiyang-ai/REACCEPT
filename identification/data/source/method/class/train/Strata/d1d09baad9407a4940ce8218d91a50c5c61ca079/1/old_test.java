  @Test
  public void test_builder() {
    RateAccrualPeriod test = RateAccrualPeriod.builder()
        .startDate(DATE_2014_03_31)
        .endDate(DATE_2014_07_01)
        .unadjustedStartDate(DATE_2014_03_30)
        .unadjustedEndDate(DATE_2014_06_30)
        .yearFraction(0.25d)
        .rateComputation(GBP_LIBOR_3M_2014_03_28)
        .build();
    assertThat(test.getStartDate()).isEqualTo(DATE_2014_03_31);
    assertThat(test.getEndDate()).isEqualTo(DATE_2014_07_01);
    assertThat(test.getUnadjustedStartDate()).isEqualTo(DATE_2014_03_30);
    assertThat(test.getUnadjustedEndDate()).isEqualTo(DATE_2014_06_30);
    assertThat(test.getYearFraction()).isCloseTo(0.25d, offset(0d));
    assertThat(test.getRateComputation()).isEqualTo(GBP_LIBOR_3M_2014_03_28);
    assertThat(test.getGearing()).isCloseTo(1d, offset(0d));
    assertThat(test.getSpread()).isCloseTo(0d, offset(0d));
    assertThat(test.getNegativeRateMethod()).isEqualTo(ALLOW_NEGATIVE);
  }