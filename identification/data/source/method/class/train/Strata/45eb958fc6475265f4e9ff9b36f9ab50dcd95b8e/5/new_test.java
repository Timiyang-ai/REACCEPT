  @Test
  public void kappa() {
    double[] cashFlowAmount = new double[] {-1.0, 0.05, 0.05, 0.05, 0.05, 1.05 };
    double notional = 100000000; // 100m
    double[] cashFlowTime = new double[] {10.0, 11.0, 12.0, 13.0, 14.00, 15.00 };
    double expiryTime = cashFlowTime[0] - 2.0 / 365.0;
    int nbCF = cashFlowAmount.length;
    double[] discountedCashFlow = new double[nbCF];
    double[] alpha = new double[nbCF];
    double rate = 0.04;
    for (int loopcf = 0; loopcf < nbCF; loopcf++) {
      discountedCashFlow[loopcf] = cashFlowAmount[loopcf] * Math.exp(-rate * cashFlowTime[loopcf]) * notional;
      alpha[loopcf] = MODEL.alpha(MODEL_PARAMETERS, 0.0, expiryTime, expiryTime, cashFlowTime[loopcf]);
    }
    double kappa = MODEL.kappa(DoubleArray.copyOf(discountedCashFlow), DoubleArray.copyOf(alpha));
    double swapValue = 0.0;
    for (int loopcf = 0; loopcf < nbCF; loopcf++) {
      swapValue += discountedCashFlow[loopcf] * Math.exp(-Math.pow(alpha[loopcf], 2.0) / 2.0 - alpha[loopcf] * kappa);
    }
    assertThat(0.0).isCloseTo(swapValue, offset(1.0E-1));
  }