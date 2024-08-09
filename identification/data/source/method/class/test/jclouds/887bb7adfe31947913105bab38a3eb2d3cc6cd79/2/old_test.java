   @Test
   public void testauthorizePublicKey() throws IOException {
      TemplateOptions options = new TemplateOptions();
      options.authorizePublicKey("ssh-rsa");
      assertEquals(options.toString(), "{publicKeyPresent=true}");
      assertEquals(options.getPublicKey(), "ssh-rsa");
   }