  @Test
  public void asType() {
    assertThat(MoreElements.asType(stringElement)).isEqualTo(stringElement);
  }