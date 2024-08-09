  @Test
  public void addBoolean() throws Exception {
    String f1 = new Fingerprint().addBoolean(true).hexDigestAndReset();
    String f2 = new Fingerprint().addBoolean(false).hexDigestAndReset();
    String f3 = new Fingerprint().addBoolean(true).hexDigestAndReset();

    assertThat(f1).isEqualTo(f3);
    assertThat(f1).isNotEqualTo(f2);
  }