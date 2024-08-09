  @Test
  public void jumpToDefaultTest() {
    JumpToDefault computed = PRICER.jumpToDefault(PRODUCT, RATES_PROVIDER, SETTLEMENT_STD, REF_DATA);
    LocalDate stepinDate = PRODUCT.getStepinDateOffset().adjust(VALUATION_DATE, REF_DATA);
    double dirtyPvMod =
        PRICER.presentValue(PRODUCT, RATES_PROVIDER, SETTLEMENT_STD, PriceType.DIRTY, REF_DATA).getAmount() / INDEX_FACTOR;
    double accrued = PRODUCT.accruedYearFraction(stepinDate) * PRODUCT.getFixedRate() *
        PRODUCT.getBuySell().normalize(NOTIONAL);
    double protection = PRODUCT.getBuySell().normalize(NOTIONAL) * (1d - RECOVERY_RATE);
    double expected = (protection - accrued - dirtyPvMod) / ((double) LEGAL_ENTITIES.size());
    assertThat(computed.getCurrency()).isEqualTo(USD);
    assertThat(computed.getAmounts().size() == 1).isTrue();
    assertThat(computed.getAmounts().get(INDEX_ID)).isCloseTo(expected, offset(NOTIONAL * TOL));

  }