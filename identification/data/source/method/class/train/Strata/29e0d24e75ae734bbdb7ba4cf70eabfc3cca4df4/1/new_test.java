  @Test
  public void test_volatilities() {
    BlackFxOptionSmileVolatilitiesSpecification base = BlackFxOptionSmileVolatilitiesSpecification.builder()
        .name(VOL_NAME)
        .currencyPair(EUR_GBP)
        .dayCount(ACT_360)
        .nodes(NODES)
        .timeInterpolator(PCHIP)
        .strikeInterpolator(PCHIP)
        .build();
    LocalDate date = LocalDate.of(2017, 9, 25);
    ZonedDateTime dateTime = date.atStartOfDay().atZone(ZoneId.of("Europe/London"));
    DoubleArray parameters = DoubleArray.of(0.05, -0.05, 0.15, 0.25, 0.1, -0.1);
    BlackFxOptionSmileVolatilities computed = base.volatilities(dateTime, parameters, REF_DATA);
    LocalDate spotDate = SPOT_OFFSET.adjust(dateTime.toLocalDate(), REF_DATA);
    DaysAdjustment expOffset = DaysAdjustment.ofBusinessDays(-2, TA_LO);
    DoubleArray expiries = DoubleArray.of(
        ACT_360.relativeYearFraction(date, expOffset.adjust(BUS_ADJ.adjust(spotDate.plus(Tenor.TENOR_3M), REF_DATA), REF_DATA)),
        ACT_360.relativeYearFraction(date, expOffset.adjust(BUS_ADJ.adjust(spotDate.plus(Tenor.TENOR_1Y), REF_DATA), REF_DATA)));
    SmileDeltaTermStructure smiles = InterpolatedStrikeSmileDeltaTermStructure.of(
        expiries, DoubleArray.of(0.1), DoubleArray.of(0.25, 0.15), DoubleMatrix.ofUnsafe(new double[][] {{-0.1}, {-0.05}}),
        DoubleMatrix.ofUnsafe(new double[][] {{0.1}, {0.05}}), ACT_360, PCHIP, FLAT, FLAT, PCHIP, FLAT, FLAT);
    BlackFxOptionSmileVolatilities expected = BlackFxOptionSmileVolatilities.of(VOL_NAME, EUR_GBP, dateTime, smiles);
    assertThat(computed).isEqualTo(expected);
  }