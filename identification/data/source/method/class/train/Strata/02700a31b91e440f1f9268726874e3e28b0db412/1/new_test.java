  @Test
  /**
   * Tests the second order adjoint derivatives for the SABR Hagan volatility function.
   * Only the derivatives with respect to the forward and the strike are provided.
   */
  public void volatilityAdjoint2() {
    volatilityAdjoint2ForInstrument(CALL_ITM, 1.0E-6, 1.0E-2);
    volatilityAdjoint2ForInstrument(CALL_ATM, 1.0E-6, 1.0E+2); // ATM the second order derivative is poor.
    volatilityAdjoint2ForInstrument(CALL_OTM, 1.0E-6, 1.0E-2);
  }