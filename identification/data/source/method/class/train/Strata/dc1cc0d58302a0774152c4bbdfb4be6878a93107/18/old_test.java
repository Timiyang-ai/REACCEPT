  @Test
  public void test_presentValueSensitivity_ISDA() {
    RateComputationFn<RateComputation> mockObs = mock(RateComputationFn.class);
    DiscountFactors mockDf = mock(DiscountFactors.class);
    SimpleRatesProvider simpleProv = new SimpleRatesProvider(VAL_DATE, mockDf);

    ResolvedFra fraExp = RFRA;
    double forwardRate = 0.05;
    double discountRate = 0.015;
    double paymentTime = 0.3;
    double discountFactor = Math.exp(-discountRate * paymentTime);
    LocalDate fixingDate = FRA.getStartDate();
    IborIndexObservation obs = IborIndexObservation.of(FRA.getIndex(), fixingDate, REF_DATA);
    PointSensitivityBuilder sens = IborRateSensitivity.of(obs, 1d);
    when(mockDf.discountFactor(fraExp.getPaymentDate()))
        .thenReturn(discountFactor);
    when(mockDf.zeroRatePointSensitivity(fraExp.getPaymentDate()))
        .thenReturn(ZeroRateSensitivity.of(fraExp.getCurrency(), paymentTime, -discountFactor * paymentTime));
    when(mockObs.rateSensitivity(fraExp.getFloatingRate(), fraExp.getStartDate(), fraExp.getEndDate(), simpleProv))
        .thenReturn(sens);
    when(mockObs.rate(fraExp.getFloatingRate(), FRA.getStartDate(), FRA.getEndDate(), simpleProv))
        .thenReturn(forwardRate);
    DiscountingFraProductPricer test = new DiscountingFraProductPricer(mockObs);
    PointSensitivities sensitivity = test.presentValueSensitivity(fraExp, simpleProv);
    double eps = 1.e-7;
    double fdDscSense = dscSensitivity(RFRA, forwardRate, discountFactor, paymentTime, eps);
    double fdSense = presentValueFwdSensitivity(RFRA, forwardRate, discountFactor, eps);

    ImmutableList<PointSensitivity> sensitivities = sensitivity.getSensitivities();
    assertThat(sensitivities).hasSize(2);
    IborRateSensitivity sensitivity0 = (IborRateSensitivity) sensitivities.get(0);
    assertThat(sensitivity0.getIndex()).isEqualTo(FRA.getIndex());
    assertThat(sensitivity0.getObservation().getFixingDate()).isEqualTo(fixingDate);
    assertThat(sensitivity0.getSensitivity()).isCloseTo(fdSense, offset(FRA.getNotional() * eps));
    ZeroRateSensitivity sensitivity1 = (ZeroRateSensitivity) sensitivities.get(1);
    assertThat(sensitivity1.getCurrency()).isEqualTo(FRA.getCurrency());
    assertThat(sensitivity1.getYearFraction()).isEqualTo(paymentTime);
    assertThat(sensitivity1.getSensitivity()).isCloseTo(fdDscSense, offset(FRA.getNotional() * eps));

    // test via FraTrade
    DiscountingFraTradePricer testTrade = new DiscountingFraTradePricer(test);
    assertThat(testTrade.presentValueSensitivity(RFRA_TRADE, simpleProv)).isEqualTo(test.presentValueSensitivity(fraExp, simpleProv));
  }