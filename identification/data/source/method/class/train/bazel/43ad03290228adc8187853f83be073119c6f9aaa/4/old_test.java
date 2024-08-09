  @Test
  public void addNullableBoolean() throws Exception {
    String f1 = new Fingerprint().addNullableBoolean(null).hexDigestAndReset();
    assertThat(f1).isEqualTo(new Fingerprint().addNullableBoolean(null).hexDigestAndReset());
    assertThat(f1).isNotEqualTo(new Fingerprint().addNullableBoolean(false).hexDigestAndReset());
    assertThat(f1).isNotEqualTo(new Fingerprint().addNullableBoolean(true).hexDigestAndReset());
  }