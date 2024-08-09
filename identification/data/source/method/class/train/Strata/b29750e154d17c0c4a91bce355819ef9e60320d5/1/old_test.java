  @Test
  public void test_calculate() {
    assertThat(CurveNodeDate.of(DATE1).calculate(() -> DATE2, () -> DATE3)).isEqualTo(DATE1);
    assertThat(CurveNodeDate.END.calculate(() -> DATE2, () -> DATE3)).isEqualTo(DATE2);
    assertThat(CurveNodeDate.LAST_FIXING.calculate(() -> DATE2, () -> DATE3)).isEqualTo(DATE3);
  }