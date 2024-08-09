   @Test
   public void testnoKeyPair() {
      AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
      options.noKeyPair();
      assertEquals(options.getKeyPair(), null);
      assert !options.shouldAutomaticallyCreateKeyPair();
   }