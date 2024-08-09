    @Test
    public void restoreSSLCertificateTest() throws ClientException {
        HttpClientConfig config = mock(HttpClientConfig.class);
        when(config.isIgnoreSSLCerts()).thenReturn(false);
        CompatibleUrlConnClient client = new CompatibleUrlConnClient(config);
        try {
            client.restoreSSLCertificate();
        } catch (IllegalStateException e) {
            Assert.assertEquals("use HttpClientConfig.setIgnoreSSLCerts(false) instead", e.getMessage());
        }

    }