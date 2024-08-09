   @Test
   public void testauthorizePublicKey() throws IOException {
      AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
      options.authorizePublicKey("ssh-rsa");
      assertEquals(options.getPublicKey(), "ssh-rsa");
   }