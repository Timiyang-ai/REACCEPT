  @Test
  public void integrant_internal() {
    SwapIndex index = CAPLET.getIndex();
    LocalDate effectiveDate = CAPLET.getUnderlyingSwap().getStartDate();
    ResolvedSwap expanded = CAPLET.getUnderlyingSwap();
    double tenor = VOLATILITIES_SHIFT.tenor(effectiveDate, CAPLET.getUnderlyingSwap().getEndDate());
    double theta = VOLATILITIES_SHIFT.relativeTime(
        CAPLET.getFixingDate().atTime(index.getFixingTime()).atZone(index.getFixingZone()));
    double delta = index.getTemplate().getConvention().getFixedLeg()
        .getDayCount().relativeYearFraction(effectiveDate, PAYMENT);
    double s0 = PRICER_SWAP.parRate(COUPON.getUnderlyingSwap(), RATES_PROVIDER);
    CmsIntegrantProvider integrant = new CmsIntegrantProvider(CAPLET, expanded, STRIKE, tenor, theta,
        s0, -delta, VOLATILITIES_SHIFT, CUT_OFF_STRIKE, MU);
    // Integrant internal
    double h = integrant.h(STRIKE);
    double hExpected = Math.pow(1 + STRIKE, -delta);
    assertThat(h).isCloseTo(hExpected, offset(TOLERANCE_K_P));
    double g = integrant.g(STRIKE);
    double gExpected = (1.0 - 1.0 / Math.pow(1 + STRIKE, tenor)) / STRIKE;
    assertThat(g).isCloseTo(gExpected, offset(TOLERANCE_K_P));
    double kExpected = integrant.h(STRIKE) / integrant.g(STRIKE);
    double k = integrant.k(STRIKE);
    assertThat(k).isCloseTo(kExpected, offset(TOLERANCE_K_P));
    double shiftFd = 1.0E-5;
    double kP = integrant.h(STRIKE + shiftFd) / integrant.g(STRIKE + shiftFd);
    double kM = integrant.h(STRIKE - shiftFd) / integrant.g(STRIKE - shiftFd);
    double[] kpkpp = integrant.kpkpp(STRIKE);
    assertThat(kpkpp[0]).isCloseTo((kP - kM) / (2 * shiftFd), offset(TOLERANCE_K_P));
    assertThat(kpkpp[1]).isCloseTo((kP + kM - 2 * k) / (shiftFd * shiftFd), offset(TOLERANCE_K_PP));
  }