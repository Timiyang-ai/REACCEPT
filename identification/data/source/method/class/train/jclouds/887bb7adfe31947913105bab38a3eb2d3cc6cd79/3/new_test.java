   @Test
   public void testinstallPrivateKey() throws IOException {
      EC2TemplateOptions options = new EC2TemplateOptions();
      options.installPrivateKey("-----BEGIN RSA PRIVATE KEY-----");
      assertEquals(options.getPrivateKey(), "-----BEGIN RSA PRIVATE KEY-----");
   }