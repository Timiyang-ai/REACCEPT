@Test public void forJavaName_fromLegacyEnumName() {
    // These would have been considered equal in OkHttp 3.3.1, but now aren't.
    assertNotEquals(
        forJavaName("TLS_RSA_EXPORT_WITH_RC4_40_MD5"),
        forJavaName("SSL_RSA_EXPORT_WITH_RC4_40_MD5"));

    // The SSL_ one of these would have been invalid in OkHttp 3.3.1; it now is valid and not equal.
    assertNotEquals(
        forJavaName("TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA"),
        forJavaName("SSL_DH_RSA_EXPORT_WITH_DES40_CBC_SHA"));

    // These would have not been valid in OkHttp 3.3.1, and now aren't equal.
    assertNotEquals(
        forJavaName("TLS_FAKE_NEW_CIPHER"),
        forJavaName("SSL_FAKE_NEW_CIPHER"));
  }