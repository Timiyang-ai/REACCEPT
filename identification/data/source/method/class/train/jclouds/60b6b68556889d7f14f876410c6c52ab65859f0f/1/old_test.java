   @Test
   public void testkeyPair() {
      EC2TemplateOptions options = new EC2TemplateOptions();
      options.keyPair("mykeypair");
      assertEquals(options.getKeyPair(), "mykeypair");
   }