  @Test
  public void priceAdjoint() {
    // Price
    double price = BlackFormulaRepository.price(F, F - DELTA_F, T, SIGMA, true);
    ValueDerivatives priceAdjoint = BlackFormulaRepository.priceAdjoint(F, F - DELTA_F, T, SIGMA, true);
    assertThat(price).isCloseTo(priceAdjoint.getValue(), offset(TOLERANCE_PRICE2));
    // Price with 0 volatility
    double price0 = BlackFormulaRepository.price(F, F - DELTA_F, T, 0.0d, true);
    ValueDerivatives price0Adjoint = BlackFormulaRepository.priceAdjoint(F, F - DELTA_F, T, 0.0d, true);
    assertThat(price0).isCloseTo(price0Adjoint.getValue(), offset(TOLERANCE_PRICE2));
    // Derivative forward.
    double deltaF = 0.01;
    double priceFP = BlackFormulaRepository.price(F + deltaF, F - DELTA_F, T, SIGMA, true);
    double priceFM = BlackFormulaRepository.price(F - deltaF, F - DELTA_F, T, SIGMA, true);
    double derivativeFxFD = (priceFP - priceFM) / (2 * deltaF);
    assertThat(derivativeFxFD).isCloseTo(priceAdjoint.getDerivative(0), offset(TOLERANCE_PRICE_DELTA));
    // Derivative strike.
    double deltaK = 0.01;
    double priceKP = BlackFormulaRepository.price(F, F - DELTA_F + deltaK, T, SIGMA, true);
    double priceKM = BlackFormulaRepository.price(F, F - DELTA_F - deltaK, T, SIGMA, true);
    double derivativeKxFD = (priceKP - priceKM) / (2 * deltaK);
    assertThat(derivativeKxFD).isCloseTo(priceAdjoint.getDerivative(1), offset(TOLERANCE_PRICE_DELTA));
    // Derivative time.
    double deltaT = 1.0 / 365.0;
    double priceTP = BlackFormulaRepository.price(F, F - DELTA_F, T + deltaT, SIGMA, true);
    double priceTM = BlackFormulaRepository.price(F, F - DELTA_F, T - deltaT, SIGMA, true);
    double derivativeTxFD = (priceTP - priceTM) / (2 * deltaT);
    assertThat(derivativeTxFD).isCloseTo(priceAdjoint.getDerivative(2), offset(TOLERANCE_PRICE_DELTA));
    // Derivative volatility.
    double deltaV = 0.0001;
    double priceVP = BlackFormulaRepository.price(F, F - DELTA_F, T, SIGMA + deltaV, true);
    double priceVM = BlackFormulaRepository.price(F, F - DELTA_F, T, SIGMA - deltaV, true);
    double derivativeVxFD = (priceVP - priceVM) / (2 * deltaV);
    assertThat(derivativeVxFD).isCloseTo(priceAdjoint.getDerivative(3), offset(TOLERANCE_PRICE_DELTA));
  }