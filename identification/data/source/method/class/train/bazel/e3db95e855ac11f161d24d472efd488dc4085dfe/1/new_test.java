  @Test
  public void addNullableString() throws Exception {
    String f1 = new Fingerprint().addNullableString(null).hexDigestAndReset();
    assertThat(f1).isEqualTo(new Fingerprint().addNullableString(null).hexDigestAndReset());
    assertThat(f1).isNotEqualTo(new Fingerprint().addNullableString("").hexDigestAndReset());
  }