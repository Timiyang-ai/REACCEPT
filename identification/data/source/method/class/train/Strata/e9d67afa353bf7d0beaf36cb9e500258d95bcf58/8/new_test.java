  @Test
  public void test_sensitivity_LegalEntityDiscountingProvider() {
    CurrencyParameterSensitivities computed = CALC.sensitivity(PARAMETER_SENSITIVITIES, PROVIDER);
    assertThat(computed.getSensitivities()).hasSize(4);
    DoubleArray expected11 = (DoubleArray) MATRIX_ALGEBRA.multiply(SENSI_1, DoubleMatrix.copyOf(MATRIX_11));
    DoubleArray expected12 = (DoubleArray) MATRIX_ALGEBRA.multiply(SENSI_1, DoubleMatrix.copyOf(MATRIX_12));
    DoubleArray expected21 = (DoubleArray) MATRIX_ALGEBRA.multiply(SENSI_2, DoubleMatrix.copyOf(MATRIX_21));
    DoubleArray expected22 = (DoubleArray) MATRIX_ALGEBRA.multiply(SENSI_2, DoubleMatrix.copyOf(MATRIX_22));
    assertThat(computed.getSensitivity(CURVE_NAME_1, USD).getSensitivity().equalWithTolerance(expected11, TOL)).isTrue();
    assertThat(computed.getSensitivity(CURVE_NAME_1, GBP).getSensitivity().equalWithTolerance(expected21, TOL)).isTrue();
    assertThat(computed.getSensitivity(CURVE_NAME_2, USD).getSensitivity().equalWithTolerance(expected12, TOL)).isTrue();
    assertThat(computed.getSensitivity(CURVE_NAME_2, GBP).getSensitivity().equalWithTolerance(expected22, TOL)).isTrue();
  }