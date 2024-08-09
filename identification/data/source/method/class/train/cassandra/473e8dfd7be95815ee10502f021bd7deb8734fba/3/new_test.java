    @Test
    public void getSslContext_OpenSSL() throws IOException
    {
        // only try this test if OpenSsl is available
        if (!OpenSsl.isAvailable())
        {
            logger.warn("OpenSSL not available in this application, so not testing the netty-openssl code paths");
            return;
        }

        EncryptionOptions options = addKeystoreOptions(encryptionOptions);
        SslContext sslContext = SSLFactory.getOrCreateSslContext(options, true, SSLFactory.SocketType.CLIENT, true);
        Assert.assertNotNull(sslContext);
        Assert.assertTrue(sslContext instanceof OpenSslContext);
    }