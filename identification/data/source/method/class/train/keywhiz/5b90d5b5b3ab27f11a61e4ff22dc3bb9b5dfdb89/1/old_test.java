  @Test public void expand_nullInfoSameAsEmpty() {
    Hkdf hkdf = Hkdf.usingDefaults();
    SecretKey key = hkdf.extract(null, HEX.decode("DEADBEE0"));
    byte[] keyNullInfo = hkdf.expand(key, null, 53);
    byte[] keyEmptyInfo = hkdf.expand(key, new byte[0], 53);
    assertThat(keyNullInfo).isEqualTo(keyEmptyInfo);
  }