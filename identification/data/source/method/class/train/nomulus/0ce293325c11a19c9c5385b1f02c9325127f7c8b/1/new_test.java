  @Test
  public void test_validateCertificate_canBeConfiguredToBypassCertHashes() throws Exception {
    TlsCredentials tls = new TlsCredentials(false, "certHash", Optional.of("192.168.1.1"));
    persistResource(
        loadRegistrar("TheRegistrar")
            .asBuilder()
            .setClientCertificate(null, DateTime.now(UTC))
            .setFailoverClientCertificate(null, DateTime.now(UTC))
            .build());
    // This would throw a RegistrarCertificateNotConfiguredException if cert hashes wren't bypassed.
    tls.validateCertificate(Registrar.loadByClientId("TheRegistrar").get());
  }