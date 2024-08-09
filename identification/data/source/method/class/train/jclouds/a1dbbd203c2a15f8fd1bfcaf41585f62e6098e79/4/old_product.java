public static AWSEC2TemplateOptions inboundPorts(int... ports) {
         AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
         return AWSEC2TemplateOptions.class.cast(options.inboundPorts(ports));
      }