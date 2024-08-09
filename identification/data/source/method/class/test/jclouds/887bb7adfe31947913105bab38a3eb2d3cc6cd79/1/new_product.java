@Override
   @Deprecated
   public EC2TemplateOptions authorizePublicKey(String publicKey) {
      return EC2TemplateOptions.class.cast(super.authorizePublicKey(publicKey));
   }