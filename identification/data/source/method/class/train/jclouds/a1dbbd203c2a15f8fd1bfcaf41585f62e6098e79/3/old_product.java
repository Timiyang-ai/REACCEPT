public static AWSEC2TemplateOptions installPrivateKey(String rsaKey) {
         AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
         return AWSEC2TemplateOptions.class.cast(options.installPrivateKey(rsaKey));
      }