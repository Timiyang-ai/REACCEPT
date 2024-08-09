   @Test
   public void testblockOnPort() {
      AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
      options.blockOnPort(22, 30);
      assertEquals(options.getPort(), 22);
      assertEquals(options.getSeconds(), 30);

   }