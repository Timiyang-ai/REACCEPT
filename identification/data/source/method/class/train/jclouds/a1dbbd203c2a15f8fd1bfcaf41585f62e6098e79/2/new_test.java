   @Test
   public void testkeyPair() {
      AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
      options.keyPair("mykeypair");
      assertEquals(options.getKeyPair(), "mykeypair");
   }