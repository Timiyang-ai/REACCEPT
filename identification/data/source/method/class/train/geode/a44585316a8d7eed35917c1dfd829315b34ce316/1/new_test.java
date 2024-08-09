  @Test
  public void isSerializable() {
    assertThat(GemFireSecurityException.class).isInstanceOf(Serializable.class);
  }