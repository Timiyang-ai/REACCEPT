@Override
   @Deprecated
   public EC2TemplateOptions installPrivateKey(String privateKey) {
      return EC2TemplateOptions.class.cast(super.installPrivateKey(privateKey));
   }