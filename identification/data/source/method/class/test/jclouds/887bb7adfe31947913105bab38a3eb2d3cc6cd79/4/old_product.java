@Override
   public EC2TemplateOptions installPrivateKey(Payload privateKey) {
      return EC2TemplateOptions.class.cast(super.installPrivateKey(privateKey));
   }