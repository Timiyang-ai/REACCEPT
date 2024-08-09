  @Test
  public void test_volatilities() {
    BlackFxOptionInterpolatedNodalSurfaceVolatilitiesSpecification base =
        BlackFxOptionInterpolatedNodalSurfaceVolatilitiesSpecification.builder()
            .name(VOL_NAME)
            .currencyPair(GBP_USD)
            .dayCount(ACT_365F)
            .nodes(NODES)
            .timeInterpolator(PCHIP)
            .strikeInterpolator(DOUBLE_QUADRATIC)
            .build();
    LocalDate date = LocalDate.of(2017, 9, 25);
    ZonedDateTime dateTime = date.atStartOfDay().atZone(ZoneId.of("Europe/London"));
    DoubleArray parameters = DoubleArray.of(0.19, 0.15, 0.13, 0.14, 0.14, 0.11, 0.09, 0.09, 0.11, 0.09, 0.07, 0.07);
    BlackFxOptionSurfaceVolatilities computed = base.volatilities(dateTime, parameters, REF_DATA);
    DaysAdjustment expOffset = DaysAdjustment.ofBusinessDays(-2, NY_LO);
    double[] expiries = new double[STRIKES.size() * TENORS.size()];
    double[] strikes = new double[STRIKES.size() * TENORS.size()];
    ImmutableList.Builder<ParameterMetadata> paramMetadata = ImmutableList.builder();
    for (int i = 0; i < TENORS.size(); ++i) {
      double expiry = ACT_365F.relativeYearFraction(
          date, expOffset.adjust(BDA.adjust(SPOT_OFFSET.adjust(date, REF_DATA).plus(TENORS.get(i)), REF_DATA), REF_DATA));
      for (int j = 0; j < STRIKES.size(); ++j) {
        paramMetadata.add(FxVolatilitySurfaceYearFractionParameterMetadata.of(expiry, SimpleStrike.of(STRIKES.get(j)), GBP_USD));
        expiries[STRIKES.size() * i + j] = expiry;
        strikes[STRIKES.size() * i + j] = STRIKES.get(j);
      }
    }
    InterpolatedNodalSurface surface = InterpolatedNodalSurface.ofUnsorted(
        Surfaces.blackVolatilityByExpiryStrike(VOL_NAME.getName(), ACT_365F).withParameterMetadata(paramMetadata.build()),
        DoubleArray.ofUnsafe(expiries), DoubleArray.ofUnsafe(strikes), parameters,
        GridSurfaceInterpolator.of(PCHIP, DOUBLE_QUADRATIC));
    BlackFxOptionSurfaceVolatilities expected = BlackFxOptionSurfaceVolatilities.of(VOL_NAME, GBP_USD, dateTime, surface);
    assertThat(computed).isEqualTo(expected);
  }