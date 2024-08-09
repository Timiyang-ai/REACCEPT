   @Test
   public void testinboundPorts() {
      AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
      options.inboundPorts(22, 30);
      assertEquals(options.getInboundPorts()[0], 22);
      assertEquals(options.getInboundPorts()[1], 30);

   }