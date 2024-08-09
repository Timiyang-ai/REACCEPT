  @Test
  public void test_toString() {
    assertThat(TENOR_3D.toString()).isEqualTo("3D");
    assertThat(TENOR_2W.toString()).isEqualTo("2W");
    assertThat(TENOR_4M.toString()).isEqualTo("4M");
    assertThat(TENOR_12M.toString()).isEqualTo("12M");
    assertThat(TENOR_1Y.toString()).isEqualTo("1Y");
    assertThat(TENOR_18M.toString()).isEqualTo("18M");
    assertThat(TENOR_4Y.toString()).isEqualTo("4Y");
  }