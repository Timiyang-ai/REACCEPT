  @Test
  public void test_resolve_Ibor() {
    Fra fra = Fra.builder()
        .buySell(BUY)
        .notional(NOTIONAL_1M)
        .startDate(date(2015, 6, 15))
        .endDate(date(2015, 9, 15))
        .paymentDate(AdjustableDate.of(date(2015, 6, 20), BDA_MOD_FOLLOW))
        .fixedRate(FIXED_RATE)
        .index(GBP_LIBOR_3M)
        .fixingDateOffset(MINUS_TWO_DAYS)
        .build();
    ResolvedFra test = fra.resolve(REF_DATA);
    assertThat(test.getCurrency()).isEqualTo(GBP);
    assertThat(test.getNotional()).isCloseTo(NOTIONAL_1M, offset(0d));
    assertThat(test.getStartDate()).isEqualTo(date(2015, 6, 15));
    assertThat(test.getEndDate()).isEqualTo(date(2015, 9, 15));
    assertThat(test.getPaymentDate()).isEqualTo(date(2015, 6, 22));
    assertThat(test.getFixedRate()).isCloseTo(FIXED_RATE, offset(0d));
    assertThat(test.getFloatingRate()).isEqualTo(IborRateComputation.of(GBP_LIBOR_3M, date(2015, 6, 11), REF_DATA));
    assertThat(test.getYearFraction()).isCloseTo(ACT_365F.yearFraction(date(2015, 6, 15), date(2015, 9, 15)), offset(0d));
    assertThat(test.getDiscounting()).isEqualTo(ISDA);
  }