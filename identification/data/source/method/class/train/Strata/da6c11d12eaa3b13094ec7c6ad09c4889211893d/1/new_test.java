  @Test
  public void test_toString() {
    assertThat(ShiftType.ABSOLUTE.toString()).isEqualTo("Absolute");
    assertThat(ShiftType.RELATIVE.toString()).isEqualTo("Relative");
    assertThat(ShiftType.SCALED.toString()).isEqualTo("Scaled");
  }