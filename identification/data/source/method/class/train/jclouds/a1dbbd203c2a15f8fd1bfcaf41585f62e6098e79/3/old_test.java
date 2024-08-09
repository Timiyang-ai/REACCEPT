   @Test
   public void testinstallPrivateKey() throws IOException {
      AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
      options.installPrivateKey("-----BEGIN RSA PRIVATE KEY-----");
      assertEquals(options.getPrivateKey(), "-----BEGIN RSA PRIVATE KEY-----");
   }