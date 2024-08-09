public static AWSEC2TemplateOptions authorizePublicKey(String rsaKey) {
         AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
         return AWSEC2TemplateOptions.class.cast(options.authorizePublicKey(rsaKey));
      }