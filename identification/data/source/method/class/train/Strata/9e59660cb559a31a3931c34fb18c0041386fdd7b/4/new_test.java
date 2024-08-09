  @Test
  public void test_presentValue() {
    CurrencyAmount computedRec = PRICER.presentValue(SWAPTION_REC_LONG, RATE_PROVIDER, HW_PROVIDER);
    CurrencyAmount computedPay = PRICER.presentValue(SWAPTION_PAY_SHORT, RATE_PROVIDER, HW_PROVIDER);
    SwapPaymentEventPricer<SwapPaymentEvent> paymentEventPricer = SwapPaymentEventPricer.standard();
    ResolvedSwapLeg cashFlowEquiv = CashFlowEquivalentCalculator.cashFlowEquivalentSwap(RSWAP_REC, RATE_PROVIDER);
    LocalDate expiryDate = MATURITY.toLocalDate();
    int nPayments = cashFlowEquiv.getPaymentEvents().size();
    double[] alpha = new double[nPayments];
    double[] discountedCashFlow = new double[nPayments];
    for (int loopcf = 0; loopcf < nPayments; loopcf++) {
      SwapPaymentEvent payment = cashFlowEquiv.getPaymentEvents().get(loopcf);
      alpha[loopcf] = HW_PROVIDER.alpha(RATE_PROVIDER.getValuationDate(), expiryDate, expiryDate, payment.getPaymentDate());
      discountedCashFlow[loopcf] = paymentEventPricer.presentValue(payment, RATE_PROVIDER);
    }
    double omegaPay = -1d;
    double kappa = HW_PROVIDER.getModel().kappa(DoubleArray.copyOf(discountedCashFlow), DoubleArray.copyOf(alpha));
    double expectedRec = 0.0;
    double expectedPay = 0.0;
    for (int loopcf = 0; loopcf < nPayments; loopcf++) {
      expectedRec += discountedCashFlow[loopcf] * NORMAL.getCDF((kappa + alpha[loopcf]));
      expectedPay += discountedCashFlow[loopcf] * NORMAL.getCDF(omegaPay * (kappa + alpha[loopcf]));
    }
    assertThat(computedRec.getCurrency()).isEqualTo(EUR);
    assertThat(computedRec.getAmount()).isCloseTo(expectedRec, offset(NOTIONAL * TOL));
    assertThat(computedPay.getCurrency()).isEqualTo(EUR);
    assertThat(computedPay.getAmount()).isCloseTo(expectedPay, offset(NOTIONAL * TOL));
  }