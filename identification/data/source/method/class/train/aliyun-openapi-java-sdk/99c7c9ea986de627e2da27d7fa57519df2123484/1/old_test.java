    @Test
    public void ignoreSSLCertificateTest() throws ClientException {
        HttpClientConfig config = mock(HttpClientConfig.class);
        when(config.isIgnoreSSLCerts()).thenReturn(true);
        CompatibleUrlConnClient client = new CompatibleUrlConnClient(config);
        try {
            client.ignoreSSLCertificate();
        } catch (IllegalStateException e) {
            Assert.assertEquals("use HttpClientConfig.setIgnoreSSLCerts(true) instead", e.getMessage());
        }
    }