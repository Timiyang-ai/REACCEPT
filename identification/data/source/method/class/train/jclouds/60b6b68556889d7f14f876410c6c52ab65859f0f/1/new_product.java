public EC2TemplateOptions keyPair(String keyPair) {
      checkState(!noKeyPair, "you cannot specify both options keyPair and noKeyPair");
      this.keyPair = checkNotNull(emptyToNull(keyPair), "use noKeyPair option to request boot without a keypair");
      return this;
   }