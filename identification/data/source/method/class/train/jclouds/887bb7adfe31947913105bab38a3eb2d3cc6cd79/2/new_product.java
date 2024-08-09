@Override
   @Deprecated
   public EC2TemplateOptions authorizePublicKey(Payload publicKey) {
      return EC2TemplateOptions.class.cast(super.authorizePublicKey(publicKey));
   }