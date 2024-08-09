   @Test
   public void testinstallPrivateKey() throws IOException {
      TemplateOptions options = new TemplateOptions();
      options.installPrivateKey("-----BEGIN RSA PRIVATE KEY-----");
      assertEquals(options.toString(), "{privateKeyPresent=true}");
      assertEquals(options.getPrivateKey(), "-----BEGIN RSA PRIVATE KEY-----");
   }