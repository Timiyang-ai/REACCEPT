   @Test
   public void testauthorizePublicKey() throws IOException {
      EC2TemplateOptions options = new EC2TemplateOptions();
      options.authorizePublicKey("ssh-rsa");
      assertEquals(options.getPublicKey(), "ssh-rsa");
   }