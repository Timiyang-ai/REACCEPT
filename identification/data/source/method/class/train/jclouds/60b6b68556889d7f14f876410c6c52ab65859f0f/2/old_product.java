public EC2TemplateOptions keyPair(String keyPair) {
      checkNotNull(keyPair, "use noKeyPair option to request boot without a keypair");
      checkState(!noKeyPair, "you cannot specify both options keyPair and noKeyPair");
      Utils.checkNotEmpty(keyPair, "keypair must be non-empty");
      this.keyPair = keyPair;
      return this;
   }