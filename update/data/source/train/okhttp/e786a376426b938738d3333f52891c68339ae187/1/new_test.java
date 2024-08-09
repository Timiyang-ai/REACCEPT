@Test public void forJavaName_fromLegacyEnumName() {
    // These would have been considered equal in OkHttp 3.3.1, but now aren't.
    assertEquals(
        forJavaName("TLS_RSA_EXPORT_WITH_RC4_40_MD5"),
        forJavaName("SSL_RSA_EXPORT_WITH_RC4_40_MD5"));
    assertEquals(
        forJavaName("TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA"),
        forJavaName("SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA"));
    assertEquals(
        forJavaName("TLS_FAKE_NEW_CIPHER"),
        forJavaName("SSL_FAKE_NEW_CIPHER"));
  }