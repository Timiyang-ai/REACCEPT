public static AWSEC2TemplateOptions keyPair(String keyPair) {
         AWSEC2TemplateOptions options = new AWSEC2TemplateOptions();
         return AWSEC2TemplateOptions.class.cast(options.keyPair(keyPair));
      }