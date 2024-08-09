public static AWSEC2TemplateOptions blockOnPort(int port, int seconds) {
         AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
         return AWSEC2TemplateOptions.class.cast(options.blockOnPort(port, seconds));
      }