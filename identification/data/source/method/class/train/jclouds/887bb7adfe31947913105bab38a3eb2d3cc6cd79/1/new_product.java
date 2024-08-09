public TemplateOptions authorizePublicKey(Payload publicKey) {
      this.publicKey = checkNotNull(publicKey, "publicKey");
      return this;
   }