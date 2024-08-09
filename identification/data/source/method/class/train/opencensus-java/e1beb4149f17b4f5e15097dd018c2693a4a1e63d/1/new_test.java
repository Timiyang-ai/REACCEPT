  @Test
  public void getLowerLong() {
    assertThat(first.getLowerLong()).isEqualTo(0);
    assertThat(second.getLowerLong()).isEqualTo(-0xFF00000000000000L);
  }