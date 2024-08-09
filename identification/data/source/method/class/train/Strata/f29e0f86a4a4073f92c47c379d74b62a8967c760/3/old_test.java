  @Test
  public void jumpToDefaultTest() {
    JumpToDefault computed = PRICER.jumpToDefault(PRODUCT_BEFORE, RATES_PROVIDER, VALUATION_DATE, REF_DATA);
    LocalDate stepinDate = PRODUCT_BEFORE.getStepinDateOffset().adjust(VALUATION_DATE, REF_DATA);
    double dirtyPv = PRICER.presentValue(PRODUCT_BEFORE, RATES_PROVIDER, VALUATION_DATE, PriceType.DIRTY, REF_DATA).getAmount();
    double accrued = PRODUCT_BEFORE.accruedYearFraction(stepinDate) * PRODUCT_BEFORE.getFixedRate() *
        PRODUCT_BEFORE.getBuySell().normalize(NOTIONAL);
    double protection = PRODUCT_BEFORE.getBuySell().normalize(NOTIONAL) * (1d - RECOVERY_RATES.getRecoveryRate());
    double expected = protection - accrued - dirtyPv;
    assertThat(computed.getCurrency()).isEqualTo(USD);
    assertThat(computed.getAmounts().size() == 1).isTrue();
    assertThat(computed.getAmounts().get(LEGAL_ENTITY)).isCloseTo(expected, offset(NOTIONAL * TOL));
  }