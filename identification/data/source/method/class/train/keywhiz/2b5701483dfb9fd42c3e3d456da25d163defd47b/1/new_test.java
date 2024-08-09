  @Test public void extract_nullSaltSameAsZeros() {
    byte[] ikm = HEX.decode("DEADBEEF");
    Hkdf hkdf = Hkdf.usingDefaults();
    byte[] prkFromNullSalt = hkdf.extract(null, ikm).getEncoded();

    SecretKeySpec zeroSalt = new SecretKeySpec(
        HEX.decode(Strings.repeat("00", Hash.SHA256.getByteLength())),
        Hash.SHA256.getAlgorithm());
    byte[] prkFromZeroSalt = hkdf.extract(zeroSalt, ikm).getEncoded();

    assertThat(prkFromNullSalt).isEqualTo(prkFromZeroSalt);
  }