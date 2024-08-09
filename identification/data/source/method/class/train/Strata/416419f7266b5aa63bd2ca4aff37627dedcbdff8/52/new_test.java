  @Test
  public void test_presentValueFromCleanPrice() {
    double cleanPrice = 0.985;
    CurrencyAmount computed = TRADE_PRICER.presentValueFromCleanPrice(TRADE, PROVIDER, REF_DATA, cleanPrice);
    LocalDate standardSettlement = PRODUCT.getSettlementDateOffset().adjust(VAL_DATE, REF_DATA);
    double df = ZeroRateDiscountFactors.of(EUR, VAL_DATE, CURVE_REPO).discountFactor(standardSettlement);
    double accruedInterest = PRODUCT_PRICER.accruedInterest(PRODUCT, standardSettlement);
    double pvPayment = PRICER_NOMINAL
        .presentValue(UPFRONT_PAYMENT, ZeroRateDiscountFactors.of(EUR, VAL_DATE, CURVE_REPO)).getAmount();
    double expected = QUANTITY * (cleanPrice * df * NOTIONAL + accruedInterest * df) + pvPayment;
    assertThat(computed.getCurrency()).isEqualTo(EUR);
    assertThat(computed.getAmount()).isCloseTo(expected, offset(NOTIONAL * QUANTITY * TOL));
  }