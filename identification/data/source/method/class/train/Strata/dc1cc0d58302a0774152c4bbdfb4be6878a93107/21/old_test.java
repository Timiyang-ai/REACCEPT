  @Test
  public void test_presentValueSensitivity_ibor_noCompounding() {
    LocalDate valDate = PAYMENT_PERIOD_FLOATING.getPaymentDate().minusDays(90);
    double paymentTime = DAY_COUNT.relativeYearFraction(valDate, PAYMENT_PERIOD_FLOATING.getPaymentDate());
    DiscountFactors mockDf = mock(DiscountFactors.class);
    SimpleRatesProvider simpleProv = new SimpleRatesProvider(valDate, mockDf);
    simpleProv.setDayCount(DAY_COUNT);
    RateComputationFn<RateComputation> obsFunc = mock(RateComputationFn.class);

    when(mockDf.discountFactor(PAYMENT_PERIOD_FLOATING.getPaymentDate()))
        .thenReturn(DISCOUNT_FACTOR);
    ZeroRateSensitivity builder = ZeroRateSensitivity.of(
        PAYMENT_PERIOD_FLOATING.getCurrency(), paymentTime, -DISCOUNT_FACTOR * paymentTime);
    when(mockDf.zeroRatePointSensitivity(PAYMENT_PERIOD_FLOATING.getPaymentDate())).thenReturn(builder);

    DiscountingRatePaymentPeriodPricer pricer = new DiscountingRatePaymentPeriodPricer(obsFunc);
    LocalDate[] dates = new LocalDate[] {CPN_DATE_1, CPN_DATE_2, CPN_DATE_3, CPN_DATE_4};
    double[] rates = new double[] {RATE_1, RATE_2, RATE_3};
    for (int i = 0; i < 3; ++i) {
      IborRateComputation rateComputation =
          (IborRateComputation) PAYMENT_PERIOD_FLOATING.getAccrualPeriods().get(i).getRateComputation();
      IborRateSensitivity iborSense = IborRateSensitivity.of(rateComputation.getObservation(), 1d);
      when(obsFunc.rateSensitivity(rateComputation, dates[i], dates[i + 1], simpleProv)).thenReturn(iborSense);
      when(obsFunc.rate(rateComputation, dates[i], dates[i + 1], simpleProv)).thenReturn(rates[i]);
    }
    PointSensitivities senseComputed = pricer.presentValueSensitivity(PAYMENT_PERIOD_FLOATING, simpleProv).build();

    double eps = 1.e-7;
    List<IborRateSensitivity> senseExpectedList = futureFwdSensitivityFD(simpleProv, PAYMENT_PERIOD_FLOATING, obsFunc, eps);
    PointSensitivities senseExpected = PointSensitivities.of(senseExpectedList).multipliedBy(DISCOUNT_FACTOR);
    List<ZeroRateSensitivity> dscExpectedList = dscSensitivityFD(simpleProv, PAYMENT_PERIOD_FLOATING, obsFunc, eps);
    PointSensitivities senseExpectedDsc = PointSensitivities.of(dscExpectedList);

    assertThat(senseComputed.equalWithTolerance(
        senseExpected.combinedWith(senseExpectedDsc), eps * PAYMENT_PERIOD_FLOATING.getNotional())).isTrue();
  }