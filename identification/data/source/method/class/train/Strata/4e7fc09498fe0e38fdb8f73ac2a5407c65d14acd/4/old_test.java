  @Test
  public void test_cashFlows_ISDA() {
    ResolvedFra fraExp = RFRA;
    SimpleRatesProvider prov = createProvider(fraExp);

    double fixedRate = FRA.getFixedRate();
    double yearFraction = fraExp.getYearFraction();
    double notional = fraExp.getNotional();
    double expected = notional * yearFraction * (FORWARD_RATE - fixedRate) / (1.0 + yearFraction * FORWARD_RATE);

    DiscountingFraProductPricer test = DiscountingFraProductPricer.DEFAULT;
    CashFlows computed = test.cashFlows(fraExp, prov);
    assertThat(computed.getCashFlows()).hasSize(1);
    assertThat(computed.getCashFlows().get(0).getPaymentDate()).isEqualTo(fraExp.getPaymentDate());
    assertThat(computed.getCashFlows().get(0).getForecastValue().getCurrency()).isEqualTo(fraExp.getCurrency());
    assertThat(computed.getCashFlows().get(0).getForecastValue().getAmount()).isCloseTo(expected, offset(TOLERANCE));

    // test via FraTrade
    DiscountingFraTradePricer testTrade = new DiscountingFraTradePricer(test);
    assertThat(testTrade.cashFlows(RFRA_TRADE, prov)).isEqualTo(test.cashFlows(fraExp, prov));
  }